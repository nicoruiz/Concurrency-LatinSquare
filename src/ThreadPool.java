public class ThreadPool {
    private Buffer buffer;
    private int totalWorkers;

    public ThreadPool( Buffer buffer, int workersSize) {
        this.buffer = buffer;
        this.totalWorkers = workersSize;
        //instanciar y setar buffer
        SortedList sortedList = new SortedList();
        for (int i=0; i < this.totalWorkers; i++) {
            LatinWorker latinworker = new LatinWorker(i,sortedList, this.buffer);
        }
    }
}
