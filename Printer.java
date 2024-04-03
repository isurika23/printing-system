import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Printer implements Runnable{
    private SharedQueue sharedQueue;

    public Printer(SharedQueue sharedQueue){
        this.sharedQueue = sharedQueue;
    }

    private TextFile readAFile(PrintJob job){
        System.out.println("Reading file");

        try (BufferedReader reader = new BufferedReader(new FileReader("file"))) {
            String line;
            String content = "";
            while ((line = reader.readLine()) != null) {
                content = content +"\n"+ line;
                
            }
            TextFile textObject = new TextFile(content);

            return textObject;
        } 
        catch (IOException e) {
            e.printStackTrace(); // Handle a potential exception
        }

        return null;
    }

    private void print(TextFile textObject){
        System.out.println("Printing \n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(textObject.getText());          

        System.out.println("\nPrinted");
    }

    @Override
    public void run() {
        while (true) {
            PrintJob job = sharedQueue.getPrintJob();
            TextFile textObject = this.readAFile(job);
            this.print(textObject);
        }
    }    
}