import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final int limit;
    private final Queue<Integer> queue;

    public Buffer(int size) {
        this.limit = size;
        this.queue = new LinkedList<>();
    }

    public synchronized void Write(int i) {
        while(isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Add an element to the end
        this.queue.add(i);
        notifyAll ();
    }

    public synchronized int Read() {
        while(isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Removes the first element in the queue
        int e = this.queue.remove();
        notifyAll();
        return e;
    }

    public boolean isFull() {
        return this.queue.size() == this.limit;
    }
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
