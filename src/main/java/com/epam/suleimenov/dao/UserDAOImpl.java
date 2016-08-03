package com.epam.suleimenov.dao;

import com.epam.suleimenov.connection.DBConnection;
import com.epam.suleimenov.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDAOImpl implements UserDAO {

    private Connection connection;
    private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class.getName());

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean clear() {
        String sql = "DELETE FROM " + DBConnection.getDBName() + ".USERS";
        boolean isCleared = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (preparedStatement.executeUpdate() > 0)
                isCleared = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCleared;
    }

    @Override
    public User create(User user) {
        String sql = "INSERT INTO " + DBConnection.getDBName() + ".USERS(FIRST_NAME, LAST_NAME, EMAIL, USER_ROLE, PASSWORD) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"USER_ID"})) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserRole().toString());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet != null && resultSet.next())
                user.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean delete(Object id) {
        String sql = "DELETE FROM " + DBConnection.getDBName() + ".COURSES WHERE USER_ID = ?";
        boolean isDeleted = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, (int) id);
            if(preparedStatement.executeUpdate() > 0)
                isDeleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    @Override
    public User find(Object id) {
        String sql = "SELECT * FROM " +  DBConnection.getDBName() + ".USERS WHERE USER_ID = ?";
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUserRole(User.Role.valueOf(resultSet.getString("user_role")));
                user.setPassword(resultSet.getString("password"));
                user.setCourses(new CourseDAOImpl(connection).findCoursesByUserId(user.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();

        String sql = "SELECT * from " + DBConnection.getDBName() + ".USERS";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getInt("user_id"));
                user.setFirstName(resultset.getString("first_name"));
                user.setLastName(resultset.getString("last_name"));
                user.setEmail(resultset.getString("email"));
                user.setUserRole(User.Role.valueOf(resultset.getString("user_role")));
                user.setPassword(resultset.getString("password"));
                user.setCourses(new CourseDAOImpl(connection).findCoursesByUserId(user.getId()));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE " + DBConnection.getDBName() + ".USERS SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, USER_ROLE = ?, PASSWORD = ? WHERE USER_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserRole().toString());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean checkUser(String login, String password, String userRole) {
        User user = findUserByEmail(login);
        log.debug("checkUser{}", user);

        if (user == null)
            return false;


        if (user.getPassword().equals(password) && user.getUserRole().toString().equals(userRole))
            return true;

        return false;
    }

    @Override
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".USERS WHERE EMAIL = ?";
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUserRole(User.Role.valueOf(resultSet.getString("user_role").toUpperCase()));
                user.setPassword(resultSet.getString("password"));
                user.setCourses(new CourseDAOImpl(connection).findCoursesByUserId(user.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
