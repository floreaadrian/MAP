using System;
namespace ToyLanguage.Model
{
    public class CompStmt : IStmt
    {
        private IStmt first;
        private IStmt snd;

        public CompStmt(IStmt first, IStmt snd)
        {
            this.first = first;
            this.snd = snd;
        }

        public PrgState execute(PrgState state)
        {
            IStack<IStmt> stk = state.getStack();
            stk.push(snd);
            stk.push(first);
            return null;
        }
        public override string ToString()
        {
            return "(" + first.ToString() + "; " + snd.ToString() + ") ";
        }

    }
}
