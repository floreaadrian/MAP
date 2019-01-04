using System;
namespace ToyLanguage.Exceptions
{
    public class OperatorNotFound : Exception
    {
        public OperatorNotFound()
        : base("Operator was not found")
        {
        }
    }
}
