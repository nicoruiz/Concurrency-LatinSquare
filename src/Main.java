import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        LocalDateTime start = LocalDateTime.now();
        String filePath = "files/inputs-ejemplo";

        // Fix: ahora se lee la primera linea del archivo para obtener la cantidad de squares a analizar.
        int squaresQuantity = Integer.parseInt(Files.lines(Paths.get(filePath)).findFirst().get());
        int bufferSize = squaresQuantity;
        int workersSize = 4;

        // Fix: se inicializa el Countdown con la cantidad de squares presentes en el archivo, en lugar de inicializarlo en 0.
        Countdown countdown = new Countdown(squaresQuantity);
        SortedList sortedList = new SortedList();

        ThreadPool threadPool = new ThreadPool(filePath, bufferSize, workersSize, countdown, sortedList);

        LocalDateTime end = LocalDateTime.now();
        long diff = ChronoUnit.MILLIS.between(start, end);
        System.out.println("Sorted List after finish: " + Arrays.toString(threadPool.sortedList.sortedList.toArray()));
        System.out.println("Time elapsed in milliseconds: " + diff);
    }
}
