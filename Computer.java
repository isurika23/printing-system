import java.nio.file.Path;

class TypenotSupportedException extends Exception {
    public TypenotSupportedException(String message) {
        super(message);
    }
}

public class Computer extends Thread{
    private String fileType;
    private SharedQueue sharedQueue;
    private Path filePath;

    public Computer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    public void setFile(Path filePath) {
        this.filePath = filePath;
    }

    private String getFileType() {
        String fileName = filePath.getFileName().toString();
        String[] parts = fileName.split("\\.");
        return parts[parts.length - 1];
    }
 
    @Override
    public void run() {
        while(true){
            synchronized (this){
                if (this.filePath != null){
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
                    this.filePath = null;
                }
            }
        }      
    }
}