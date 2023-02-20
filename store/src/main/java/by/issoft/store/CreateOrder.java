package by.issoft.store;

import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CreateOrder implements Runnable {

    private final Store store = Store.getInstance();
    private final PurchaseProductStorage purchaseProductStorage = PurchaseProductStorage.getInstance();

    public void run() {
        //Order a product
        System.out.println("Thread name: " + Thread.currentThread().getName());
        Product purchasedProduct = store.getRandomProductFromStore();
        System.out.println("Ordered product: " + purchasedProduct);
        purchaseProductStorage.addPurchasedProduct(purchasedProduct);
        purchaseProductStorage.printPurchasedProducts();

        int randomNum = (int) (Math.random() * 31);
        try {
            TimeUnit.SECONDS.sleep(randomNum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " has been finished");
    }
}
