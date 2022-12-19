package by.issoft.consoleApp;

import java.util.logging.LogRecord;

public class Handler_Quit extends Handler_Base {
    public Handler_Quit() {
        super(null);
    }

    @Override
    public void handleRequest(String input) {
        if (input.equals("Quit")) {
            System.out.println("Quit was chosen");
            System.exit(0);
        } else if (getNext() == null) {
            System.out.println("Wrong command");
        }
    }
}
