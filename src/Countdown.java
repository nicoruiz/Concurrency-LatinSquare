public class Countdown {
    private int counter;

    public Countdown(int squaresQuantity) {
        this.counter = squaresQuantity;
    }

    public synchronized void dec() {
        this.counter--;
        notifyAll ();
    }

    public synchronized void zero() {
        while(isNotZero()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // All squares processed
        notifyAll ();
    }

    public boolean isNotZero() {
        return this.counter > 0;
    }
}
