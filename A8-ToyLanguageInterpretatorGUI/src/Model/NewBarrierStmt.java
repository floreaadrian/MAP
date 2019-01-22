package Model;

import Exceptions.DivisionByZero;
import Exceptions.OperatorNotFound;
import Exceptions.VariableNotFound;

public class NewBarrierStmt implements IStmt {
    private String var;
    private Exp exp;

    public NewBarrierStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> heap = state.getHeap();
        MyIRandIntKeyDict<ITuple<Integer, MyIList<Integer>>> barrierTable = state.getBarrierTable();
        Integer expResult = exp.eval(symTbl, heap);
        synchronized (state) {
            Integer newfreelocation = barrierTable.add(new Tuple<>(
                    expResult,
                    new MyList<>()
            ));
            if (symTbl.isDefined(var))
                symTbl.add(var, newfreelocation);
            else
                symTbl.update(var, newfreelocation);
        }
        return null;
    }

    @Override
    public String toString() {
        return "newBarrier(" + this.var + "," + this.exp.toString() + ")";
    }
}
