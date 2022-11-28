package by.issoft.consoleApp;

import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreApp {
    public static void main(String[] args) {

        Store store = Store.getInstance();
        RandomStorePopulator populator = new RandomStorePopulator(store);
        populator.fillStoreRandomly();
        store.printAllCategoriesAndProducts();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Boolean flag = true;
        System.out.println("________________________________________________________________________________________");
        System.out.println("---- Enter Top, Sort or Quit ----");
        while (flag) {
            try {
                String command = reader.readLine();
                switch (command) {
                    case "Top":
                        store.getTop5();
                        break;
                    case "Sort":
                        store.sortStore();
                        break;
                    case "Quit":
                        flag = false;
                        break;
                    default:
                        System.out.println("---- Enter Top, Sort or Quit ----");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
