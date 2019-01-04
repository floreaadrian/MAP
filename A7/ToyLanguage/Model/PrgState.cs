using System;
using System.IO;
namespace ToyLanguage.Model
{
    public class PrgState
    {
        private IStack<IStmt> exeStack;
        private IDict<String, int> symTable;
        private IList<int> output;
        private IDictRandIntKey<MyTuple<String, TextReader>> fileTable;
        private IStmt originalProgramState;
        public PrgState(IStack<IStmt> stk, IDict<String, int> symtbl, IList<int> output, IDictRandIntKey<MyTuple<String, TextReader>> fileTable, IStmt stmt)
        {
            this.exeStack = stk;
            this.symTable = symtbl;
            this.output = output;
            this.fileTable = fileTable;
            this.originalProgramState = stmt;
            this.exeStack.push(stmt);
        }
        public void reset()
        {
            this.exeStack.clear();
            this.symTable.clear();
            this.output.clear();
            this.fileTable.clear();
            this.exeStack.push(this.originalProgramState);
        }

        public IStack<IStmt> getStack() { return this.exeStack; }
        public IDict<String, int> getSymTbl() { return this.symTable; }
        public IList<int> getOutput() { return this.output; }
        public IDictRandIntKey<MyTuple<String, TextReader>> getFileTable() { return this.fileTable; }
        public override string ToString()
        {
            return "Stack:\n" + this.exeStack.ToString() + "\nSymbol Table:\n" 
                                    + this.symTable.ToString() + "Output:\n"
                                    + this.output.ToString() + "File Table:\n"
                                    + this.fileTable.ToString() + new String('-',20);
        }
    }
}
