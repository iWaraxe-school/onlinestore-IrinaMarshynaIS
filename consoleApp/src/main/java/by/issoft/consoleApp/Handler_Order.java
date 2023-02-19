package by.issoft.consoleApp;

import by.issoft.store.CreateOrder;
import by.issoft.store.Store;

public class Handler_Order extends Handler_Base {

    public Handler_Order() {
        super(new Handler_Quit());
    }

    Store store = Store.getInstance();

    @Override
    public void handleRequest(String input) {
        if (input.equals("Order")) {
            System.out.println("Order was chosen");

            Thread thread1 = new Thread(new CreateOrder());
            thread1.start();

        } else if (getNext() != null) {
            getNext().handleRequest(input);

        }
    }
}
