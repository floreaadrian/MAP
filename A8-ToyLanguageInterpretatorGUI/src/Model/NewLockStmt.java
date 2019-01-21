package Model;

import Exceptions.*;

import java.io.IOException;

public class NewLockStmt implements IStmt {
    private String var;

    public NewLockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileAlreadyUsed, FileDoesntExist, IOException, FileNotOpened {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> lockTable = state.getLockTable();
        Integer newfreelocation = lockTable.add(-1);
        if(symTbl.isDefined(var))
            symTbl.add(var,newfreelocation);
        else
            symTbl.update(var,newfreelocation);
        return null;
    }

    @Override
    public String toString() {
        return "newLock( " + var+ ")";
    }

}
