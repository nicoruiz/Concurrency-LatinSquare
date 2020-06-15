import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Square implements Runnable {

    public int id;
    public int n;
    public List<List<Integer>> matrix;
    public boolean isLatinSquare = false;

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
            if(splitFrecuency == 0 || isLastElementStr(i, numbers)) {
                matrix.add(row);
                row = new ArrayList<>();
                splitFrecuency = n;
            }
            splitFrecuency--;
        }
        // Set the matrix for this square to determine if it is a latin one
        this.matrix = matrix;
    }

    @Override
    public void run() {
        // Verify if is Latin Square
        this.verifySquare();
    }
    private void verifySquare() {
        if(this.isNxNShape() && this.allLessThanN() &&
                this.noRepeatedNumbersInRows() && this.noRepeatedNumbersInColumns()) {
            this.isLatinSquare = true;
        }
    }

    private boolean isNxNShape() {
        // Validation 1: El size de la matriz tiene que ser igual a n y
        // el size de todas las sublistas(cada fila de la matriz del square) es igual a n
        return this.matrix.size() == this.n &&
                this.matrix.stream()
                        .allMatch(row -> row.size() == this.n);
    }

    private boolean allLessThanN() {
        // Validation 2: no puede haber un elemento de la fila mayor a n
        return this.matrix.stream()
                .allMatch(row -> row.stream()
                        .allMatch(e -> e <= this.n));
    }

    private boolean noRepeatedNumbersInRows() {
        // Validation 3: En ninguna lista se puede repetir un numero (es lo mismo que decir que en ninguna fila se repiten numeros)
        return this.matrix.stream()
                .noneMatch(this::hasAnyRepeatedElement);
    }

    private boolean noRepeatedNumbersInColumns() {
        // Validation 4: En ninguna columna se puede repetir un numero
        return !this.hasAnyRepeatedInColumn();
    }

    private boolean hasAnyRepeatedElement(List<Integer> row) {
        // Sort list to compare easily if there is any repeated element
        List<Integer> copyRow = new ArrayList<>(row);
        Collections.sort(copyRow);
        for (int i = 0; i < copyRow.size(); i++) {
            if(!isLastElement(i, copyRow)) {
                int current = copyRow.get(i);
                int next = copyRow.get(i+1);
                // If current is equals to next means that a repeated element has been found
                if(current == next) return true;
            }
        }
        return false;
    }

    private boolean hasAnyRepeatedInColumn() {
        int size = this.matrix.size();
        for (int i = 0; i < size; i++) {
            List<Integer> columnList = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                int current = this.matrix.get(j).get(i);
                // Check if the current element exists in the columnList
                if(columnList.contains(current)) return true;
                // Add element to list to continue checking
                columnList.add(current);
            }
        }
        return false;
    }

    private boolean isLastElement(int index, List<Integer> list) {
        return index == list.size() - 1;
    }
    private boolean isLastElementStr(int index, List<String> list) { return index == list.size() - 1; }
}
