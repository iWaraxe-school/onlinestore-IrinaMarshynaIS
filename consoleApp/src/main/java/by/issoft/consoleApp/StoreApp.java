package by.issoft.consoleApp;

import by.issoft.store.CleanUp;
import by.issoft.store.RandomStorePopulatorFactory;
import by.issoft.store.CreateOrder;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreApp {
    public static void main(String[] args) {

        Store store = Store.getInstance();
        RandomStorePopulatorFactory populator = new RandomStorePopulatorFactory(store);
        populator.fillStoreRandomly();
        store.printAllCategoriesAndProducts();
        Handler_Top5 top5 = new Handler_Top5();

        Runnable clearOrders = new CleanUp();
        new Thread(clearOrders).start();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("________________________________________________________________________________________");
                System.out.println("---- Enter Top5, Sort, Quit or Order ----");
                String input = reader.readLine();
                top5.handleRequest(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
