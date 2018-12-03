package Model;

import Exceptions.*;

import java.io.*;
import java.util.Collections;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable;
    private MyIList<Integer> out;
    private MyIRandKeyDict<ITuple<String, BufferedReader>> fileTable;
    private MyIRandKeyDict<Integer> heap;
    private IStmt originalProgramState;
    private int id;

    public void reset() {
        this.exeStack.clear();
        this.symTable.clear();
        this.out.clear();
        this.fileTable.clear();
        this.heap.clear();
        this.exeStack.push(this.originalProgramState);
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot,
                    MyIRandKeyDict<ITuple<String, BufferedReader>> fileTable, MyIRandKeyDict<Integer> heap,
                    IStmt prg, int id) {
        this.exeStack = stk;
        this.symTable = symtbl;
        this.out = ot;
        this.id = id;
        this.originalProgramState = prg;
        this.fileTable = fileTable;
        this.heap = heap;
        stk.push(prg);
    }

    MyIStack<IStmt> getStk() {
        return this.exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return this.symTable;
    }

    MyIList<Integer> getOut() {
        return this.out;
    }

    MyIRandKeyDict<ITuple<String, BufferedReader>> getFileTable() {
        return this.fileTable;
    }

    public MyIRandKeyDict<Integer> getHeap() {
        return this.heap;
    }

    int getId() {
        return this.id;
    }

    public boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStep() throws DivisionByZero, OperatorNotFound, FileDoesntExist, VariableNotFound, FileAlreadyUsed, FileNotOpened, IOException, MyStmtExecException {
        if (this.exeStack.isEmpty()) throw new MyStmtExecException();
        IStmt crtStmt = this.exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        return "\nId: " + this.id + "\nExeStack:\n" + this.exeStack.toString() + "SymTable:\n" + this.symTable.toString()
                + "Heap:\n" + this.heap.toString() + "File Table:\n" + this.fileTable.toString()
                + "Out:\n" + this.out.toString() + "\n" + multipleLines() + "\n";
    }

    private String multipleLines() {
        return String.join("", Collections.nCopies(15, "-"));
    }
}