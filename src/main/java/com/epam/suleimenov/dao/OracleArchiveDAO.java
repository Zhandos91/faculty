package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.Archive;
import com.epam.suleimenov.service.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public class OracleArchiveDAO implements ArchiveDAO {
    private final String single_quote = new String("'");
    private Connection connection;

    public OracleArchiveDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addToArchive(int id, int student_id, int course_id, int grade) {
        SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String sql = "INSERT INTO archive " +
                "values (" + id + "," + student_id + "," + course_id + "," + grade + ",TO_DATE(" + single_quote + date_formatter.format(new Date()) + single_quote + "," + single_quote + "yyyy-mm-dd hh:mi:ss" + single_quote + "))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearArchive() {
        String sql = "DELETE FROM ARCHIVE";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Archive> getArchive() {
        ArrayList<Archive> archives = new ArrayList<>();
        String sql = "select * from archive";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Archive archive = new Archive();
                archive.setId(resultSet.getInt("id"));
                archive.setTeacher(Service.getTeacherDAO(connection).getTeacherById(Service.getTeacherDAO(connection).getTeacherIdByCourseId(resultSet.getInt("course_id"))).getName());
                archive.setStudent(Service.getStudentDAO(connection).getStudentById(resultSet.getInt("student_id")).getName());
                archive.setCourse(Service.getCourseDAO(connection).getCourseById(resultSet.getInt("course_id")).getName());
                archive.setGrade(resultSet.getInt("grade"));
                archive.setDate(resultSet.getDate("date"));
                archives.add(archive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return archives;
    }
}
