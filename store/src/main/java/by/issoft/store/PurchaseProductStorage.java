package by.issoft.store;

import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class PurchaseProductStorage {

    private final List<Product> purchasedProductList = new ArrayList<>();

    private PurchaseProductStorage() {
    }
    private static class SingletonHelper {

        private static final PurchaseProductStorage PURCHASED_PRODUCTS_STORAGE = new PurchaseProductStorage();
    }

    public static PurchaseProductStorage getInstance() {
        return SingletonHelper.PURCHASED_PRODUCTS_STORAGE;
    }

    public synchronized void addPurchasedProduct(Product product) {
        purchasedProductList.add(product);
    }

    public synchronized void clearPurchasedProductList() {
        purchasedProductList.clear();
    }

    public void printPurchasedProducts() {
        System.out.println("_____________________________________");
        System.out.println("CART:");
        for (int i = 1; i <= purchasedProductList.size(); i++) {
            System.out.println(i + ": " + purchasedProductList.get(i - 1));
        }
        System.out.println("_____________________________________");
        System.out.println(purchasedProductList.size() + " Products are in the cart");
        System.out.println("_____________________________________");
    }
}
