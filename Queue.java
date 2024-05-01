import java.util.ArrayList;
public class Queue <T> {

    private ArrayList<T> q = new ArrayList<>();

    public void enqueue(T t) {
        q.add(t);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Kolejka jest pusta");
        }
        T r = q.getFirst();
        q.remove(0);
        return r;
    }

    public void print() {
        for (T item : q) {
            System.out.print(item.toString() + ",");
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return q.size() == 0;
    }
}









