import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Printer implements Runnable{
    private String file;

    public Printer(String file){
        this.file = file;
    }

    private void readFile(){
        System.out.println("Reading file");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Process the line
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle a potential exception
        }
    }

    private void print(){
        this.readFile();
        System.out.println("Printing");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Printed");
    }

    @Override
    public void run() {
        while (true) {
            this.print();
        }
    }
    
}