package Model;

import java.io.*;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable;
    private MyIList<Integer> out;
    private MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable;
    private IStmt originalProgramState;
    private int id;

    public void reset() {
        this.exeStack.clear();
        this.symTable.clear();
        this.out.clear();
        this.fileTable.clear();
        this.exeStack.push(this.originalProgramState);
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable, IStmt prg, int id) {
        this.exeStack = stk;
        this.symTable = symtbl;
        this.out = ot;
        this.id = id;
        this.originalProgramState = prg;
        this.fileTable = fileTable;
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

    MyIDictionary<Integer, ITuple<String, BufferedReader>> getFileTable() {
        return this.fileTable;
    }


    @Override
    public String toString() {
        return "Id: " + this.id + "\nExeStack:\n" + this.exeStack.toString() + "SymTable:\n" + this.symTable.toString() + "Out:\n"
                + this.out.toString() + "\nFile Table:\n" + this.fileTable.toString() + "\n";
    }
}