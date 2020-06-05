import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedList {
    private final List<Integer> sortedList = new ArrayList<>();

    public synchronized void insert(int i) {
        this.sortedList.add(i);
        Collections.sort(this.sortedList);
    }

    public List<Integer> getSortedList() {
        return this.sortedList;
    }
}
