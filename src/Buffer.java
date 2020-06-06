import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final int limit;
    private final Queue<Square> queue;

    public Buffer(int size) {
        this.limit = size;
        this.queue = new LinkedList<>();
        //instanciar reader
    }

    public synchronized void write(Square square) {
        while(isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Add an element to the end
        this.queue.add(square);
        notifyAll ();
    }

    public synchronized Square read() {
        while(isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Removes the first element in the queue
        Square square = this.queue.remove();
        notifyAll();
        return square;
    }

    public boolean isFull() {
        return this.queue.size() == this.limit;
    }
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
