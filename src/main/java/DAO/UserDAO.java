package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void addUser(User user);
    boolean validateUser(String login, String password);
    User getUserByLogin(String login);
    User getUserById(Long id);
    void deleteUser(Long id);
    void editUser(User user);
}
