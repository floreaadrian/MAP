package Model;

import Exceptions.*;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(VarExp v) {
        this.exp = v;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIList<Integer> out = state.getOut();
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        int val = exp.eval(symTbl);
        out.add(val);
        return state;
    }
}