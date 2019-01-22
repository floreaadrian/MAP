package Model;

public class Generator {
    private int generatedNumber;

    public Generator() {
        this.generatedNumber = 0;
    }

    int getGeneratedNumber() {
        synchronized (this) {
            this.generatedNumber++;
            return this.generatedNumber;
        }
    }

}
