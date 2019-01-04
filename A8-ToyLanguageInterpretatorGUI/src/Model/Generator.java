package Model;

public class Generator {
    private int generatedNumber;
    public Generator(){
        this.generatedNumber = 0;
    }
    int getGeneratedNumber(){
        this.generatedNumber++;
        return this.generatedNumber;
    }

}
