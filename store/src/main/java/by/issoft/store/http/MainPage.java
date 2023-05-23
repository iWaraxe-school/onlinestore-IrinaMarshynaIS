package by.issoft.store.http;

import by.issoft.store.DBHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class MainPage implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        try {
            ResponseHandler.handleResponse(exchange, "All available products in the store: \n\n" + DBHelper.getProductsWithCategories());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
