package Model;

import Controller.Controller;
import Exceptions.*;

public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctrl = ctr;
    }

    @Override
    public void execute() {
        try {
            ctrl.allStep();
        } catch (MyStmtExecException | DivisionByZero | VariableNotFound | OperatorNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}