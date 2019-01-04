using System;
using System.IO;
using ToyLanguage.Exceptions;

namespace ToyLanguage.Model
{
    public class ReadFileStmt : IStmt
    {
        private Exp exp_file_id;
        private String var_name;

        public ReadFileStmt(Exp exp, String var_name)
        {
            this.exp_file_id = exp;
            this.var_name = var_name;
        }

        public PrgState execute(PrgState state) {
            IDict<String, int> symTable = state.getSymTbl();
            int file_id = this.exp_file_id.eval(symTable);
            MyTuple<String, TextReader> fileTable = state.getFileTable().lookup(file_id);
            if (fileTable == null)
                throw new FileNotOpened();
            String line = fileTable.getSecond().ReadLine();
            int value;
            Console.WriteLine("\n\nstart"+line+"aici\n\n");
            if (line == null)
                value = 0;
            else
                value = Convert.ToInt32(line);
            if (symTable.isDefined(var_name))
                symTable.update(var_name, value);
            else
                symTable.add(var_name, value);
            return state;
        }

        public override string ToString()
        {
            return "readFile(" + exp_file_id.ToString() + "," + var_name + ")";
        }
    }
}
