package utils;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        if (connection == null) {
            connection = getMySQLConnection();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static final String URL = PropertiesReader.getProperty("jdbc.url");
    private static final String USERNAME = PropertiesReader.getProperty("jdbc.username");
    private static final String PASSWORD = PropertiesReader.getProperty("jdbc.password");
    private static final String DRIVER_CLASS = PropertiesReader.getProperty("jdbc.driverClassName");

    private static final String HIBERNATE_DIALECT = PropertiesReader.getProperty("hibernate.dialect");
    private static final String HIBERNATE_SHOW_SQL = PropertiesReader.getProperty("hibernate.show_sql");
    private static final String HIBERNATE_HBM2DDL_AUTO = PropertiesReader.getProperty("hibernate.hbm2ddl.auto");

    private static Connection getMySQLConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.connection.url", URL);
        configuration.setProperty("hibernate.connection.username", USERNAME);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.connection.driver_class", DRIVER_CLASS);

        configuration.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}