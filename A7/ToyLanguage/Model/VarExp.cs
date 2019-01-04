using System;
using ToyLanguage.Exceptions;

namespace ToyLanguage.Model
{
    public class VarExp : Exp
    {
        private String id;

        public VarExp(String id)
        {
            this.id = id;
        }
        public override int eval(IDict<String, int> dict)
        {
            if (!dict.isDefined(this.id)) throw new VariableNotFound();
            return dict.lookup(this.id);
        }
        public override string ToString()
        {
            return this.id; 
        }
    }
}
