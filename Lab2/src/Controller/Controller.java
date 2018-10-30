package Controller;

import Exceptions.*;
import Model.*;
import Repository.IRepository;

public class Controller {
    private IRepository repo;

    public Controller(IRepository e) {
        this.repo = e;
    }

    private void oneStep(PrgState state) throws MyStmtExecException, DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIStack<IStmt> stk = state.getStk();
        if (stk.isEmpty()) {
            throw new MyStmtExecException();
        }
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
    }

    public void allStep() throws MyStmtExecException, DivisionByZero, VariableNotFound, OperatorNotFound {
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type // MyRepoInterface

        System.out.println(prg);
        while (!prg.getStk().isEmpty()) {
            oneStep(prg);
            System.out.println(prg);
        }
    }
}
