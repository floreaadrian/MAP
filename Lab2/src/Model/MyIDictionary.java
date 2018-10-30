package Model;

public interface MyIDictionary<Key,Value>  {
    void add(Key key,Value value);

    boolean isDefined(Key id);

    void update(Key id, Value val);

    Value lookup(Key id);

    String toString();
}
