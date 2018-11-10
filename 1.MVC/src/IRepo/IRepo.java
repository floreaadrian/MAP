package IRepo;


import IAnimal.IAnimal;
import Exception.*;

public interface IRepo {
    void add(IAnimal m) throws NotEnoughSpace;
    void delete(IAnimal m) throws AnimalNotFound;
    IAnimal[] getOverKg(int kg);
}
