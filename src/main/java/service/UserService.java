package service;

import DAO.UserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.DBHelper;

import java.util.List;

public class UserService {
    private static UserService instance;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService(DBHelper.getSessionFactory());
        }
        return instance;
    }


    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        List<User> list = userDAO.getAllUsers();
        return list;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        userDAO.addUser(user);
    }

    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        return userDAO.getUserById(id);
    }


    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        userDAO.deleteUser(id);
    }

    public void editUser(User user) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        userDAO.editUser(user);
    }
}
