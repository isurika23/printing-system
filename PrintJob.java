import java.nio.file.Path;

public class PrintJob {
    private Path filePath;
    
    public PrintJob(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }
}