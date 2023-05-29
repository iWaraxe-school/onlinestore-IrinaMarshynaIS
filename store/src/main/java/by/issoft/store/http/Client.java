package by.issoft.store.http;

import by.issoft.domain.Product;
import by.issoft.store.DBHelper;
import by.issoft.store.Store;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.sql.SQLException;

public class Client {
    Store store;

    public Client(Store store) {
        this.store = store;
    }

    public static void clientMakesOrder() throws SQLException {
        Product orderedProduct = DBHelper.getRandomProduct();
        Gson g = new Gson();
        String productInJson = g.toJson(orderedProduct);

        RestAssured.baseURI = "http://localhost:8088";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.auth().basic("user", "user");
        request.body(productInJson);

        Response response = request.post("/order");
        System.out.println("The status received" + response.statusLine());
        System.out.println("Response status code:" + response.getStatusCode());
        System.out.println("Response body:" + response.getBody().asString());

    }
}
