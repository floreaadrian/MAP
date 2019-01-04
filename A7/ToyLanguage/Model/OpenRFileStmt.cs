using System;
using System.IO;
using ToyLanguage.Exceptions;

namespace ToyLanguage.Model
{
    public class OpenRFileStmt : IStmt
    {
        private String var_file_id;
        private String filename;

        public OpenRFileStmt(String var_file_id, String filename)
        {
            this.var_file_id = var_file_id;
            this.filename = filename;
        }

        public PrgState execute(PrgState state) {
            IDictRandIntKey<MyTuple<String, TextReader>> fileTable = state.getFileTable();
            foreach (MyTuple<String, TextReader> act in fileTable.values())
                if (act.getFirst() == this.filename)
                    throw new FileAlreadyUsed();
            if (!File.Exists(this.filename))
                throw new FileDoesntExist();
            IDict<String, int> symTbl = state.getSymTbl();
            FileStream fs = File.OpenRead(this.filename);
            TextReader bufferedReader = new StreamReader(fs);
            int uniqueRandomNumber = fileTable.add(new MyTuple<String, TextReader>(this.filename, bufferedReader));
            symTbl.add(var_file_id, uniqueRandomNumber);
            return state;
        }

        public override string ToString()
        {
            return "openRFile(" + this.var_file_id + ",\"" + this.filename + "\")";
        }
    }
}
