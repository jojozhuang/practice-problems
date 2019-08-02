package johnny.problem.customlist.common;

public class ListNode<E> {
    public E item;
    public ListNode<E> previous;
    public ListNode<E> next;
    public ListNode(E item) {
        this.item = item;
        this.previous = null;
        this.next = null;
    }
}
