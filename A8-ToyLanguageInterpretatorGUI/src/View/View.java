package View;

import Controller.Controller;
import Model.*;
import Repository.*;
import Services.PrgStateService;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class View extends Application {

    private static Repository createRepository(IStmt statement) {
        Generator generator = new Generator();
        String logPath = "/Users/adrianflorea/Codes/Java/Lab2/log.txt";
        MyIStack<IStmt> myStack1 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary1 = new MyDictionary<>();
        MyIList<Integer> myList1 = new MyList<>();
        MyIRandIntKeyDict<ITuple<String, BufferedReader>> fileTable1 = new MyRandIntKeyDict<>(generator);
        MyIRandIntKeyDict<Integer> heap = new MyRandIntKeyDict<>(generator);
        PrgState state = new PrgState(myStack1, myDictionary1, myList1, fileTable1, heap, statement, 1);
        return new Repository(state, logPath);
    }

    @Override
    public void start(Stage primaryStage) {
        //EX 1:
        //v=2;Print(v)
        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                VarExp("v")));

        IStmt ex2 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(2),
                        new ArithExp("*", new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));

        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp("-", new ConstExp(2), new
                ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        /*openRFile(var_f,"test.in");
        readFile(var_f,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)*/
        IStmt ex4 = new CompStmt(
                new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/Java/Lab2/test.in"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));

        /*
        openRFile(var_f,"test.in");
        readFile(var_f+2,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)
         */
        IStmt ex5 = new CompStmt(
                new OpenRFileStmt("var_f", "/Users/adrianflorea/Codes/Java/Lab2/test.in"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f+2"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));

        //v=10;new(v,20);new(a,22);print(v
        IStmt ex6 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new PrintStmt(new VarExp("v")))));

        // v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
        IStmt ex7 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new CompStmt(new PrintStmt(new ArithExp("+", new ConstExp(100), new ReadHeapExp("v")))
                                        , new PrintStmt(new ArithExp("+", new ConstExp(100), new ReadHeapExp("a"))))
                        )));

        // v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a))
        IStmt ex8 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new CompStmt(new WriteHeapStmt("a", new ConstExp(30))
                                        , new CompStmt(new PrintStmt(new VarExp("a"))
                                        , new PrintStmt(new ReadHeapExp("a")))))));

        //v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0
        IStmt ex9 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new CompStmt(new WriteHeapStmt("a", new ConstExp(30))
                                        , new CompStmt(new PrintStmt(new VarExp("a"))
                                        , new CompStmt(new PrintStmt(new ReadHeapExp("a")),
                                        new AssignStmt("a", new ConstExp(0))))))));

        //10 + (2<6) evaluates to 11

        IStmt ex10 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(10), new BoolExp("<", new ConstExp(2),
                        new ConstExp(6)))), new PrintStmt(new VarExp("a")));

        // (10+2)<6
        IStmt ex11 = new PrintStmt(new BoolExp("<",
                new ArithExp("+", new ConstExp(10), new ConstExp(2)), new ConstExp(2)));

        //v=6; (while (v-4) print(v);v=v-1);print(v)
        IStmt ex12 = new CompStmt(new AssignStmt("v", new ConstExp(6)),
                new CompStmt(new WhileStmt(new BoolExp(">", new ArithExp("-", new VarExp("v"), new ConstExp(4)), new ConstExp(0)),
                        new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ConstExp(1))))),
                        new PrintStmt(new VarExp("v")))
        );

        IStmt ex13 = new OpenRFileStmt("var", "/Users/adrianflorea/Codes/Java/Lab2/test.in");

         /*
         v=10;new(a,22);
         fork(wH(a,30);v=32;print(v);print(rH(a)));
         print(v);print(rH(a))
         */
        IStmt forkStmt = new CompStmt(
                new WriteHeapStmt("a", new ConstExp(30)),
                new CompStmt(new AssignStmt("v", new ConstExp(32)),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))
                ));
        IStmt ex14 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("a", new ConstExp(22)), new CompStmt(
                        new ForkStmt(forkStmt), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))
                )));

        IStmt ex15 = new CompStmt(new NewStmt("a", new ConstExp(32)), new AssignStmt("a", new ConstExp(32)));
        //        IStmt ex15 = new CompStmt(new NewStmt("a",new ConstExp(32),generator),new CompStmt(new AssignStmt("a",new ConstExp(32)),new AssignStmt("dfs",new ConstExp())));

        ////
        ////            STARTING THE GUI
        ///
        try {
            List<IStmt> menu = new ArrayList<>();
            menu.add(ex1);
            menu.add(ex2);
            menu.add(ex3);
            menu.add(ex4);
            menu.add(ex5);
            menu.add(ex6);
            menu.add(ex7);
            menu.add(ex8);
            menu.add(ex9);
            menu.add(ex10);
            menu.add(ex11);
            menu.add(ex12);
            menu.add(ex13);
            menu.add(ex14);
            menu.add(ex15);
            VBox root = new VBox(3);
            root.getChildren().add(new Label("Please choose a program: "));

            /// create the listview
            ObservableList<IStmt> observableStmtList = FXCollections.observableArrayList(menu);
            ListView<IStmt> programList = new ListView<>(observableStmtList);
            programList.setCellFactory(new Callback<ListView<IStmt>, ListCell<IStmt>>() {
                @Override
                public ListCell<IStmt> call(ListView<IStmt> param) {
                    ListCell<IStmt> listCell = new ListCell<IStmt>() {
                        @Override
                        protected void updateItem(IStmt e, boolean empty) {
                            super.updateItem(e, empty);
                            if (e == null || empty)
                                setText("");
                            else
                                setText(e.toString());
                        }
                    };
                    return listCell;
                }
            });
            root.getChildren().add(programList);

            Scene scene = new Scene(root, 500, 300);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Examples");
            primaryStage.show();

            programList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IStmt>() {
                @Override
                public void changed(ObservableValue<? extends IStmt> observable, IStmt oldValue, IStmt newValue) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("Menu.fxml"));
                        VBox root = (VBox) loader.load();
                        PrgStateService prgStateService = new PrgStateService(createRepository(newValue));
                        Controller ctrl = loader.getController();

                        ctrl.setService(prgStateService);
                        prgStateService.addObserver(ctrl);

                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Run example dialog");
                        dialogStage.initModality(Modality.APPLICATION_MODAL);
                        dialogStage.setScene(new Scene(root));
                        dialogStage.show();

                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}


//        menu.addCommand(new ExitCommand("0", "exit"));
//        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
//        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
//        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
//        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
//        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
//        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
//        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
//        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
//        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
//        menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
//        menu.addCommand(new RunExample("11", ex11.toString(), ctr11));
//        menu.addCommand(new RunExample("12", ex12.toString(), ctr12));
//        menu.addCommand(new RunExample("13", ex13.toString(), ctr13));
//        menu.addCommand(new RunExample("14", ex14.toString(), ctr14));
//        menu.addCommand(new RunExample("15", ex15.toString(), ctr15));