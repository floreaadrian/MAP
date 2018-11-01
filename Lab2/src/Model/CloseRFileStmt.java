package Model;

import Exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt{
    private Exp exp_file_id;
    public CloseRFileStmt(Exp exp){
        this.exp_file_id = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileNotOpened, IOException {
        MyIDictionary<String,Integer> symTable = state.getSymTable();
        int file_id = this.exp_file_id.eval(symTable);
        ITuple<String, BufferedReader> strBuffer = state.getFileTable().lookup(file_id);
        if(strBuffer == null)
            throw new FileNotOpened();
        strBuffer.getSecond().close();
//        symTable.delete(this.exp_file_id.toString());
        state.getFileTable().delete(file_id);
        return state;
    }
    public String toString(){
        return "closeRFile("+this.exp_file_id.toString() + ")";
    }
}
