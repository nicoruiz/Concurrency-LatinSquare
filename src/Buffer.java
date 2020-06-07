import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private final int limit;
    private final List<Square> squares;

    public Buffer(int limit) {
        this.limit = limit;
        this.squares = new ArrayList<>();
    }

    public synchronized void write(Square square) {
        while(isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Add a square to the end
        this.squares.add(this.squares.size(), square);
        System.out.println("Buffer added square " + square.id + " - buffer size: " + this.squares.size());
        notifyAll ();
    }

    public synchronized Square read(int threadId) {
        while(isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Removes the first element in the queue
        Square square = this.squares.remove(0);
        System.out.println("Thread " + threadId + " processing square " + square.id + " - buffer size: " + this.squares.size());
        notifyAll();
        return square;
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
