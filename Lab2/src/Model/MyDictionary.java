package Model;


import java.util.HashMap;

public class MyDictionary<Key, Value> implements MyIDictionary<Key, Value> {
    private HashMap<Key, Value> dictionary = new HashMap<>();

    public MyDictionary() {

    }

    @Override
    public void add(Key key, Value value) {
        this.dictionary.put(key, value);
    }


    @Override
    public boolean isDefined(Key id) {
        return this.dictionary.containsKey(id);
    }

    @Override
    public void update(Key id, Value val) {
        this.dictionary.put(id, val);

    }

    @Override
    public Value lookup(Key id) {
        return this.dictionary.get(id);
    }

    @Override
    public String toString() {
        return this.dictionary.toString();
    }
}
