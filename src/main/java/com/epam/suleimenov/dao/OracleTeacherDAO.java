package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.service.Service;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public class OracleTeacherDAO implements TeacherDAO {

    private Connection connection;

    public OracleTeacherDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTeacher(int teacher_id, int user_id) {

        String sql = "INSERT INTO teachers " +
                "values (" + teacher_id + "," + user_id + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearTeachers() {
        String sql = "DELETE FROM TEACHERS";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int findTeacherIdByUserId(int id) {
        String sql = "SELECT * FROM TEACHERS WHERE USER_ID = " + id;
        int teacher_id = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                teacher_id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher_id;
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        String sql = "select * from teachers";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                String courses = resultSet.getString("courses");
                ArrayList<String> list = new ArrayList<String>();
                if (courses != null) {
                    for (String course : courses.split(",")) {
                        list.add(course);
                    }

                }
                teachers.add(teacher);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    @Override
    public Teacher getTeacherById(int id) {
        String sql = "SELECT * FROM TEACHERS WHERE ID = " + id;
        int user_id;
        Teacher teacher = new Teacher();
        ArrayList<Course> courses = new ArrayList<Course>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user_id = resultSet.getInt("user_id");
                teacher.setId(id);
                teacher.setName(Service.getUserDAO().getUserById(user_id).getName());
            } else {
                System.out.println("Cannot find the user");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT * FROM COURSES WHERE TEACHER_ID = " + id;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setDescription(resultSet.getString("description"));
                course.setTeacherId(resultSet.getInt("teacher_id"));
                course.setStatus(resultSet.getString("status"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        teacher.setCourses(courses);

        return teacher;
    }

    @Override
    public int getTeacherIdByCourseId(int id) {
        String sql = "SELECT * FROM COURSES WHERE ID = " + id;
        int teacher_id = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                teacher_id = resultSet.getInt("teacher_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher_id;
    }

    @Override
    public void removeTeacher(int id) {
        String sql = "DELETE FROM teachers WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
