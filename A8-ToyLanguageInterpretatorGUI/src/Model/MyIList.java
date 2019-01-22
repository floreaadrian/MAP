package Model;

import java.util.List;

public interface MyIList<T> {
    void add(T var);
    T fromIndex(int index);
    String toString();
    void clear();
    boolean contain(T el);
    int size();
    List toList();
}
