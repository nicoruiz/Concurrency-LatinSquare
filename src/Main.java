import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        String filePath = "files/inputs-ejemplo";
        int bufferSize = 10;
        int workersSize = 2;
        ThreadPool threadPool = new ThreadPool(filePath, bufferSize, workersSize);

        LocalDateTime end = LocalDateTime.now();
        long diff = ChronoUnit.MILLIS.between(start, end);
        System.out.println("Time elapsed in milliseconds: " + diff);
    }
}
