package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Rue2w3zf";
    private static Connection connection;

    private Util() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                System.out.println("Connection established");
            } catch (SQLException e) {
                throw new SQLException("Failed connection", e);
            }
        }
        return connection;
    }
}
