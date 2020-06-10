import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        String filePath = "files/latin_squares200";
        int bufferSize = 50;
        int workersSize = 4;
        ThreadPool threadPool = new ThreadPool(filePath, bufferSize, workersSize);

        LocalDateTime end = LocalDateTime.now();
        long diff = ChronoUnit.MILLIS.between(start, end);
        System.out.println("Sorted List after finish: " + Arrays.toString(threadPool.sortedList.sortedList.toArray()));
        System.out.println("Time elapsed in milliseconds: " + diff);
    }
}
