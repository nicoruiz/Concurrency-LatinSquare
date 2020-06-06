import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(8);
        // Invocar threadpool
        ThreadPool threadPool = new ThreadPool();

        Reader reader = new Reader(buffer);
        reader.readFile("files/inputs-ejemplo");
        // Main validaciones latin square

    }

    /* MAIN 2
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        // Read file
        Reader reader = new Reader();
        //reader.readFile("files/inputs-ejemplo");

        // SortedList testing
        SortedList list = new SortedList();
        for(int i = 0; i < 10; i++) {
            LatinWorker latinWoker = new LatinWorker(list, i);
            latinWoker.start();
        }

        // Instanciar ThreadPool
        ThreadPool tp = new ThreadPool(5);
        // Llenar el buffer (es propiedad del threadpool)
        // Cuando termina de llenarse, lanzar los LatinWorkers para analizar cada Square si es Latin o no

        LocalDateTime end = LocalDateTime.now();
        long diff = ChronoUnit.MILLIS.between(start, end);
        System.out.println("Time elapsed in milliseconds: " + diff);
        System.out.println("Sorted list: " + Arrays.toString(list.getSortedList().toArray()));
    }*/
}
