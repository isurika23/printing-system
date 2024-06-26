// Create an object to represent a print job
public class PrintJob {
    private String filePath;
    
    public PrintJob(String filePath) {
        this.filePath = filePath;   
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        String[] fileParts = this.filePath.split("/");
        return fileParts[fileParts.length - 1];
    }
}