package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into users(login, password, email) value (?,?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            if (stmt.executeUpdate() != 0) {
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from users where id=?");
            stmt.setLong(1, user.getId());
            if (stmt.executeUpdate() != 0) {
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public boolean editUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement
                                                ("update users set login=?, password=?, email=? where id=?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setLong(4, user.getId());
            if (stmt.executeUpdate() != 0) {
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
