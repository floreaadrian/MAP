package Repository;

import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> prgList = new ArrayList<>();
    private String logFilePath;

    public Repository(PrgState e, String path) {
        this.prgList.add(e);
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        } catch (IOException ee) {
            ee.getStackTrace();
        }

        this.logFilePath = path;
    }

    @Override
    public void reset() {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
            logFile.write("");
            logFile.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        for (PrgState aPrgList : prgList)
            aPrgList.reset();
    }

    @Override
    public void logPrgStateExec(PrgState pr) {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(pr.toString());
            logFile.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public int size() {
        return this.prgList.size();
    }

    @Override
    public String getFilePath() {
        return this.logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.prgList;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        this.prgList = list;
    }

}
