import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private Queue<PrintJob> queue;
    private int size;

    public SharedQueue() {
        this.size = 5;
        this.queue = new LinkedList<>();
    }

    public synchronized void addPrintJob(PrintJob job) {
        while (queue.size() == size) {
            // wait for the queue to have space
            try {
                queue.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(job);
    }

    public synchronized PrintJob getPrintJob() {
        while (queue.isEmpty()) {
            // wait for the queue to have a job
            try {
                queue.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        PrintJob job = queue.poll();
        return job;
    }
}
