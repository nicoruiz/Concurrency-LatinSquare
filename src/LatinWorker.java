public class LatinWorker extends Thread {
    public int id;
    public Buffer buffer;
    public SortedList sortedList;

    public LatinWorker(int id, SortedList sortedList, Buffer buffer) {
        this.id = id;
        this.sortedList = sortedList;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while(true){
                //Read square from buffer
            }
        } catch (PoisonPillException pp){
            System.out.println("Poison Pill, finish working");
        };
        this.sortedList.insert(this.id);
    }
}
