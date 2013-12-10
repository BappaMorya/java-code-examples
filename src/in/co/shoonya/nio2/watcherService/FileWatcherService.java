package in.co.shoonya.nio2.watcherService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import in.co.shoonya.nio2.watcherService.event.FileModificationEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.eventbus.EventBus;

public class FileWatcherService implements Runnable
{

    private WatchService watcher;
    private final Map<WatchKey, Path> keys = new HashMap<WatchKey, Path>(4);
    
    private EventBus eventBus = null;
    private static FileWatcherService INSTANCE = null;    
    private List<String> watchedFiles = new ArrayList<>();
    
    
    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event)
    {
        return (WatchEvent<T>) event;
    }   
    
    
    /**
     * 
     * @param dirPath directory where sources/ssim ini file is placed
     * @param fileName source file name i.e. sources.ini / ssim.ini
     */
    private FileWatcherService(List<String> watchedFiles,EventBus eventBus) 
    {
        try
        {
            watcher = FileSystems.getDefault().newWatchService();
            this.eventBus = eventBus;
            register(watchedFiles);
            INSTANCE = this;
            
        }
        catch (Exception e)
        {
            System.out.println("Exception in creation of watch service ");
            e.printStackTrace();
        }
    }
    
    public static final FileWatcherService getInstance(List<String> watchedFiles, EventBus eventBus)
    {
        if(INSTANCE==null)
        {
            INSTANCE = new FileWatcherService(watchedFiles,eventBus);
        }
        return INSTANCE;        
    }    
    
    
    public void postCacheRefreshNotification(EventBus eventBus,String filePath)
    {
        FileModificationEvent event = new FileModificationEvent(filePath);
        eventBus.post(event);
    }    
    
    /**
     * Register the given directory with the WatchService
     */
    private void register(List<String> watchedFiles) throws IOException
    {
        for(String watchedFile : watchedFiles)
        {
            String watchedDir = new File(watchedFile).getParentFile().getAbsolutePath();
            
            Path dir = Paths.get(watchedDir);
            // we are not watching for create and delete events for this directory
            WatchKey key = dir.register(watcher,  ENTRY_MODIFY);
            this.watchedFiles.add(watchedFile);
            keys.put(key, dir);            
        }
        System.out.println("watcher registration done for: "+watchedFiles);
    }    

    /**
     * Process all events for keys queued to the watcher
     */
    void watchFileModificationEvents()
    {
        for (;;)
        {
            // wait for key to be signalled
            WatchKey key;
            try
            {
                key =  watcher.take();
            }
            catch (InterruptedException x)
            {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null)
            {
                System.out.println("WatchKey not recognized!!");
                continue;
            }
            
            for(String filePath : this.watchedFiles )
            {
                String watchedPath =new File(filePath).getParentFile().getAbsolutePath(); 
                if(dir.toFile().getAbsolutePath().equalsIgnoreCase(watchedPath))
                {
                    WatchEvent<?> modifyEvent = null;
                    // this makes sure that only the single modify event is being considered 
                    // when there are multiple callbacks from the watcher service 
                    for (WatchEvent<?> event : key.pollEvents())
                    {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == OVERFLOW)
                        {
                            continue;
                        }else if(kind == ENTRY_MODIFY)
                        {
                            modifyEvent = event;
                        }
                    }
                    
                    if(modifyEvent!=null)
                    {
                        // Context for directory entry event is the file name of entry
                        WatchEvent<Path> ev = cast(modifyEvent);
                        Path name = ev.context();
                        Path child = dir.resolve(name);
                        if(child.toFile().getAbsolutePath().equalsIgnoreCase(filePath))
                        {
                            // Update the modified entries and send "hot-refresh" notification to cache  
                            System.out.println("Operation "+modifyEvent.kind().name()+" is performed on "+child);
                            postCacheRefreshNotification(eventBus,child.toFile().getAbsolutePath());
                            System.out.println(child +" cache is updated and event dispatched on eventbus!");
                        }
                    }  
                    break;
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid)
            {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty())
                {
                    break;
                }
            }
        }
    }        
    @Override
    public void run()
    {
        watchFileModificationEvents();
    }

    
}
