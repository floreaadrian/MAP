package Model;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> implements MyIStack<T> {
    private List<T> stack = new ArrayList<>(); //a field whose type Type is an appropriate generic java library //collection

    public MyStack() {
    }

    @Override
    public T pop() {
        T elemToReturn = this.stack.get(this.stack.size()-1);
        this.stack.remove(this.stack.size() - 1);
        return elemToReturn;
    }

    @Override
    public void clear() {
        this.stack.clear();
    }

    @Override
    public void push(T v) {
        this.stack.add(v);
    }


    @Override
    public boolean isEmpty() {
        return this.stack.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        int size = this.stack.size() - 1;
        for (int index = size; index >= 0; index--) res.append(this.stack.get(index).toString()).append("\n");
        return res.toString();
    }
}