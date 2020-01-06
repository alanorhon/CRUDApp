package service;

import DAO.UserDAOFactory;
import DAO.UserHibernateDAO;
import Interfaces.UserDAOInterface;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.DBHelper;

import java.io.IOException;
import java.util.List;

public class UserService {
    private static UserService instance;
    private static UserDAOInterface userDAOFactory;
    private UserDAOInterface DAOFactory;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        try {
            userDAOFactory = new UserDAOFactory().getFactory();
        }catch (IOException e){
            e.printStackTrace();
        }
        return instance;
    }


    public List<User> getAllUsers() {
        List<User> list = userDAOFactory.getAllUsers();
        return list;
    }

    public void addUser(User user) {
        userDAOFactory.addUser(user);
    }

    public User getUserById(Long id) {
        return userDAOFactory.getUserById(id);
    }


    public void deleteUser(Long id) {
        userDAOFactory.deleteUser(id);
    }

    public void editUser(User user) {
        userDAOFactory.editUser(user);
    }
}
