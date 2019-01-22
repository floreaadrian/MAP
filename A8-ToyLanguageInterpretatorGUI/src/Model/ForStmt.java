package Model;

import Exceptions.*;

import java.io.IOException;

public class ForStmt implements IStmt {
    private IStmt initStmt;
    private Exp verfExp;
    private IStmt modfExp;
    private IStmt body;

    public ForStmt(IStmt initStmt, Exp verfExp, IStmt modfExp, IStmt body) {
        this.initStmt = initStmt;
        this.verfExp = verfExp;
        this.modfExp = modfExp;
        this.body = body;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileAlreadyUsed,
            FileDoesntExist, IOException, FileNotOpened, BarrieDosentExist {
        this.initStmt.execute(state);
        IStmt whileFor = new WhileStmt(verfExp, new CompStmt(body, modfExp));
        // whileFor.execute(state);
        state.getStk().push(whileFor);
        return null;
    }

    @Override
    public String toString() {
        return "for(" + this.initStmt.toString() + "; " + this.verfExp.toString() + "; " + this.modfExp.toString()
                + ") {\n" + this.body.toString() + "\n}";
    }
}
