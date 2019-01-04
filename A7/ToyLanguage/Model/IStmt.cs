using System;
namespace ToyLanguage.Model
{
    public interface IStmt
    {
        PrgState execute(PrgState state);
    }
}
