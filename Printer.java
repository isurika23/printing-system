import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Printer extends Thread{
    private SharedQueue sharedQueue;
    private String printerName;

    public Printer(SharedQueue sharedQueue,String printerName) {
        // initialize the shared queue and printer name
        this.sharedQueue = sharedQueue;
        this.printerName = printerName;
    }

    private TextFile readAFile(PrintJob job){
        // read the file
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Reading file");

        try (BufferedReader reader = new BufferedReader(new FileReader(job.getFilePath()))) {
            String line;
            String content = "";
            while ((line = reader.readLine()) != null) {
                content = content +"\n"+ line;
                
            }
            TextFile textObject = new TextFile(content);

            return textObject;
        }
        // Handle potential exceptions 
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void print(TextFile textObject){
        // print the file
        System.out.println("Printing from : " + this.printerName);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(textObject.getText());          

        System.out.println("\nPrinted successfully from : " + this.printerName);
    }

    @Override
    public void run() {
        // run the printer
        while (true) {
            PrintJob job = sharedQueue.getPrintJob();
            TextFile textObject = this.readAFile(job);
            this.print(textObject);
        }
    }    
}