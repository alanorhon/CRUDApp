package Interfaces;

import model.User;

import java.util.List;

public interface UserDAOInterface {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    void editUser(User user);
}
