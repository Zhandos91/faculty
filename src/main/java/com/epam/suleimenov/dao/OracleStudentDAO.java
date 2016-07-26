package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.service.Service;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public class OracleStudentDAO implements StudentDAO {

    private Connection connection;

    public OracleStudentDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStudent(int student_id, int user_id) {
        String sql = "INSERT INTO students " +
                "values (" + student_id + "," + user_id + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearStudents() {
        String sql = "DELETE FROM STUDENTS";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int findStudentIdByUserId(int id) {
        String sql = "SELECT * FROM STUDENTS WHERE USER_ID = " + id;
        int student_id = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ;
                student_id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student_id;
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        String sql = "select * from students";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                String courses = resultSet.getString("courses");
                ArrayList<String> list = new ArrayList<String>();
                if (courses != null) {
                    for (String course : courses.split(",")) {
                        list.add(course);
                    }

                }
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        Student student = null;
        String sql = "SELECT * FROM STUDENTS WHERE ID = " + id;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(Service.getUserDAO().getUserById(resultSet.getInt("user_id")).getName());
                student.setSurname(Service.getUserDAO().getUserById(resultSet.getInt("user_id")).getSurname());
                student.setCourses(Service.getCourseDAO().getCoursesByStudentId(id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public ArrayList<Student> getStudentsByCourseId(int id) {

        String sql = "SELECT * FROM COURSE_TO_STUDENT WHERE COURSE_ID = " + id;

        ArrayList<Student> students = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            students = new ArrayList<Student>();

            while (resultSet.next()) {
                students.add(getStudentById(resultSet.getInt("student_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public void removeStudent(int id) {
        String sql = "DELETE FROM courses WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
