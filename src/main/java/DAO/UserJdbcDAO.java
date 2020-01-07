package DAO;

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
            PreparedStatement stmt = connection.prepareStatement("insert into users(login, password, email, role) value (?,?,?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            if (stmt.executeUpdate() != 0) {
                stmt.close();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String login, String password) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM users WHERE login=? AND password=?")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from users where login=?");
            stmt.setString(1, login);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                user = new User(login, password, email, role);
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public void deleteUser(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from users where id=?");
            stmt.setLong(1, id);
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
                String role = resultSet.getString("role");
                user = new User(id, login, password, email, role);
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
                    ("update users set login=?, password=?, email=?, role=? where id=?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setLong(5, user.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                Long id = result.getLong("id");
                String login = result.getString("login");
                String password = result.getString("password");
                String email = result.getString("email");
                String role = result.getString("role");
                allUsers.add(new User(id, login, password, email, role));
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}
