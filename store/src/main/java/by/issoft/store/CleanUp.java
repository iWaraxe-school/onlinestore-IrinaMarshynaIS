package by.issoft.store;

import java.util.concurrent.TimeUnit;

public class CleanUp implements Runnable {

    private final PurchaseProductStorage purchaseProductStorage = PurchaseProductStorage.getInstance();

    public void run() {

        while (true) {

            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread name: " + Thread.currentThread().getName());
            purchaseProductStorage.clearPurchasedProductList();
            System.out.println("______Order list was cleared______");
            purchaseProductStorage.printPurchasedProducts();
            //Clean Up the List
        }
    }
}
