package service;

import DAO.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService instance;

    public UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMySQLConnection());
    }

    public List<User> getAllUsers() {
        return getUserDAO().getAllUsers();
    }

    public boolean addUser(User user) {
        return getUserDAO().addUser(user);
    }

    public User getUserById(Long id) {
        return getUserDAO().getUserById(id);
    }

    public boolean deleteUser(User user) {
        return getUserDAO().deleteUser(user);
    }

    public boolean editUser(User user) {
        return getUserDAO().editUser(user);
    }


    private static Connection getMySQLConnection() {
        {
            try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
                StringBuilder url = new StringBuilder();

                url.
                        append("jdbc:mysql://").        //db type
                        append("localhost:").           //host name
                        append("3306/").                //port
                        append("pp1?").                //db name
                        append("user=root&").           //login
                        append("password=0268q7410").  //password
                        append("&serverTimezone=UTC");  //setup server time

                return DriverManager.getConnection(url.toString());
            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new IllegalStateException();
            }
        }
    }
}
