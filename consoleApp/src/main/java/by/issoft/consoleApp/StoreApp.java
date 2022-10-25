package by.issoft.consoleApp;

import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(store);
        populator.fillStoreRandomly();
        store.sortStore();
        store.printAllCategoriesAndProducts();
        store.getTop5();


    }

}
