package View;

import Controller.Controller;
import Model.*;
import Repository.*;

import java.io.BufferedReader;


public class View {
    public static void main(String[] argc) {
        Generator generator = new Generator();
        String logPath = "/Users/adrianflorea/Codes/Java/Lab2/log.txt";
        MyIStack<IStmt> myStack1 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary1 = new MyDictionary<>();
        MyIList<Integer> myList1 = new MyList<>();
        MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable1 = new MyDictionary<>();
        //EX 1:
        //v=2;Print(v)
        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                VarExp("v")));
        PrgState state1 = new PrgState(myStack1, myDictionary1, myList1, fileTable1, ex1, 1);
        IRepository repo1 = new Repository(state1, logPath);
        Controller ctr1 = new Controller(repo1);
        TextMenu menu = new TextMenu();
        MyIStack<IStmt> myStack2 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary2 = new MyDictionary<>();
        MyIList<Integer> myList2 = new MyList<>();
        MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable2 = new MyDictionary<>();
        IStmt ex2 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(2),
                        new ArithExp("*", new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));
        PrgState state2 = new PrgState(myStack2, myDictionary2, myList2, fileTable2, ex2, 2);
        IRepository repo2 = new Repository(state2, logPath);
        Controller ctr2 = new Controller(repo2);
        MyIStack<IStmt> myStack3 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary3 = new MyDictionary<>();
        MyIList<Integer> myList3 = new MyList<>();
        MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable3 = new MyDictionary<>();
        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp("-", new ConstExp(2), new
                ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));

        PrgState state3 = new PrgState(myStack3, myDictionary3, myList3, fileTable3, ex3, 3);
        IRepository repo3 = new Repository(state3, logPath);
        Controller ctr3 = new Controller(repo3);
        /*openRFile(var_f,"test.in");
        readFile(var_f,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)*/
        MyIStack<IStmt> myStack4 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary4 = new MyDictionary<>();
        MyIList<Integer> myList4 = new MyList<>();
        MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable4 = new MyDictionary<>();
        IStmt ex4 = new CompStmt(
                new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/Java/Lab2/test.in", generator),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));
        PrgState state4 = new PrgState(myStack4, myDictionary4, myList4, fileTable4, ex4, 4);
        IRepository repo4 = new Repository(state4, logPath);
        Controller ctr4 = new Controller(repo4);
        MyIStack<IStmt> myStack5 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary5 = new MyDictionary<>();
        MyIList<Integer> myList5 = new MyList<>();
        MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable5 = new MyDictionary<>();
        IStmt ex5 = new CompStmt(
                new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/Java/Lab2/test.in", generator),
                new CompStmt(new ReadFileStmt(new VarExp("var_f+2"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));
        PrgState state5 = new PrgState(myStack5, myDictionary5, myList5, fileTable5, ex5, 5);
        IRepository repo5 = new Repository(state5, logPath);
        Controller ctr5 = new Controller(repo5);
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.show();
    }
}
