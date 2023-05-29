package by.issoft.store.http;

import by.issoft.store.DBHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class Sorted implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            ResponseHandler.handleResponse(exchange, "Sorted products: \n\n" + DBHelper.getSortedProducts());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
