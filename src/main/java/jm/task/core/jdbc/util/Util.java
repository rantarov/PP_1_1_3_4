package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Rue2w3zf";
    private static Connection connection;
    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.url", JDBC_URL);
                configuration.setProperty("hibernate.connection.username", JDBC_USER);
                configuration.setProperty("hibernate.connection.password", JDBC_PASSWORD);
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                configuration.setProperty("hibernate.hbm2ddl.auto", "none");
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.setProperty("hibernate.format_sql", "true");
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Session Factory established");
            } catch (Exception e) {
                throw new RuntimeException("Failed to create session factory", e);
            }
        }
        return sessionFactory;
    }
}
