package com.epam.suleimenov.dao;

import com.epam.suleimenov.connection.DBConnection;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CourseDAOImpl implements CourseDAO {

    private Connection connection;
    private static Logger log = LoggerFactory.getLogger(CourseDAOImpl.class.getName());

    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean clear() {
        String sql = "DELETE FROM " + DBConnection.getDBName() + ".COURSES";
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
    public Course create(Course course) {

        String sql = "INSERT INTO " + DBConnection.getDBName() + ".COURSES(COURSE_NAME, COURSE_DESCRIPTION, COURSE_STATUS) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,  new String[]{"COURSE_ID"})) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getStatus());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet != null && resultSet.next())
                    course.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public boolean delete(Object id) {
        String sql = "DELETE FROM " + DBConnection.getDBName() + ".COURSES WHERE COURSE_ID = ?";
        boolean isCourseDeleted = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, (int) id);
            if(preparedStatement.executeUpdate() > 0) {
                isCourseDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCourseDeleted;
    }

    @Override
    public Course find(Object id) {
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".COURSES WHERE COURSE_ID = ?";
        Course course = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getInt("course_id"));
                course.setName(resultSet.getString("course_name"));
                course.setDescription(resultSet.getString("course_description"));
                course.setStatus(resultSet.getString("course_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public List<Course> getAll() {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".COURSES";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                Course course = new Course();
                course.setId(resultset.getInt("course_id"));
                course.setName(resultset.getString("course_name"));
                course.setDescription(resultset.getString("course_description"));
                course.setStatus(resultset.getString("course_status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Course update(Course course) {

        String sql = "UPDATE " + DBConnection.getDBName() + ".COURSES SET COURSE_NAME = ?, COURSE_DESCRIPTION = ?, COURSE_STATUS = ? WHERE COURSE_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getStatus());
            preparedStatement.setInt(4, course.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public boolean addToCourseToUser(Course course, User user, String listener_type) {
        String sql = "INSERT INTO " + DBConnection.getDBName() + ".COURSE_TO_USER(USER_ID, COURSE_ID, LISTENER_TYPE) VALUES(?, ?, ?) ";
        boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, course.getId());
            preparedStatement.setString(3, listener_type);
            if (preparedStatement.executeUpdate() > 0)
                success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean clearCourseToUser() {
        String sql = "DELETE FROM " + DBConnection.getDBName() + ".COURSE_TO_USER";
        boolean success = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (preparedStatement.executeUpdate() > 0)
                success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }


    @Override
    public List<Course> getAllCoursesByStatus(String status) {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".COURSES WHERE COURSE_STATUS = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                Course course = new Course();
                course.setId(resultset.getInt("course_id"));
                course.setName(resultset.getString("course_name"));
                course.setDescription(resultset.getString("course_description"));
                course.setStatus(resultset.getString("course_status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> findCoursesByUserId(int user_id) {
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".COURSE_TO_USER WHERE USER_ID = ?";
        List<Course> courses = new ArrayList<Course>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(find(resultSet.getInt("course_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<User> findStudentsByCourseId(int id) {

        return findUsersByCourseId(id, User.Role.valueOf("STUDENT").toString());
    }

    public List<User> findTeachersByCourseId(int id) {

        return findUsersByCourseId(id, User.Role.valueOf("TEACHER").toString());
    }

    public List<User> findUsersByCourseId(int course_id, String listener_type) {
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".COURSE_TO_USER WHERE COURSE_ID = ? AND LISTENER_TYPE = ?";
        List<User> users = new ArrayList<User>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, course_id);
            preparedStatement.setString(2, listener_type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new UserDAOImpl(connection).find(resultSet.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
