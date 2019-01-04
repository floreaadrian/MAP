using System;
namespace ToyLanguage.Model
{
    public abstract class Exp
    {
        abstract public int eval(IDict<String, int> dict);
    }
}
