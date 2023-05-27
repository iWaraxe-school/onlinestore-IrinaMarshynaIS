package by.issoft.store.http;

import by.issoft.store.DBHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class Top5 implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        try {
            ResponseHandler.handleResponse(exchange, "List of Top5 products: \n\n" + DBHelper.getTop5Http());
        } catch (SQLException e) {
            throw new RuntimeException("Error in Top5 class!!!", e);
        }
    }
}
