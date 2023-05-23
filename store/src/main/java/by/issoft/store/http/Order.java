package by.issoft.store.http;

import by.issoft.domain.Product;
import by.issoft.store.DBHelper;
import com.mysql.cj.xdevapi.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import static by.issoft.store.http.ResponseHandler.handleResponse;

public class Order implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String orderedProductInJSON = null;
        if (exchange.getRequestMethod().equals("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            orderedProductInJSON = bufferedReader.readLine();
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject obj = (JSONObject) new JSONParser().parse(orderedProductInJSON);
                String productName = obj.get("name").toString();
                String price = obj.get("price").toString();
                String rate = obj.get("rate").toString();
                Product orderedProduct = new Product(productName, Double.parseDouble(price), Double.parseDouble(rate));
                DBHelper.addOrderToDB(orderedProduct);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (org.json.simple.parser.ParseException e) {
                throw new RuntimeException(e);
            }
            handleResponse(exchange, "You have ordered the following product: " + orderedProductInJSON);
        }
        if (exchange.getRequestMethod().equals("GET")) {
            try {
                handleResponse(exchange, "Ordered product: \n\n" + DBHelper.getOrderFromDB());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            handleResponse(exchange, "Error!");
        }

    }
}
