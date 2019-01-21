package Model;

import Exceptions.*;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStmt implements IStmt {
    private String var;


    public LockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileAlreadyUsed, FileDoesntExist, IOException, FileNotOpened {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> lockTable = state.getLockTable();
        MyIStack<IStmt> stack = state.getStk();
        if (!symTbl.isDefined(var)) throw new VariableNotFound();
        Integer foundIndex = symTbl.lookup(var);
        if (!lockTable.isDefined(foundIndex)) throw new VariableNotFound();
        Integer lockTableVal = lockTable.lookup(foundIndex);
        if (lockTableVal == -1) {
            Lock l = new ReentrantLock();
            l.lock();
            try {
                lockTable.update(foundIndex, state.getId());
            } finally {
                l.unlock();
            }

        } else
            stack.push(this);
        return null;
    }

    @Override
    public String toString() {
        return "lock( " + var + ")";
    }
}
