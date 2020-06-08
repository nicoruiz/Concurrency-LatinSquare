import java.util.ArrayList;
import java.util.List;

public class Square {
    public int id;
    public int n;
    public List<List<Integer>> matrix;

    public Square(int id, int n, List<String> numbers) {
        this.id = id;
        this.n = n;
        this.setValues(n, numbers);
    }

    public void setValues(int n, List<String> numbers) {
        List<List<Integer>> matrix = new ArrayList<>();
        // When splitFrecuency is zero, it means that row array is completed and it is appended to matrix
        int splitFrecuency = n;
        List<Integer> row = new ArrayList<>();
        for(int i = 0; i < numbers.size(); i++) {
            int number = Integer.parseInt(numbers.get(i));
            if(i > 0) {
                row.add(number);
            }
            if(splitFrecuency == 0 || isLastElement(i, numbers)) {
                matrix.add(row);
                row = new ArrayList<>();
                splitFrecuency = n;
            }
            splitFrecuency--;
        }
        // Set the matrix for this square to determine if it is a latin one
        this.matrix = matrix;
    }

    private boolean isLastElement(int index, List<String> numbers) {
        return index == numbers.size() - 1;
    }
}
