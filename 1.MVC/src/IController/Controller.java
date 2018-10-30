package IController;

import IRepo.IRepo;
import IAnimal.IAnimal;
import Exception.*;

public class Controller implements IController {
    private IRepo repo;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public void add(IAnimal m) throws NotEnoughSpace {
        this.repo.add(m);
    }

    public void delete(IAnimal m) throws AnimalNotFound {
        this.repo.delete(m);
    }

    public IAnimal[] getOverKG(int kg) {
        return this.repo.getOverKg(kg);
    }

}
