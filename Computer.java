import java.nio.file.Path;

class TypenotSupportedException extends Exception {
    public TypenotSupportedException(String message) {
        super(message);
    }
}

public class Computer extends Thread{
    private Path filePath;
    private String fileType;

    public Computer(Path filePath) {
        this.filePath = filePath;
    }

    private String getFileType() {
        String fileName = filePath.getFileName().toString();
        String[] parts = fileName.split("\\.");
        return parts[parts.length - 1];
    }

    @Override
    public void run() {
        try {
            fileType = getFileType();
            if (fileType.equals("txt")) {

            } else {
                throw new TypenotSupportedException("Unsupported file type: " + fileType);
            }
        } catch (TypenotSupportedException e) {
            System.out.println(e.getMessage());
        }         
    }
}