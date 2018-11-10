package IAnimal;

public class Cow implements IAnimal {
    private int weight;
    private String name;


    public Cow(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getWeight() {
        return this.weight;
    }

    public String toString() {
        return this.name + " " + this.weight;
    }
}
