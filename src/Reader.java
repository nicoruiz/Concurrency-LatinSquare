import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    public void ReadFile(String path) {
        // pass the path to the file as a parameter
        File file = new File(path);
        Scanner sc;
        try {
            sc = new Scanner(file);
            // print each line of the file
            while(sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
