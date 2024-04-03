// handle the exception when the file type is not supported
class TypenotSupportedException extends Exception {
    public TypenotSupportedException(String message) {
        super(message);
    }
}

// Create a class to represent a computer
public class Computer extends Thread{
    private String fileType;
    private SharedQueue sharedQueue;
    private String filePath;
    private String computerName;

    public Computer(SharedQueue sharedQueue,String computerName) {
        this.sharedQueue = sharedQueue;
        this.computerName = computerName;
    }

    public void setFile(String filePath) {
        // set the file path
        if(this.filePath != null){
            System.out.println(computerName + " is busy. Try again later.");
            return;
        }
        this.filePath = filePath;
    }

    private String getFileType() {
        String[] fileParts = this.filePath.split("\\.");
        return fileParts[fileParts.length - 1];
    }
 
    @Override
    public void run() {
        while(true){
            synchronized (this){ // synchronize the block of code
                if (this.filePath != null){
                    try {
                        fileType = getFileType();
                        if (fileType.equals("txt")) {
                            PrintJob printJob = new PrintJob(filePath);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            sharedQueue.addPrintJob(printJob);
                            System.out.println("Added to queue from : " + this.computerName);
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