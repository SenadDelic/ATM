package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance = null;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "mike";
    private static final String URL = Constant.CONNECTION_STRING + Constant.TIME_ZONE_ERROR;

    private Connection connection = null;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (instance == null)
            instance = new ConnectionManager();
        return instance;
    }

    public boolean openConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            if (openConnection()) {
                System.out.println("Connection opened!");
                return connection;
            } else
                return null;
        }
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
