package Repository;

import Model.MyList;
import Model.PrgState;

public class Repository implements IRepository {
    private MyList<PrgState> prgList = new MyList<>();

    public Repository(PrgState e) {
        prgList.add(e);
    }

    public PrgState getCrtPrg() {
        return this.prgList.fromIndex(0);
    }
}
