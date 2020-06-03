public class ThreadPool {
    private Buffer buffer;

    public ThreadPool(int bufferSize) {
        this.buffer = new Buffer(bufferSize);
    }
}
