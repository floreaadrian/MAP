using System;
using ToyLanguage.Exceptions;

namespace ToyLanguage.Model
{
    public class ArithExp : Exp
    {
        private Exp firstExp;
        private Exp sndExp;
        private String op;

        public ArithExp(String op, Exp e1, Exp e2)
        {
            this.firstExp = e1;
            this.sndExp = e2;
            this.op = op;
        }

        public override int eval(IDict<String, int> dict)
        {
            int finalResult = 0;
            int rez1 = this.firstExp.eval(dict);
            int rez2 = this.sndExp.eval(dict);
            switch (op)
            {
                case "+":
                    finalResult = rez1 + rez2;
                    break;
                case "-":
                    finalResult = rez1 - rez2;
                    break;
                case "*":
                    finalResult = rez1 * rez2;
                    break;
                case "//":
                    if (rez2 == 0) throw new DivisionByZero();
                    finalResult = rez1 / rez2;
                    break;
                default:
                    throw new OperatorNotFound();
            }
            return finalResult;
        }
        public override string ToString()
        {
            return this.firstExp.ToString() + this.op + this.sndExp.ToString();
        }

    }
}