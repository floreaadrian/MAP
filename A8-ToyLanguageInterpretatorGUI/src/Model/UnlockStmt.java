package Model;

import Exceptions.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStmt implements IStmt {
    private String var;

    public UnlockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableNotFound {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> lockTable = state.getLockTable();
        if (!symTbl.isDefined(var)) throw new VariableNotFound();
        Integer foundIndex = symTbl.lookup(var);
        if (!lockTable.isDefined(foundIndex)) throw new VariableNotFound();
        Integer lockTableVal = lockTable.lookup(foundIndex);
        if (lockTableVal == state.getId()) {
            Lock l = new ReentrantLock();
            l.lock();
            try {
                lockTable.update(foundIndex, -1);
            } finally {
                l.unlock();
            }

        }
        return null;
    }

    @Override
    public String toString() {
        return "unlock( " + var + ")";
    }

}
