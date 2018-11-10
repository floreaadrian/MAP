package Model;

import Controller.Controller;
import Exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctrl = ctr;
    }

    @Override
    public void execute() throws FileNotFoundException {
        String filePath = ctrl.getFilePath();
        PrintWriter writer = new PrintWriter(filePath);
        writer.print("");
        writer.close();

        try {
            ctrl.reset();
            ctrl.allStep();
        } catch (MyStmtExecException | FileAlreadyUsed | FileNotOpened | FileDoesntExist | DivisionByZero | VariableNotFound | OperatorNotFound e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}