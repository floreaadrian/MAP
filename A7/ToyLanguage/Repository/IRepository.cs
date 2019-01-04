using System;
using ToyLanguage.Model;

namespace ToyLanguage.Repository
{
    public interface IRepository
    {
        PrgState getCrtPrg();
        void logPrgStateExec();
        String getFilePath();

        void reset();
    }
}
