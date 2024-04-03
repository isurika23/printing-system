import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Path filePath = Path.of("/Users/tumashadeshan/Documents/GitHub/printing-system/hello.txt");
        Computer computer = new Computer(filePath);
        computer.start();
    }
}