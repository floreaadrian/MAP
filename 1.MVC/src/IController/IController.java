package IController;

import IAnimal.IAnimal;
import Exception.*;

public interface IController {
    void add(IAnimal m) throws NotEnoughSpace;
    void delete(IAnimal m) throws AnimalNotFound;
    IAnimal[] getOverKG(int kg);
}
