package in.co.shoonya.nio2.watcherService.event;

import com.google.common.eventbus.Subscribe;

public class FileModificationHandler
{
    
    @Subscribe
    public void handleSourcesChangedEvent(FileModificationEvent event)
    {
        System.out.println(event.getFilePath()+" is modified");
    }

}
