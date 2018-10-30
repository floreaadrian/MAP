package Model;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable;
    private MyIList<Integer> out;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, IStmt prg) {
        this.exeStack = stk;
        this.symTable = symtbl;
        this.out = ot;
        stk.push(prg);
    }

    public MyIStack<IStmt> getStk() {
        return this.exeStack;
    }

    MyIDictionary<String, Integer> getSymTable() {
        return this.symTable;
    }

    MyIList<Integer> getOut() {
        return this.out;
    }

    @Override
    public String toString() {
        return "Stack: " + this.exeStack.toString() + "\nSymTable: " + this.symTable.toString() + "\nOutput: "
                + this.out.toString() + "\n";
    }
}