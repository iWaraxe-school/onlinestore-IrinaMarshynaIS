package by.issoft.consoleApp;

import by.issoft.store.*;
import by.issoft.store.http.Client;
import by.issoft.store.http.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class StoreApp {
    public static void main(String[] args) throws SQLException, IOException {
        Store onlineStore = Store.getInstance();
        //RandomStorePopulator storePopulator = new RandomStorePopulator(onlineStore);
        //storePopulator.fillStoreRandomly();
        //onlineStore.printAllCategoriesAndProducts();

        DBHelper dbHelper = new DBHelper(onlineStore);
        //dbHelper.dBinit();
        //dbHelper.getConnection();
        dbHelper.createCategoryTable();
        dbHelper.createProductTable();
        dbHelper.fillStoreRandomly();
        dbHelper.createOrdersTable();
        //dbHelper.printFilledStore();
        //dbHelper.dropAllTables();
        //dbHelper.clearData();

        Server.startServer();
        Client.clientMakesOrder();

    }
}
