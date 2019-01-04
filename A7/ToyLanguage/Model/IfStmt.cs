using System;
namespace ToyLanguage.Model
{
    public class IfStmt : IStmt
    {
        private Exp condition;
        private IStmt thenS;
        private IStmt elseS;

        public IfStmt(Exp e, IStmt t, IStmt el)
        {
            this.condition = e;
            this.thenS = t;
            this.elseS = el;
        }

        public PrgState execute(PrgState state)
        {
            IStack<IStmt> stk = state.getStack();
            IDict<String, int> symtbl = state.getSymTbl();
            if (this.condition.eval(symtbl) == 0)
            {
                stk.push(elseS);
            }
            else
            {
                stk.push(thenS);
            }
            return null;
        }
        public override string ToString()
        {
            return "IF(" + condition.ToString() + ") THEN (" + thenS.ToString()
                + ") ELSE (" + elseS.ToString() + ")";
        }
    }
}
