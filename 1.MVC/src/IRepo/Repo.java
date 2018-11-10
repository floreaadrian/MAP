package IRepo;

import IAnimal.IAnimal;
import Exception.*;

public class Repo implements IRepo {
    private IAnimal[] arr;
    private int index;

    public Repo(int size) {
        this.arr = new IAnimal[size];
        this.index = 0;
    }

    public void add(IAnimal m) throws NotEnoughSpace{
        if (index == this.arr.length) {
            throw new NotEnoughSpace();
        } else {
            arr[index++] = m;
        }
    }

    public void delete(IAnimal m) throws AnimalNotFound {
        boolean flag = false;
        for (int i = 0; i < this.index; ++i)
            if (m.getName().equals(arr[i].getName()) && m.getWeight() == arr[i].getWeight()) {
                if (this.index - 1 - i >= 0) System.arraycopy(this.arr, i + 1, this.arr, i, this.index - 1 - i);
                this.index--;
                flag = true;
                break;
            }
        if (!flag) {
            throw new AnimalNotFound();
        }
    }


    public IAnimal[] getOverKg(int kg) {
        IAnimal[] overKgArray = new IAnimal[this.index];
        int index = 0;
        for (int i = 0; i < this.index; ++i) {
            if (this.arr[i].getWeight() > kg) {
                overKgArray[index++] = this.arr[i];
            }
        }
        return overKgArray;
    }
}
