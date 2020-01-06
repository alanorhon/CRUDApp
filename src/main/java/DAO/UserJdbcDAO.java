package DAO;

import Interfaces.UserDAOInterface;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAOInterface {
    private static UserJdbcDAO instance;
    private static Connection connection;

    private UserJdbcDAO(Connection daoConnection) {
        connection = daoConnection;
    }

    public static UserJdbcDAO getInstance(Connection daoConnection) {
        if (instance == null) {
            instance = new UserJdbcDAO(daoConnection);
        }
        return instance;
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into users(login, password, email) value (?,?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            if (stmt.executeUpdate() != 0) {
                stmt.close();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from users where id=?");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(Long id) {
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from users where id=?");
            stmt.setLong(1, id);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                user = new User(id, login, password, email);
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void editUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement
                    ("update users set login=?, password=?, email=? where id=?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                Long id = result.getLong("id");
                String login = result.getString("login");
                String password = result.getString("password");
                String email = result.getString("email");
                allUsers.add(new User(id, login, password, email));
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}
