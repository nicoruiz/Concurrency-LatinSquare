import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatinWorker extends Thread {
    public int id;
    public Buffer buffer;
    public SortedList sortedList;

    public LatinWorker(int id, SortedList sortedList, Buffer buffer) {
        this.id = id;
        this.sortedList = sortedList;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while(true){
                //Read square from buffer
                Square square = this.buffer.read(this.id);
                // Verificar si es un latin square
                this.verifySquare(square);
            }
        } catch (PoisonPillException pp){
            System.out.println("Poison Pill, finish working");
        }
    }

    private void verifySquare(Square square) {
        if(isNxNShape(square) && allLessThanN(square) &&
                noRepeatedNumbersInRows(square) && noRepeatedNumbersInColumns(square)) {
            this.sortedList.insert(square.id);
        }
    }

    private boolean isNxNShape(Square square) {
        // Validation 1: El size de la matriz tiene que ser igual a n y
        // el size de todas las sublistas(cada fila de la matriz del square) es igual a n
        return square.matrix.size() == square.n &&
                                  square.matrix.stream()
                                    .allMatch(row -> row.size() == square.n);
    }

    private boolean allLessThanN(Square square) {
        // Validation 2: no puede haber un elemento de la fila mayor a n
        return square.matrix.stream()
                .allMatch(row -> row.stream()
                        .allMatch(e -> e <= square.n));
    }

    private boolean noRepeatedNumbersInRows(Square square) {
        // Validation 3: En ninguna lista se puede repetir un numero (es lo mismo que decir que en ninguna fila se repiten numeros)
        return square.matrix.stream()
                .noneMatch(this::hasAnyRepeatedElement);
    }

    private boolean noRepeatedNumbersInColumns(Square square) {
        // Validation 4: En ninguna columna se puede repetir un numero
        return !this.hasAnyRepeatedInColumn(square);
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

    private boolean hasAnyRepeatedInColumn(Square square) {
        int size = square.matrix.size();
        for (int i = 0; i < size; i++) {
            List<Integer> columnList = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                int current = square.matrix.get(j).get(i);
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
}
