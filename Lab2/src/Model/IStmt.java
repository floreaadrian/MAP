package Model;

import Exceptions.DivisionByZero;
import Exceptions.OperatorNotFound;
import Exceptions.VariableNotFound;

public interface IStmt {
    PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound;
}
