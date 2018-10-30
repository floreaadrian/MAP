package IView;

import IRepo.*;
import IAnimal.*;
import IController.*;
import Exception.*;

public class View {
    public static void main(String[] argc) {
        IRepo repo = new Repo(3);
        IController ctrl = new Controller(repo);
        IAnimal cow1 = new Cow(18, "An");
        IAnimal cow2 = new Cow(23, "A");
        IAnimal pig1 = new Pig(19, "Anp1");
        IAnimal pig2 = new Pig(23, "Ap2");
        IAnimal chicken1 = new Chicken(11, "Anc1");
        IAnimal chicken2 = new Chicken(23, "Ac2");
        try {
            ctrl.add(cow1);
            ctrl.add(cow2);
            ctrl.add(pig1);
            ctrl.delete(pig1);
            ctrl.add(pig2);
            ctrl.add(chicken1);
            ctrl.add(chicken2);
        } catch (NotEnoughSpace | AnimalNotFound e) {
            System.out.println("The following exception has occurred: " + e.getMessage());
        }
        try {
            ctrl.delete(chicken2);
        } catch (AnimalNotFound e) {
            System.out.println("The following exception has occurred: " + e.getMessage());
        }
        IAnimal[] overKGArray = ctrl.getOverKG(20);
        for (IAnimal animal : overKGArray) {
            if (animal != null) {
                System.out.println(animal);
            }
        }
    }
}
