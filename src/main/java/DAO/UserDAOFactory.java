package DAO;

import utils.Config;
import utils.DBHelper;

import java.io.IOException;

public class UserDAOFactory {
    public UserDAOInterface getFactory() throws IOException {
        if (Config.getInstance().getDriver().equals("Hibernate")) {
            return UserHibernateDAO.getInstance(DBHelper.getSessionFactory());
        } else {
            return UserJdbcDAO.getInstance(DBHelper.getConnection());
        }
    }
}
