using System;
using System.IO;
using ToyLanguage.Exceptions;
using ToyLanguage.Model;
using ToyLanguage.Repository;
using ToyLanguage.Ctrl;
using ToyLanguage.View;

namespace ToyLanguage
{
    class MainClass
    {
        private static Controller createController(IStmt statement){
            String logPath = "/Users/adrianflorea/Codes/MAP/A7/log.out";
            IStack <IStmt> myStack1 = new MyStack<IStmt>();
            IDict<String, int> myDictionary1 = new MyDict<String, int>();
            IList<int> myList1 = new MyList<int>();
            IDictRandIntKey<MyTuple<String, TextReader>> myFileTable = new FileTable<MyTuple<String, TextReader> >();
            PrgState state = new PrgState(myStack1, myDictionary1, myList1,myFileTable, statement);
            IRepository repo = new Repo(state,logPath);
            Controller ctrl = new Controller(repo);
            return ctrl;
        }
        public static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            //EX 1:
            //v=2;Print(v)
            IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                    VarExp("v")));

            Controller ctrl1 = createController(ex1);
            IStmt ex2 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(2),
                        new ArithExp("*", new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));
            Controller ctrl2 = createController(ex2);

            IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp("-", new ConstExp(2), new
                    ConstExp(2))),
                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                            AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
            Controller ctrl3 = createController(ex3);
            /*openRFile(var_f,"test.in");
            readFile(var_f,var_c);print(var_c);
            (if var_c then readFile(var_f,var_c);print(var_c)
            else print(0));
            closeRFile(var_f)*/
            IStmt ex4 = new CompStmt(
                    new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/MAP/A7/input.in"),
                    new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new CompStmt(
                            new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                            new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                            new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));
            Controller ctrl4 = createController(ex4);

            /*
            openRFile(var_f,"test.in");
            readFile(var_f+2,var_c);print(var_c);
            (if var_c then readFile(var_f,var_c);print(var_c)
            else print(0));
            closeRFile(var_f)*/
            IStmt ex5 = new CompStmt(
                    new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/MAP/A7/input.in"),
                    new CompStmt(new ReadFileStmt(new VarExp("var_f+2"), "var_c"), new CompStmt(
                            new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                            new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                            new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));
            Controller ctrl5 = createController(ex5);
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExampleCommand("1", ex1.ToString(), ctrl1));
            menu.addCommand(new RunExampleCommand("2", ex2.ToString(), ctrl2));
            menu.addCommand(new RunExampleCommand("3", ex3.ToString(), ctrl3));
            menu.addCommand(new RunExampleCommand("4", ex4.ToString(), ctrl4));
            menu.addCommand(new RunExampleCommand("5", ex5.ToString(), ctrl5));
            menu.show();
        }
    }
}
