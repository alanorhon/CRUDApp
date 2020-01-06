package utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Config {
    private static Config instance;

    private String driver;
    private String driverClass;
    private String dbUrl;
    private String dbDialect;
    private String dblogin;
    private String dbPassword;
    private String showSql;
    private String hbm2ddl;


    private Config() {
        readConfig();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void readConfig() {
        Properties property = new Properties();
        try {
            property.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")));
        } catch (IOException e) {
            System.out.println("ERROR: Config file is empty or not exist");
        }
        driver = property.getProperty("Driver");
        driverClass = property.getProperty("DriverClass");
        dbUrl = property.getProperty("DbUrl");
        dbDialect = property.getProperty("DbDialect");
        dblogin = property.getProperty("DbLogin");
        dbPassword = property.getProperty("DbPassword");
        showSql = property.getProperty("ShowSql");
        hbm2ddl = property.getProperty("hbm2ddl");
    }

    public String getDriver() {
        return driver;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbDialect() {
        return dbDialect;
    }

    public String getDblogin() {
        return dblogin;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getShowSql() {
        return showSql;
    }

    public String getHbm2ddl() {
        return hbm2ddl;
    }
}