import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        // Read file
        Reader reader = new Reader();
        //reader.readFile("files/inputs-ejemplo");

        // SortedList testing
        SortedList list = new SortedList();
        new Thread(() -> list.insert(6)).start();
        new Thread(() -> list.insert(3)).start();
        new Thread(() -> list.insert(10)).start();
        new Thread(() -> list.insert(1)).start();
        new Thread(() -> list.insert(0)).start();
        new Thread(() -> list.insert(4)).start();

        // Instanciar ThreadPool
        ThreadPool tp = new ThreadPool(5);
        // Llenar el buffer (es propiedad del threadpool)
        // Cuando termina de llenarse, lanzar los LatinWorkers para analizar cada Square si es Latin o no

        LocalDateTime end = LocalDateTime.now();
        long diff = ChronoUnit.MILLIS.between(start, end);
        System.out.println("Time elapsed in milliseconds: " + diff);
        System.out.println("Sorted list: " + Arrays.toString(list.getSortedList().toArray()));
    }
}
