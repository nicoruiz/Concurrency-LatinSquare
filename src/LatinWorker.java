import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatinWorker extends Thread {

    public int id;
    public Buffer buffer;
    public SortedList sortedList;
    private Countdown countDown;

    public LatinWorker(int id, SortedList sortedList, Buffer buffer, Countdown countDown) {
        this.id = id;
        this.buffer = buffer;
        this.sortedList = sortedList;
        this.countDown = countDown;
    }

    @Override
    public void run() {
        try {
            while(true){
                //Read square from buffer
                Runnable square = this.buffer.read(this.id);

                // Verificar si es un latin square
                square.run();

                this.countDown.dec();
                Square squareRunned = (Square)square;
                if (squareRunned.isLatinSquare) { this.sortedList.insert(squareRunned.id); }
            }
        } catch (PoisonPillException pp){
            System.out.println("Poison Pill, finish working");
        }
    }
}
