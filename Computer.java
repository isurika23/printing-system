import java.nio.file.Path;

class TypenotSupportedException extends Exception {
    public TypenotSupportedException(String message) {
        super(message);
    }
}

public class Computer extends Thread{
    private Path filePath;
    private String fileType;
    private SharedQueue sharedQueue;

    public Computer(Path filePath,SharedQueue sharedQueue) {
        this.filePath = filePath;
        this.sharedQueue = sharedQueue;
    }

    private String getFileType() {
        String fileName = filePath.getFileName().toString();
        System.out.println(fileName);
        String[] parts = fileName.split("\\.");
        return parts[parts.length - 1];
    }

    @Override
    public void run() {
        try {
            fileType = getFileType();
            if (fileType.equals("txt")) {
                PrintJob printJob = new PrintJob(filePath);
                sharedQueue.addPrintJob(printJob);
            } else {
                throw new TypenotSupportedException("Unsupported file type: " + fileType);
            }
        } catch (TypenotSupportedException e) {
            System.out.println(e.getMessage());
        }         
    }
}