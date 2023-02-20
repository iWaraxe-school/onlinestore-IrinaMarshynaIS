package by.issoft.consoleApp;

import by.issoft.store.Store;

import java.util.logging.LogRecord;

public class Handler_Sort extends Handler_Base {

    public Handler_Sort() {
        super(new Handler_Order());
    }
    Store store = Store.getInstance();

    @Override
    public void handleRequest(String input) {
        if (input.equals("Sort")) {
            System.out.println("Sort was chosen");
            store.sortStore();
        } else if (getNext() != null) {
            getNext().handleRequest(input);

        }
    }

}
