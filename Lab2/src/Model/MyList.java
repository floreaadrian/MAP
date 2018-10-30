package Model;

import java.util.Vector;

public class MyList<T> implements MyIList<T> {
    private Vector<T> list = new Vector<>();

    public MyList() {
    }

    public void add(T var) {
        this.list.add(var);
    }

    @Override
    public T fromIndex(int index) {
        return this.list.get(index);
    }



    @Override
    public String toString() {
        return this.list.toString();
    }

}
