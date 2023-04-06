package by.issoft.consoleApp;

import by.issoft.store.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class StoreApp {
    public static void main(String[] args) throws SQLException {
        Store onlineStore = new Store();

        RandomStorePopulator storePopulator = new RandomStorePopulator(onlineStore);
        storePopulator.fillStoreRandomly();
        onlineStore.printAllCategoriesAndProducts();

        DBHelper dbHelper = new DBHelper(onlineStore);
        dbHelper.dBinit();
        //dbHelper.createCategoryTable();
        //dbHelper.createProductTable();
        //dbHelper.fillStoreRandomly();
        //dbHelper.printFilledStore();
        //dbHelper.dropAllTables();
        //dbHelper.clearData();

    }
}
