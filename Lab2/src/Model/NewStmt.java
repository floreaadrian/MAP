package Model;

import Exceptions.*;

import java.io.IOException;

public class NewStmt implements IStmt {
    private String var_name;
    private Exp expression;
    private Generator generator;

    public NewStmt(String var_name, Exp expression, Generator generator) {
        this.var_name = var_name;
        this.expression = expression;
        this.generator = generator;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIDictionary<Integer, Integer> heap = state.getHeap();
        int evaluated = expression.eval(symTbl, heap);
        int generatedNumber = generator.getGeneratedNumber();
        if (symTbl.isDefined(var_name))
            symTbl.update(var_name, generatedNumber);
        else
            symTbl.add(var_name, generatedNumber);
        heap.add(generatedNumber, evaluated);
        return state;
    }

    @Override
    public String toString() {
        return "newStmt(" + this.var_name + ", " + this.expression.toString() + ")";
    }
}
