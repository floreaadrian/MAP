using System;
using System.IO;
using ToyLanguage.Model;

namespace ToyLanguage.Repository
{
    public class Repo : IRepository
    {
        private PrgState prgState; 
        private String logFilePath;

        public Repo(PrgState e,String filePath)
        {
            this.prgState = e;
            this.logFilePath = filePath;
        }

        public void reset()
        {
            this.prgState.reset();
        }
        public PrgState getCrtPrg()
        {
            return this.prgState;
        }

        public void logPrgStateExec()
        {

            using (StreamWriter w = File.AppendText(this.logFilePath))
            {
                w.WriteLine(this.prgState.ToString());
            }
        }

        public String getFilePath()
        {
            return this.logFilePath;
        }
    }
}
