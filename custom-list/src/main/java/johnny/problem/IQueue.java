package johnny.problem;

public interface IQueue<E> {
    boolean offer(E e);
    E poll();
    E peek();
    int size();
    boolean isEmpty();
}
