package service;

import DAO.UserDAOFactory;
import DAO.UserDAOInterface;
import model.User;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public List<User> getAllUsers() {
        List<User> list = userDAOFactory.getAllUsers();
        return list;
    }

    public boolean validateUser(String login, String password) {
        return userDAOFactory.validateUser(login, password);
    }

    public User getUserByLogin(String login) {
        return userDAOFactory.getUserByLogin(login);
    }

    public boolean addUser(User user) {
        if (userDAOFactory.getUserByLogin(user.getLogin()) == null) {
            userDAOFactory.addUser(user);
            return true;
        } else {
            return false;
        }
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
