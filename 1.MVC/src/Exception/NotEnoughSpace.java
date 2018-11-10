package Exception;

public class NotEnoughSpace extends Exception {
    public NotEnoughSpace() {
        super("There is no more space in the array!");
    }
}