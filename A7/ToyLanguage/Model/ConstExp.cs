using System;
namespace ToyLanguage.Model
{
    public class ConstExp : Exp
    {
        private int number;

        public ConstExp(int value)
        {
            this.number = value;
        }
        public override int eval(IDict<String, int> dict)
        {
            return this.number;
        }
        public override string ToString()
        {
            return this.number.ToString();
        }
    }
}
