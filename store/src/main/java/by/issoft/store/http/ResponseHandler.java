package by.issoft.store.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseHandler {
    public static void handleResponse(HttpExchange httpExchange, String data) throws IOException {
      httpExchange.sendResponseHeaders(200, data.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(data.getBytes());
        os.close();
    }
}
