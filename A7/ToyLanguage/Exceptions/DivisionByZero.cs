using System;
namespace ToyLanguage.Exceptions
{
    public class DivisionByZero : Exception
    {
        public DivisionByZero()
        : base("Division by zero")
        {
        }
    }
}
