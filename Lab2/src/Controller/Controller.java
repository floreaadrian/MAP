package Controller;

import Exceptions.*;
import Model.*;
import Repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


public class Controller {
    private IRepository repo;

    public Controller(IRepository e) {
        this.repo = e;
    }

    private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                              Map<Integer, Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

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
            prg.getHeap().setContent(conservativeGarbageCollector(
                    prg.getSymTable().getContent().values(),
                    prg.getHeap().getContent()));
            this.repo.logPrgStateExec();
//            System.out.println(prg);
        }
    }
    public String getFilePath(){
        return this.repo.getFilePath();
    }
}
