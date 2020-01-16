package DAO;

import utils.DBHelper;
import utils.PropertiesReader;

public class UserDAOFactory {
    public UserDAO getFactory() {
        if (PropertiesReader.getProperty("DAO").equals("JDBC")) {
            return new UserJdbcDAO(DBHelper.getConnection());
        } else {
            return new UserHibernateDAO(DBHelper.getSessionFactory());
        }
    }
}
