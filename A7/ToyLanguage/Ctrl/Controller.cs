using System;
using ToyLanguage.Exceptions;
using ToyLanguage.Model;
using ToyLanguage.Repository;

namespace ToyLanguage.Ctrl
{
    public class Controller
    {
        private IRepository repo;
        public Controller(IRepository repo)
        {
            this.repo = repo;
        }
        private void oneStep(PrgState state)
        {
            IStack<IStmt> stk = state.getStack();
            if (stk.isEmpty())
            {
                throw new MyStmtExecException();
            }
            IStmt crtStmt = stk.pop();
            crtStmt.execute(state);
        }
        public void allStep() {
            PrgState prg = repo.getCrtPrg();
            Console.WriteLine(prg);
            this.repo.logPrgStateExec();
            while (!prg.getStack().isEmpty()) {
                try
                {
                    oneStep(prg);
                }catch(Exception a)
                {
                    Console.WriteLine(a.GetType()+" "+a.Message);
                }
                Console.WriteLine(prg);
                this.repo.logPrgStateExec();
            }
        }
        public String getFilePath()
        {
            return this.repo.getFilePath();
        }
    }
}
