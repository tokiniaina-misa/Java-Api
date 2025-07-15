import java.util.*;

public class TicketQueue<T> {
    private final LinkedList<T> q = new LinkedList<>();

    public void enqueue(T item) {
        q.addLast(item);
    }

    public T dequeue() {
        return q.isEmpty() ? null : q.removeFirst();
    }

    public T peek() {
        return q.isEmpty() ? null : q.getFirst();
    }

    public int size() {
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public List<T> getAll() {
        return new LinkedList<>(q);
    }
}
