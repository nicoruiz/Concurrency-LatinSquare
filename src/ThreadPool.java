public class ThreadPool {
    private Buffer buffer;
    private int totalWorkers;

    public ThreadPool(String filePath, int bufferSize, int workersSize) {
        this.buffer = new Buffer(filePath, bufferSize);
        this.totalWorkers = workersSize;
        //instanciar y setar buffer
        SortedList sortedList = new SortedList();
        for (int i=0; i < this.totalWorkers; i++) {
            LatinWorker latinworker = new LatinWorker(i,sortedList, this.buffer);
        }
    }
}
