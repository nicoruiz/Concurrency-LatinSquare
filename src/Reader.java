import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reader {
    public Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    public void readFile(String path) {
        // pass the path to the file as a parameter
        File file = new File(path);
        Scanner sc;
        int squareId = 0;
        try {
            sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                if(squareId > 0) {
                    // Add a square to buffer per each line
                    this.addSquareToBuffer(squareId, currentLine);
                    System.out.println(sc.nextLine());
                }
                squareId++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addSquareToBuffer(int squareId, String line) {
        // Split list with blank spaces
        List<String> numbers = Arrays.asList(line.split(" "));
        // Read all until find the first space
        int n = Integer.parseInt(numbers.get(0));
        //numbers.remove(0);
        // Create a square with his id, size and line values
        Square square = new Square(squareId, n, numbers);
        // Add square with values as list of list to buffer
        this.buffer.write(square);
    }
}
