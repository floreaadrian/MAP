using System;
namespace ToyLanguage.Exceptions
{
    public class VariableNotFound : Exception
    {
        public VariableNotFound()
        : base("Variable was not found")
        {
        }
    }
}
