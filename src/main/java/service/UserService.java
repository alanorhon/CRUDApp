package service;

import DAO.UserDAOFactory;
import DAO.UserDAO;
import model.User;

import java.util.List;

public class UserService {
    private static UserService instance;
    private static UserDAO userDAO;


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        userDAO = new UserDAOFactory().getFactory();
        return instance;
    }


    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean validateUser(String login, String password) {
        return userDAO.validateUser(login, password);
    }

    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    public boolean addUser(User user) {
        if (userDAO.getUserByLogin(user.getLogin()) == null) {
            userDAO.addUser(user);
            return true;
        } else {
            return false;
        }
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }


    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }
}
