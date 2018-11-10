package Repository;

import Model.PrgState;


public interface IRepository {
    PrgState getCrtPrg();
    void logPrgStateExec();
    String getFilePath();

    void reset();
}
