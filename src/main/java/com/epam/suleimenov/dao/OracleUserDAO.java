package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public class OracleUserDAO implements UserDAO {

    private Connection connection;
    private final String single_quote = new String("'");

    public OracleUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users values(" + user.getId() + "," + single_quote + user.getName() + single_quote + "," + single_quote + user.getSurname() + single_quote + "," + single_quote + user.getEmail() + single_quote + "," + single_quote + user.getUserRole() + single_quote + "," + single_quote + user.getPassword() + single_quote + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkUser(String login, String password, String userRole) {
        User user = findUserByEmail(login);
        if (user == null)
            return false;

        if (user.getPassword().equals(password) && user.getUserRole().equals(userRole))
            return true;

        return false;
    }

    @Override
    public void clearUsers() {
        String sql = "DELETE FROM COURSES";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = " + single_quote + email + single_quote;
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserRole(resultSet.getString("user_role"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public int findUserIdByTeacherId(int id) {
        String sql = "SELECT * FROM TEACHERS WHERE ID = " + id;
        int user_id = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user_id = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user_id;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();

        String sql = "select * from users";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getInt("id"));
                user.setName(resultset.getString("name"));
                user.setSurname(resultset.getString("surname"));
                user.setEmail(resultset.getString("email"));
                user.setUserRole(resultset.getString("user_role"));
                user.setPassword(resultset.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM USERS where ID = " + id;
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setUserRole(resultSet.getString("user_role"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
