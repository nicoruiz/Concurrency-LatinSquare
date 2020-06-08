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
                this.sleep(4000);
                Square square = this.buffer.read(this.id);
                // Verificar si es un latin square
                this.verifySquare(square);
            }
        } catch (PoisonPillException | InterruptedException pp){
            System.out.println("Poison Pill, finish working");
        }
    }

    public void verifySquare(Square square) {
        // Validation 1: El size de la matriz tiene que ser igual a n y
        // el size de todas las sublistas(cada fila de la matriz del square) es igual a n.
        boolean validation1 = square.matrix.size() == square.n &&
                              square.matrix.stream()
                                .allMatch(row -> row.size() == square.n);
        // Validation 2: no puede haber un elemento de la fila mayor a n
        boolean validation2 = square.matrix.stream()
                                .allMatch(row -> row.stream()
                                        .allMatch(e -> e <= square.n));
        if(validation1 && validation2) {
            this.sortedList.insert(square.id);
        }
    }
}
