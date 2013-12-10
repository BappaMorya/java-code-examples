package in.co.shoonya.nio2.watcherService.event;

public class FileModificationEvent
{
    private String filePath;

    public FileModificationEvent(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    
    
}
