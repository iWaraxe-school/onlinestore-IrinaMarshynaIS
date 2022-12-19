package by.issoft.consoleApp;

import by.issoft.store.Store;

import java.util.logging.LogRecord;

public class Handler_Top5 extends Handler_Base {
    public Handler_Top5() {
        super(new Handler_Sort());
    }
    Store store = Store.getInstance();

    @Override
    public void handleRequest(String input) {
        if (input.equals("Top5")) {
            System.out.println("Top5 was chosen");
            store.getTop5();
        } else if (getNext() != null) {
            getNext().handleRequest(input);
        }
    }
}
