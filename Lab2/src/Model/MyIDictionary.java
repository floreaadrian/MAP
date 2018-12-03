package Model;

import java.util.Collection;

public interface MyIDictionary<Key,Value>  {
    void add(Key key,Value value);

    boolean isDefined(Key id);

    void update(Key id, Value val);
    Collection<Value> values();
    Collection<Key> keys();
    Value lookup(Key id);

    void clear();
    String toString();
    public MyIDictionary<Key,Value> clone();
}
