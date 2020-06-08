public class ThreadPool {
    private Buffer buffer;
    private int totalWorkers;

    public ThreadPool(String filePath, int bufferSize, int workersSize) {
        this.buffer = new Buffer(bufferSize);
        this.totalWorkers = workersSize;

        SortedList sortedList = new SortedList();
        for (int i=0; i < this.totalWorkers; i++) {
            LatinWorker latinWorker = new LatinWorker(i, sortedList, this.buffer);
            latinWorker.start();
        }
        // Fill the buffer from a file providing a path
        this.buffer.fillFromFile(filePath);
    }
}
