package by.issoft.consoleApp;

import XMLparser.XMLParser;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;

import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(store);
        populator.fillStoreRandomly();
        store.printAllCategoriesAndProducts();
        System.out.println(XMLParser.configMap());
        Map<String, String> configMap = XMLParser.configMap();
    }

}
