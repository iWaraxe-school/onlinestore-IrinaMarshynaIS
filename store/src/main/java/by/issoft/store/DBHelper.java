package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.XML.XMLParser;


import java.sql.*;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static by.issoft.store.RandomStorePopulator.createCategorySet;

public class DBHelper {

    static final String URL = "jdbc:mysql://localhost:3306/onlinestore";
    static final String USER = "username";
    static final String PASSWORD = "2wsx@WSX";

    Store store = Store.getInstance();

    public DBHelper(Store store) {
        this.store = store;
    }

    public void dBinit() {
        //connectToDB();
        //createCategoryTable();
        //createProductTable();
        //clearData();
        //fillStoreRandomly();
        //printFilledStore();
        //dropAllTables();

    }

    public static Connection getConnection() {
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
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Category Table is created.");
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
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Product Table is created.");
        } catch (SQLException e) {
            System.out.println("!!!!!Product table hasn't been created");
        }
    }

    public void createOrdersTable() {
        String query = "CREATE TABLE IF NOT EXISTS ORDERS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "NAME VARCHAR(255) NOT NULL," +
                "PRICE DECIMAL(10, 2) NOT NULL," +
                "RATE DECIMAL(10, 1) NOT NULL,";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Orders Table is created.");
        } catch (SQLException e) {
            System.out.println("!!!!!Orders table hasn't been created");
        }
    }


    public void fillStoreRandomly() {
        RandomProductGenerator generator = new RandomProductGenerator();
        Set<Category> categorySet = createCategorySet();

        int j = 1;
        for (Category category : categorySet) {
            System.out.println("Insert category " + category.getName() + " into database");

            try (Connection connection = getConnection();
                 PreparedStatement insertCategories = connection.prepareStatement("INSERT INTO CATEGORIES(NAME) VALUES(?)")) {
                insertCategories.setString(1, category.getName().toString());
                System.out.println(insertCategories);
                insertCategories.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Random randomProductAmountToAdd = new Random();
            for (int i = 0; i < randomProductAmountToAdd.nextInt(10) + 1; i++) {
                try (Connection connection = getConnection();
                     PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO PRODUCTS(category_id, name, rate, price) VALUES(?, ?, ?, ?)")) {
                    insertProduct.setInt(1, j);
                    insertProduct.setString(2, generator.generateName(category.getName()));
                    insertProduct.setDouble(3, generator.generateRate());
                    insertProduct.setDouble(4, generator.generatePrice());
                    System.out.println(insertProduct);
                    insertProduct.execute();
                    System.out.println("One more product inserted");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            j++;
        }
    }

    public static void addOrderToDB(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement addProduct = connection.prepareStatement("INSERT INTO ORDERS(NAME, PRICE, RATE) VALUES( ?, ?, ?)")) {
            addProduct.setString(1, product.getName());
            addProduct.setDouble(2, product.getPrice());
            addProduct.setDouble(3, product.getRate());
            addProduct.executeUpdate();
        }
    }

    public static ResultSet getOrderFromDB() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT NAME, PRICE, RATE FROM ORDERS");
        ResultSet listOfOrders = preparedStatement.executeQuery();
        System.out.println("-------------------------------------------------------------");
        System.out.println("                        LIST OF ORDERS");
        System.out.println("-------------------------------------------------------------");

        StringBuilder sb = new StringBuilder();

        while (listOfOrders.next()) {
            Product order = new Product();
            order.setName(listOfOrders.getString(1));
            order.setPrice(listOfOrders.getDouble(2));
            order.setRate(listOfOrders.getDouble(3));
            System.out.println(order);
            sb.append(order.toString());
            sb.append("\n");
        }
        return listOfOrders;
    }

    public static String getProductsWithCategories() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME FROM CATEGORIES");
        ResultSet setOfCategories = preparedStatement.executeQuery();
        StringBuilder sb = new StringBuilder();

        while (setOfCategories.next()) {
            String nameOfCategory = setOfCategories.getString(2);
            sb.append(nameOfCategory.toString());
            sb.append("\n");

            String products = String.format("SELECT NAME, PRICE, RATE FROM PRODUCTS WHERE CATEGORY_ID = %S");
            PreparedStatement preparedStatement1 = connection.prepareStatement("");
            ResultSet setOfProducts = preparedStatement1.executeQuery(products);
            while (setOfProducts.next()) {
                Product product = new Product();
                product.setName(setOfProducts.getString(1));
                product.setPrice(setOfProducts.getDouble(2));
                product.setRate(setOfProducts.getDouble(3));
                sb.append(product.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    public static Product getRandomProduct() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY RAND () LIMIT 1");
        ResultSet listOfProducts = preparedStatement.executeQuery();

        Product product = null;
        if (listOfProducts.next()) {
            String name = listOfProducts.getString("NAME");
            double price = listOfProducts.getDouble("PRICE");
            double rate = listOfProducts.getDouble("RATE");
            product = new Product(name, price, rate);
        }
        connection.close();
        return product;

    }


    public static String getTop5Http() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY PRICE DESC LIMIT 5");
        ResultSet listOfProducts = preparedStatement.executeQuery();

        StringBuilder sb = new StringBuilder();

        while (listOfProducts.next()) {
            Product product = new Product();
            String name = listOfProducts.getString("NAME");
            double price = listOfProducts.getDouble("PRICE");
            double rate = listOfProducts.getDouble("RATE");

            sb.append(product.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String getSortedProducts() throws SQLException {
        Map<String, String> allPropertiesToSort = XMLParser.configMap();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY");
        for (Map.Entry<String, String> entry : allPropertiesToSort.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append("  ");
            stringBuilder.append(key.toUpperCase());
            stringBuilder.append("  ");
            stringBuilder.append(value.toUpperCase());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String orderQuery = stringBuilder.toString();
        System.out.println(orderQuery);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(orderQuery);
        ResultSet listOfProducts = preparedStatement.executeQuery();

        StringBuilder sb = new StringBuilder();

        while (listOfProducts.next()) {
            Product product = new Product();
            product.setName(listOfProducts.getString(1));
            product.setPrice(listOfProducts.getDouble(2));
            product.setRate(listOfProducts.getDouble(3));
            sb.append(product.toString());
            sb.append("\n");
        }
        return sb.toString();
    }


    public void printFilledStore() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println("*******************");
            System.out.println("Print Store from DB");
            System.out.println("*******************");

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORIES")) {
                System.out.println("---------------List of Categories---------------");
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("NAME");
                    System.out.println("Category ID: " + id + ", Name: " + name);
                }
            }

            System.out.println("---------------List of Products---------------");
            try (ResultSet resultSet = statement.executeQuery("SELECT P.ID, C.NAME, P.NAME, P.RATE, P.PRICE FROM PRODUCTS P JOIN CATEGORIES C ON P.CATEGORY_ID = C.ID")) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String categoryName = resultSet.getString("C.NAME");
                    String productName = resultSet.getString("P.NAME");
                    double rate = resultSet.getDouble("P.RATE");
                    double price = resultSet.getDouble("P.PRICE");
                    System.out.println("Product ID: " + id + ", Category: " + categoryName + ", Name: " + productName + ", Rate: " + rate + ", Price: " + price);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query", e);
        }
    }

    public void dropAllTables() {
        String query = "DROP TABLE IF EXISTS PRODUCTS;" +
                "DROP TABLE IF EXISTS CATEGORIES;";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Tables have not been dropped!!!!");
            ;
        }
    }

    public void clearData() {
        String query = "DELETE FROM PRODUCTS;" +
                "DELETE FROM CATEGORIES;";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Tables have not been cleared!!!!");
        }
    }

}
