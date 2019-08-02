package johnny.problem.customlist.common;

public interface IQueue<E> {
    boolean offer(E e);
    E poll();
    E peek();
    int size();
    boolean isEmpty();
}
