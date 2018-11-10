package Model;

import java.util.Collection;
import java.util.Map;

public interface MyIDictionary<Key,Value>  {
    void add(Key key,Value value);

    boolean isDefined(Key id);

    void update(Key id, Value val);
    Collection<Value> values();
    Value lookup(Key id);

    void delete(Key id);
    void clear();
    Collection<Key> keys();
    Map<Key,Value> getContent();
    void setContent(Map<Key,Value> content);
    String toString();
}
