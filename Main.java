public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedQueue sharedQueue = new SharedQueue(); // shared queue for printer and computer
        
        // create 3 computers and 2 printers
        Computer computer1 = new Computer(sharedQueue,"computer 1"); 
        Computer computer2 = new Computer(sharedQueue,"computer 2");
        Computer computer3 = new Computer(sharedQueue,"computer 3");

        Printer printer1 = new Printer(sharedQueue,"printer 1");
        Printer printer2 = new Printer(sharedQueue,"printer 2");

        // start the threads
        computer1.start();
        computer2.start();
        computer3.start();

        printer1.start();
        printer2.start();

        // Simulating a real-world scenario
        computer1.setFile("files/hello.txt");
        Thread.sleep(200);
        computer2.setFile("files/epsilon.txt");
        Thread.sleep(200);
        computer3.setFile("files/hello.txt");
        Thread.sleep(200);
        computer1.setFile("files/epsilon.txt");
        Thread.sleep(200);
        computer2.setFile("files/hello.txt");
        Thread.sleep(200);
        computer3.setFile("files/epsilon.pdf");
        Thread.sleep(200);
        computer1.setFile("files/hello.txt");
    }
}