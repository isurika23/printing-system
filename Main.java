import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedQueue sharedQueue = new SharedQueue();

        Computer computer1 = new Computer(sharedQueue);
        Computer computer2 = new Computer(sharedQueue);
        Computer computer3 = new Computer(sharedQueue);

        Printer printer1 = new Printer(sharedQueue);
        Printer printer2 = new Printer(sharedQueue);

        computer1.start();
        computer2.start();
        computer3.start();

        printer1.start();
        printer2.start();

        computer1.setFile(Path.of("hello.txt"));

    }
}