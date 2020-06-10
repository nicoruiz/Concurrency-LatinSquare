public class ThreadPool {
    public Buffer buffer;
    public int totalWorkers;
    public Countdown countdown;
    public SortedList sortedList;

    public ThreadPool(String filePath, int bufferSize, int workersSize, Countdown countdown, SortedList sortedList) {
        this.buffer = new Buffer(bufferSize);
        this.totalWorkers = workersSize;
        this.countdown = countdown;
        this.sortedList = sortedList;

        for (int i=0; i < this.totalWorkers; i++) {
            LatinWorker latinWorker = new LatinWorker(i, sortedList, this.buffer, countdown);
            latinWorker.start();
        }
        // Fill the buffer from a file providing a path
        this.buffer.fillFromFile(filePath, this.countdown);
        this.countdown.zero();
        this.stop();
    }

    private void stop() {
        for (int i = 0; i<totalWorkers; i++){
            this.buffer.write(new PoisonPill());
        }
    }
}
