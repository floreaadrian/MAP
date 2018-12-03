package Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class MyIRandKeyDict<Value> {
    Generator gen;
    HashMap<Integer, Value> dictionary = new HashMap<>();

    MyIRandKeyDict(Generator gen) {
        this.gen = gen;
    }

    abstract Integer add(Value value);

    abstract boolean isDefined(Integer id);

    abstract void update(Integer id, Value val);

    abstract Collection<Value> values();

    abstract Collection<Integer> keys();

    abstract Value lookup(Integer id);

    abstract void delete(Integer id);

    abstract void clear();

    public abstract Map<Integer, Value> getContent();

    public abstract void setContent(Map<Integer, Value> content);
}
