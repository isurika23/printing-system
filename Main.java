import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedQueue sharedQueue = new SharedQueue();
        Path filePath = Path.of("/Users/tumashadeshan/Documents/GitHub/printing-system/hello.txt");
        Computer computer = new Computer(filePath, sharedQueue);
        computer.start();
    }
}