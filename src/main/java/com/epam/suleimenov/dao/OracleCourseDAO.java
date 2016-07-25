package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public class OracleCourseDAO implements CourseDAO {

    private Connection connection;
    private final String single_quote = new String("'");

    public OracleCourseDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses " +
                "values (" + course.getId() + "," + single_quote + course.getName() + single_quote + "," + single_quote + course.getDescription() + single_quote + "," + single_quote + course.getTeacherId() + single_quote + "," + single_quote + course.getStatus() + single_quote + ")";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeCourseStatus(int id, String status) {
        String sql = "UPDATE COURSES SET STATUS =" + single_quote + status + single_quote + " WHERE ID = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearCourses() {
        String sql = "DELETE FROM COURSES";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearCourseToStudent() {
        String sql = "DELETE FROM COURSE_TO_STUDENT";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql = "select * from courses";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                ;
                Course course = new Course();
                course.setId(resultset.getInt("id"));
                course.setName(resultset.getString("name"));
                course.setDescription(resultset.getString("description"));
                course.setTeacherId(resultset.getInt("teacher_id"));
                course.setStatus(resultset.getString("status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public ArrayList<Course> getAllCoursesByStatus(String status) {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql = "select * from courses WHERE STATUS = " + single_quote + status + single_quote;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                ;
                ;
                ;
                Course course = new Course();
                course.setId(resultset.getInt("id"));
                course.setName(resultset.getString("name"));
                course.setDescription(resultset.getString("description"));
                course.setTeacherId(resultset.getInt("teacher_id"));
                course.setStatus(resultset.getString("status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        String sql = "SELECT * FROM COURSES WHERE ID = " + id;
        Course course = null;
        ArrayList<Student> students = new ArrayList<Student>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setDescription(resultSet.getString("description"));
                course.setTeacherId(resultSet.getInt("teacher_id"));
                course.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public ArrayList<Course> getCoursesByStudentId(int id) {
        String sql = "SELECT * FROM COURSE_TO_STUDENT WHERE STUDENT_ID = " + id;
        ArrayList<Course> courses = new ArrayList<Course>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(getCourseById(resultSet.getInt("course_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public void matchCourseAndStudent(int id, int course_id, int student_id) {
        String sql = "INSERT INTO COURSE_TO_STUDENT " +
                "values (" + id + "," + course_id + "," + student_id + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCourse(int id) {
        String sql = "DELETE FROM courses WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
