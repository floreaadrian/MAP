package Model;

import Exceptions.*;

import java.io.*;

public class OpenRFileStmt implements IStmt {
    private String var_file_id;
    private String filename;
    private Generator generator;

    public OpenRFileStmt(String var_file_id, String filename, Generator generator) {
        this.var_file_id = var_file_id;
        this.filename = filename;
        this.generator = generator;
    }

    @Override
    public PrgState execute(PrgState state) throws FileAlreadyUsed, FileDoesntExist, FileNotFoundException {
        MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable = state.getFileTable();
        for (ITuple<String, BufferedReader> act : fileTable.values())
            if (act.getFirst().equals(this.filename))
                throw new FileAlreadyUsed();
        File f = new File(this.filename);
        if (!f.exists())
            throw new FileDoesntExist();
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename));
        int uniqueRandomNumber = this.generator.getGeneratedNumber();
        fileTable.add(uniqueRandomNumber, new Tuple<>(this.filename, bufferedReader));
        symTbl.add(var_file_id,uniqueRandomNumber);
        return state;
    }

    @Override
    public String toString(){
        return "openRFile("+this.var_file_id+",\""+this.filename + "\")";
    }
}
