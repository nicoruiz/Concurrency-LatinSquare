import java.util.ArrayList;
import java.util.List;

public class Buffer {
    public int limit;
    public List<Runnable> squares;

    public Buffer(int limit) {
        this.limit = limit;
        this.squares = new ArrayList<>();
    }

    public synchronized Runnable read(int threadId) {
        while(isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Removes the first element in the queue

        Runnable square = this.squares.remove(0);
        System.out.println("Thread: " + threadId + " Reading element from buffer, with current size: " + this.squares.size());
        notifyAll();
        return square;
    }

    public synchronized void write(Runnable square) {
        while(isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Add a square to the end
        this.squares.add(this.squares.size(), square);
        System.out.println("Writing element into buffer, with current size: " + this.squares.size());
        notifyAll ();
    }

    public void fillFromFile(String filePath) {
        new Reader(filePath, this).readFile();
    }

    public boolean isFull() {
        return this.squares.size() == this.limit;
    }
    public boolean isEmpty() {
        return this.squares.size() == 0;
    }
}
