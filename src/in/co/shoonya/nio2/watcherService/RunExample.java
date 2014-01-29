package in.co.shoonya.nio2.watcherService;


import in.co.shoonya.nio2.watcherService.event.FileModificationHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import com.google.common.eventbus.EventBus;

public class RunExample
{

    public static void main(String[] args)
    {
        EventBus eventBus = new EventBus();
        FileModificationHandler eventHandler = new FileModificationHandler();
        List<String> watchedFiles = new ArrayList<String>();
        
        watchedFiles.add("C:\\shoonya.txt");

        FileWatcherService fileWatcherService = FileWatcherService.getInstance(watchedFiles,
                eventBus);

        Executors.newCachedThreadPool().execute(fileWatcherService);

        eventBus.register(eventHandler);
    }

}
