public class LatinWorker extends Thread {
    public SortedList sortedList;
    public int id;

    public LatinWorker(SortedList sortedList, int id) {
        this.sortedList = sortedList;
        this.id = id;
    }

    @Override
    public void run() {
        this.sortedList.insert(this.id);
    }
}
