package by.issoft.store;

import by.issoft.domain.Category;

import java.sql.*;
import java.util.Random;
import java.util.Set;

import static by.issoft.store.RandomStorePopulator.createCategorySet;

public class DBHelper {

    static final String URL = "jdbc:mysql://localhost:3306/onlinestore";
    static final String USER = "username";
    static final String PASSWORD = "2wsx@WSX";

    Statement statement = null;
    static ResultSet RESULTSET = null;

    Store store;

    public DBHelper(Store store) {
        this.store = store;
    }

    public void dBinit() {
        connectToDB();
        createCategoryTable();
        createProductTable();
        //clearData();
        fillStoreRandomly();
        printFilledStore();
        //dropAllTables();

    }

    public void connectToDB() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Database connection successful");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public void createCategoryTable() {
        String query = "CREATE TABLE IF NOT EXISTS CATEGORIES (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "NAME VARCHAR(255) NOT NULL);";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("!!!!!Category table hasn't been created");
        }
    }

    public void createProductTable() {
        String query = "CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "CATEGORY_ID INT NOT NULL," +
                "NAME VARCHAR(255) NOT NULL," +
                "RATE DECIMAL(10, 1) NOT NULL," +
                "PRICE DECIMAL(10, 2) NOT NULL," +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("!!!!!Product table hasn't been created");;
        }
    }

    public void fillStoreRandomly() {
        RandomProductGenerator generator = new RandomProductGenerator();
        Set<Category> categorySet = createCategorySet();

        int j = 1;
        for (Category category : categorySet) {
            System.out.println("Insert category " + category.getName() + " into database");

            try (Connection connection = getConnection()) {
                try (PreparedStatement insertCategories = connection.prepareStatement("INSERT INTO CATEGORIES(NAME) VALUES(?)")) {
                    insertCategories.setString(1, category.getName().toString());
                    System.out.println(insertCategories);
                    insertCategories.execute();
                }

                Random randomProductAmountToAdd = new Random();
                for (int i = 0; i < randomProductAmountToAdd.nextInt(10) + 1; i++) {
                    try (PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO PRODUCTS(category_id, name, rate, price) VALUES(?, ?, ?, ?)")) {
                        insertProduct.setInt(1, j);
                        insertProduct.setString(2, generator.generateName(category.getName()));
                        insertProduct.setDouble(3, generator.generateRate());
                        insertProduct.setDouble(4, generator.generatePrice());
                        System.out.println(insertProduct);
                        insertProduct.execute();
                        System.out.println("One more product inserted");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            j++;
        }
    }

    public void printFilledStore() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println("*******************");
            System.out.println("Print Store from DB");
            System.out.println("*******************");
            try (ResultSet RESULTSET =statement.executeQuery("SELECT * FROM CATEGORIES")){
                System.out.println("---------------List of Categories---------------");
                while (RESULTSET.next()) {
                    System.out.println(
                            RESULTSET.getInt("ID") + ", " +
                                    RESULTSET.getString("NAME"));
                }
            }

            try (ResultSet RESULTSET =statement.executeQuery("SELECT * FROM PRODUCTS")){
                System.out.println("---------------List of Products---------------");
                while (RESULTSET.next()) {
                    System.out.println(
                            RESULTSET.getInt("CATEGORY_ID") + ", " +
                                    RESULTSET.getString("NAME") + ", " +
                                    RESULTSET.getDouble("PRICE") + ", " +
                                    RESULTSET.getDouble("RATE"));
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public void dropAllTables() {
        String query = "DROP TABLE IF EXISTS CATEGORIES;";
        String query2 = "DROP TABLE IF EXISTS PRODUCTS;";
        try {
            statement.executeUpdate(query);
            statement.executeUpdate(query2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearData() {
        String query = "DELETE FROM PRODUCTS";
        String query2 = "DELETE FROM CATEGORIES";
        try {
            statement.executeUpdate(query);
            statement.executeUpdate(query2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
