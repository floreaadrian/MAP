package Model;

import Exceptions.*;

import java.io.IOException;

///switch(exp) (case exp1: stmt1) (case exp2: stmt2) (default: stmt3)

public class SwitchStmt implements IStmt {
    private Exp exp;
    private Exp exp1;
    private Exp exp2;
    private IStmt stmt1;
    private IStmt stmt2;
    private IStmt stmt3;

    public SwitchStmt(Exp exp, Exp exp1, Exp exp2, IStmt stmt1, IStmt stmt2, IStmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileAlreadyUsed, FileDoesntExist, IOException, FileNotOpened {
        MyIStack<IStmt> stack = state.getStk();
        IStmt twoIfs = new IfStmt(new BoolExp("==", exp, exp1), stmt1,
                new IfStmt(new BoolExp("==", exp, exp2), stmt2, stmt3));
        stack.push(twoIfs);
        return null;
    }

    @Override
    public String toString() {
        return "switch(" + this.exp + ")\n" +
                "(case(" + this.exp1 + ") " + this.stmt1 + " )\n" +
                "(case(" + this.exp2 + ") " + this.stmt2 + " )\n" +
                "(default " + this.stmt3 + " )\n";
    }
}
