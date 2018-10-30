package Model;

public class ConstExp extends Exp {
    private int number;

    public ConstExp(int value) {
        this.number = value;
    }

    int eval(MyIDictionary<String, Integer> tbl) {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}