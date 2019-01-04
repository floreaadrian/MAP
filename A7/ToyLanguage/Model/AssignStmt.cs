using System;
namespace ToyLanguage.Model
{
    public class AssignStmt : IStmt
    {
        private String id;
        private Exp exp;

        public AssignStmt(String id, Exp exp)
        {
            this.id = id;
            this.exp = exp;
        }

        public PrgState execute(PrgState state)
        {
            IDict<String, int> symTbl = state.getSymTbl();
            int val = exp.eval(symTbl);
            if (symTbl.isDefined(id))
            {
                symTbl.update(id, val);
            }
            else
            {
                symTbl.add(id, val);
            }
            return null;
        }
        public override string ToString()
        {
            return id + "=" + exp.ToString();
        }

    }
}
