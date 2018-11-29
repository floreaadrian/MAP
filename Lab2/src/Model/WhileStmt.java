package Model;

import Exceptions.*;

public class WhileStmt implements IStmt {
    private Exp condition;
    private IStmt statements;

    public WhileStmt(Exp condition, IStmt statements) {
        this.condition = condition;
        this.statements = statements;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIDictionary<Integer, Integer> heap = state.getHeap();
        MyIStack<IStmt> stack = state.getStk();
        if (this.condition.eval(symTbl, heap) != 0) {
            stack.push(this);
            stack.push(statements);
            stack.push(statements);
        }
        return state;
    }

    @Override
    public String toString() {
        return " (while (" + this.condition.toString() + ") " + this.statements.toString() + ")";
    }
}
