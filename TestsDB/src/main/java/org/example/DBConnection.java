package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {


    private String _dbName;
    private String _login;
    private String _pass;
    private Connection _manager;

    public boolean Connect() {
        Connection c = null;
        try {

            Class.forName("org.postgresql.Driver");
            Properties info = new Properties();
            info.setProperty("user",_login);
            info.setProperty("password",_pass);
            info.setProperty("useUnicode","true");
            info.setProperty("characterEncoding","UTF-8");
            Class.forName("org.postgresql.Driver");
            _manager = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + _dbName,
                            info);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public DBConnection(String dbname, String login, String pass) {
        _dbName = dbname;
        _login = login;
        _pass = pass;
    }

    public Connection get_manager() {
        return _manager;
    }

    public void set_manager(Connection _manager) {
        this._manager = _manager;
    }
}
