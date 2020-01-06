package utils;

import com.mysql.cj.jdbc.Driver;
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
            connection = getMysqlConnection();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", Config.getInstance().getDbDialect());
        configuration.setProperty("hibernate.connection.driver_class", Config.getInstance().getDriverClass());
        configuration.setProperty("hibernate.connection.url", Config.getInstance().getDbUrl());
        configuration.setProperty("hibernate.connection.username", Config.getInstance().getDblogin());
        configuration.setProperty("hibernate.connection.password", Config.getInstance().getDbPassword());
        configuration.setProperty("hibernate.show_sql", Config.getInstance().getShowSql());
        configuration.setProperty("hibernate.hbm2ddl.auto", Config.getInstance().getHbm2ddl());
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver)
                    Class.forName(Config.getInstance().getDriverClass()).newInstance());
            return DriverManager.getConnection(Config.getInstance().getDbUrl(),
                    Config.getInstance().getDblogin(), Config.getInstance().getDbPassword());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}