using System;
namespace ToyLanguage.Model
{
    public class PrintStmt : IStmt
    {
        private Exp exp;

        public PrintStmt(Exp v)
        {
            this.exp = v;
        }
        public PrgState execute(PrgState state)
        {
            IList<int> output = state.getOutput();
            IDict<String, int> symTbl = state.getSymTbl();
            int val = exp.eval(symTbl);
            output.add(val);
            return null;
        }
        public override string ToString()
        {
            return "print(" + exp.ToString() + ")";
        }
    }
}
