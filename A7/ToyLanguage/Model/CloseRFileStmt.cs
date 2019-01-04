using System;
using System.IO;
using ToyLanguage.Exceptions;

namespace ToyLanguage.Model
{
    public class CloseRFileStmt : IStmt
    {
        private Exp exp_file_id;
        public CloseRFileStmt(Exp exp)
        {
            this.exp_file_id = exp;
        }

        public PrgState execute(PrgState state) {
            IDict<String, int> symTable = state.getSymTbl();
            int file_id = this.exp_file_id.eval(symTable);
            MyTuple<String, TextReader> strBuffer = state.getFileTable().lookup(file_id);
            if(strBuffer == null)
                throw new FileNotOpened();
            strBuffer.getSecond().Close();
            state.getFileTable().delete(file_id);
            return state;
        }
        public override string ToString()
        {
            return "closeRFile(" + this.exp_file_id.ToString() + ")";
        }
}
}
