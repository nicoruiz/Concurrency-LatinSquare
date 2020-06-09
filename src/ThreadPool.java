public class ThreadPool {
    public Buffer buffer;
    public int totalWorkers;
    public Countdown countDown;
    public SortedList sortedList;

    public ThreadPool(String filePath, int bufferSize, int workersSize) {
        this.buffer = new Buffer(bufferSize);
        this.totalWorkers = workersSize;
        this.countDown = new Countdown();
        this.sortedList = new SortedList();

        for (int i=0; i < this.totalWorkers; i++) {
            LatinWorker latinWorker = new LatinWorker(i, sortedList, this.buffer, countDown);
            latinWorker.start();
        }
        // Fill the buffer from a file providing a path
        this.buffer.fillFromFile(filePath,countDown);
        this.countDown.zero();
        this.stop();
    }

    private void stop() {
        for (int i = 0; i<totalWorkers; i++){
            this.buffer.write(new PoisonPill());
        }
    }
}
