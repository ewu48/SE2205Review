package Stack;

public interface Stack<E> {
    int size = 0;
    boolean isEmpty();
    void push(E e);
    E top();
    E pop();
}
