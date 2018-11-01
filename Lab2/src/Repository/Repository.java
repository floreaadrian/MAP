package Repository;

import Model.MyList;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {
    private MyList<PrgState> prgList = new MyList<>();
    private String logFilePath;

    public Repository(PrgState e, String path) {
        this.prgList.add(e);
        this.logFilePath = path;
    }

    @Override
    public void reset() {
        this.prgList.fromIndex(0).reset();
    }

    @Override
    public PrgState getCrtPrg() {
        return this.prgList.fromIndex(0);
    }

    @Override
    public void logPrgStateExec() {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(this.prgList.fromIndex(0).toString());
            logFile.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public String getFilePath() {
        return this.logFilePath;
    }

}
