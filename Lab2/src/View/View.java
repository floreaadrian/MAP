package View;

import Controller.Controller;
import Exceptions.*;
import Model.*;
import Repository.*;



public class View {
    public static void main(String[] argc) {
        MyIStack<IStmt> myStack1 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary1 = new MyDictionary<>();
        MyIList<Integer> myList1 = new MyList<>();
        //EX 1:
        //v=2;Print(v)
        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                VarExp("v")));
        PrgState state1 = new PrgState(myStack1, myDictionary1, myList1, ex1);
        IRepository repo1 = new Repository(state1);
        Controller ctr1 = new Controller(repo1);
        TextMenu menu = new TextMenu();
        MyIStack<IStmt> myStack2 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary2 = new MyDictionary<>();
        MyIList<Integer> myList2 = new MyList<>();
        IStmt ex2 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(2),
                        new ArithExp("*", new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));
        PrgState state2 = new PrgState(myStack2, myDictionary2, myList2, ex2);
        IRepository repo2 = new Repository(state2);
        Controller ctr2 = new Controller(repo2);
        MyIStack<IStmt> myStack3 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary3 = new MyDictionary<>();
        MyIList<Integer> myList3 = new MyList<>();
        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp("-", new ConstExp(2), new
                ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        PrgState state3 = new PrgState(myStack3, myDictionary3, myList3, ex3);
        IRepository repo3 = new Repository(state3);
        Controller ctr3 = new Controller(repo3);
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.show();
    }
}
