import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        // Read file
        Reader reader = new Reader();
        reader.ReadFile("files/inputs-ejemplo");

        // Instanciar ThreadPool
        ThreadPool tp = new ThreadPool(5);
        // Llenar el buffer (es propiedad del threadpool)
        // Cuando termina de llenarse, lanzar los LatinWorkers para analizar cada Square si es Latin o no

        LocalDateTime end = LocalDateTime.now();
        long diff = ChronoUnit.MILLIS.between(start, end);
        System.out.println("Time elapsed in milliseconds: " + diff);
    }
}
