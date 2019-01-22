package Model;

import Exceptions.BarrieDosentExist;
import Exceptions.VariableNotFound;

public class AwaitStmt implements IStmt {
    private String var;

    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableNotFound, BarrieDosentExist {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<ITuple<Integer, MyIList<Integer>>> barrierTable = state.getBarrierTable();
        MyIStack<IStmt> stack = state.getStk();
        if (!symTbl.isDefined(var)) throw new VariableNotFound();
        Integer foundIndex = symTbl.lookup(var);
        if (!barrierTable.isDefined(foundIndex)) throw new BarrieDosentExist();
        synchronized (state) {
            ITuple<Integer, MyIList<Integer>> barrierInfo = barrierTable.lookup(foundIndex);
            Integer n1 = barrierInfo.getFirst();
            MyIList<Integer> list1 = barrierInfo.getSecond();
            Integer nl = list1.size();
            if (n1 > nl)
                if (list1.contain(state.getId()))
                    stack.push(this);
                else {
                    barrierInfo.getSecond().add(state.getId());
                    stack.push(this);
                }
        }
        return null;
    }

    @Override
    public String toString() {
        return "await(" + this.var + ")";
    }
}
