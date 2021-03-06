package Model;

public interface MyIStack<T> {

    T pop();
    void clear();
    void push(T v);

    boolean isEmpty();
    String toString();
}