package johnny.problem;

public interface IStack<E> {
    void push(E e);
    E pop();
    E peek();
    int size();
    boolean isEmpty();
}
