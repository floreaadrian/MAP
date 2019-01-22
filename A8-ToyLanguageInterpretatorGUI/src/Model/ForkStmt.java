package Model;

import java.util.Random;

public class ForkStmt implements IStmt {
    private IStmt forkStmt;
    private Generator gen;

    public ForkStmt(IStmt forkStmt, Generator gen) {
        this.forkStmt = forkStmt;
        this.gen = gen;
    }

    @Override
    public String toString() {
        return "fork(" + this.forkStmt + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        return new PrgState(new MyStack<>(), state.getSymTable().clone(), state.getOut(),
                state.getFileTable(), state.getHeap(), state.getLockTable(),
                state.getBarrierTable(), this.forkStmt,
                state.getId() * 10 * gen.getGeneratedNumber());
    }
}
