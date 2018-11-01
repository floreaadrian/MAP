package Controller;

import Exceptions.*;
import Model.*;
import Repository.IRepository;

import java.io.IOException;


public class Controller {
    private IRepository repo;

    public Controller(IRepository e) {
        this.repo = e;
    }

    private void oneStep(PrgState state) throws MyStmtExecException, DivisionByZero, VariableNotFound, OperatorNotFound, IOException, FileDoesntExist, FileAlreadyUsed, FileNotOpened {
        MyIStack<IStmt> stk = state.getStk();
        if (stk.isEmpty()) {
            throw new MyStmtExecException();
        }
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
    }
    public void reset(){
        this.repo.reset();
    }
    public void allStep() throws MyStmtExecException, DivisionByZero, VariableNotFound, OperatorNotFound, IOException, FileDoesntExist, FileAlreadyUsed, FileNotOpened {
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type // MyRepoInterface
        this.repo.logPrgStateExec();
        while (!prg.getStk().isEmpty()) {
            oneStep(prg);
            this.repo.logPrgStateExec();
//            System.out.println(prg);
        }
    }
    public String getFilePath(){
        return this.repo.getFilePath();
    }
}
