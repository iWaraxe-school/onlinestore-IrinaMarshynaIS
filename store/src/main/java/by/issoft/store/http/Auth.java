package by.issoft.store.http;

import com.sun.net.httpserver.BasicAuthenticator;

public class Auth extends BasicAuthenticator {
    public Auth(String get) { super(get); }

    @Override
    public boolean checkCredentials(String user, String pw) {
      return user.equals("user") && pw.equals("user");

    }
}
