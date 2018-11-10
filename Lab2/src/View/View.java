package View;

import Controller.Controller;
import Model.*;
import Repository.*;

import java.io.BufferedReader;


public class View {

    private static Controller createController(IStmt statement, int nrOfCtrl) {
        String logPath = "/Users/adrianflorea/Codes/Java/Lab2/log.txt";
        MyIStack<IStmt> myStack1 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary1 = new MyDictionary<>();
        MyIList<Integer> myList1 = new MyList<>();
        MyIDictionary<Integer, ITuple<String, BufferedReader>> fileTable1 = new MyDictionary<>();
        MyIDictionary<Integer, Integer> heap = new MyDictionary<>();
        PrgState state1 = new PrgState(myStack1, myDictionary1, myList1, fileTable1, heap, statement, nrOfCtrl);
        IRepository repo1 = new Repository(state1, logPath);
        return new Controller(repo1);
    }

    public static void main(String[] argc) {
        Generator generator = new Generator();
        TextMenu menu = new TextMenu();
        //EX 1:
        //v=2;Print(v)
        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                VarExp("v")));
        Controller ctr1 = createController(ex1, 1);

        IStmt ex2 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(2),
                        new ArithExp("*", new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));
        Controller ctr2 = createController(ex2, 2);

        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp("-", new ConstExp(2), new
                ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        Controller ctr3 = createController(ex3, 3);
        /*openRFile(var_f,"test.in");
        readFile(var_f,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)*/
        IStmt ex4 = new CompStmt(
                new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/Java/Lab2/test.in", generator),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));
        Controller ctr4 = createController(ex4, 4);

        /*
        openRFile(var_f,"test.in");
        readFile(var_f+2,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)
         */
        IStmt ex5 = new CompStmt(
                new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/Java/Lab2/test.in", generator),
                new CompStmt(new ReadFileStmt(new VarExp("var_f+2"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));
        Controller ctr5 = createController(ex5, 5);

        //v=10;new(v,20);new(a,22);print(v
        IStmt ex6 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20), generator),
                        new CompStmt(new NewStmt("a", new ConstExp(22), generator),
                                new PrintStmt(new VarExp("v")))));
        Controller ctr6 = createController(ex6, 6);

        // v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
        IStmt ex7 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20), generator),
                        new CompStmt(new NewStmt("a", new ConstExp(22), generator),
                                new CompStmt(new PrintStmt(new ArithExp("+", new ConstExp(100), new ReadHeapExp("v")))
                                        , new PrintStmt(new ArithExp("+", new ConstExp(100), new ReadHeapExp("a"))))
                        )));
        Controller ctr7 = createController(ex7, 7);

        // v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a))
        IStmt ex8 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20), generator),
                        new CompStmt(new NewStmt("a", new ConstExp(22), generator),
                                new CompStmt(new WriteHeapStmt("a", new ConstExp(30))
                                        , new CompStmt(new PrintStmt(new VarExp("a"))
                                        , new PrintStmt(new ReadHeapExp("a")))))));
        Controller ctr8 = createController(ex8, 8);

        //v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0
        IStmt ex9 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20), generator),
                        new CompStmt(new NewStmt("a", new ConstExp(22), generator),
                                new CompStmt(new WriteHeapStmt("a", new ConstExp(30))
                                        , new CompStmt(new PrintStmt(new VarExp("a"))
                                        , new CompStmt(new PrintStmt(new ReadHeapExp("a")),
                                        new AssignStmt("a", new ConstExp(0))))))));
        Controller ctr9 = createController(ex9, 9);

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
        menu.show();
    }
}