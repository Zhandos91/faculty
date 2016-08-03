package com.epam.suleimenov.dao;

import com.epam.suleimenov.connection.DBConnection;
import com.epam.suleimenov.model.Archive;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchiveDAOImpl implements ArchiveDAO {

    private Connection connection;
    private static Logger log = LoggerFactory.getLogger(ArchiveDAOImpl.class.getName());

    public ArchiveDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean clear() {
        String sql = "DELETE FROM " + DBConnection.getDBName() + ".ARCHIVE";
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
    public Archive create(Archive archive) {

        String sql = "INSERT INTO " + DBConnection.getDBName() + ".ARCHIVE(STUDENT_ID, COURSE_ID, GRADE, GRADING_DATE, TEACHER_ID) VALUES(?, ?, ?, ?, ?)";
        SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        log.debug("TO_DATE(\'" + date_formatter.format(new Date()) + "\',\'yyyy-mm-dd hh:mi:ss\')");

        log.debug("create archive {}", archive);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"ARCHIVE_ID"})) {
            preparedStatement.setInt(1, archive.getStudent().getId());
            preparedStatement.setInt(2, archive.getCourse().getId());
            preparedStatement.setString(3, archive.getGrade().toString());
            preparedStatement.setDate(4, new java.sql.Date(archive.getDate().getTime()));
            preparedStatement.setInt(5, archive.getTeacher().getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet != null && resultSet.next())
                archive.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return archive;
    }

    @Override
    public boolean delete(Object id) {
        String sql = "DELETE FROM " + DBConnection.getDBName() + ".ARCHIVE WHERE ARCHIVE_ID = ?";
        boolean isDeleted = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, (int) id);
            if (preparedStatement.executeUpdate() > 0)
                isDeleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public Archive find(Object id) {
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".ARCHIVE WHERE ARCHIVE_ID = ?";
        Archive archive = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                archive = new Archive();
                archive.setId(resultSet.getInt("archive_id"));
                archive.setTeacher(new UserDAOImpl(connection).find(resultSet.getInt("teacher_id")));
                archive.setStudent(new UserDAOImpl(connection).find(resultSet.getInt("student_id")));
                archive.setCourse(new CourseDAOImpl(connection).find(resultSet.getInt("course_id")));
                archive.setGrade(Archive.Grade.valueOf(resultSet.getString("grade")));
                archive.setDate(resultSet.getDate("grading_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return archive;
    }

    @Override
    public List<Archive> getAll() {
        List<Archive> archives = new ArrayList<Archive>();
        String sql = "SELECT * FROM " + DBConnection.getDBName() + ".ARCHIVE";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Archive archive = new Archive();
                archive.setId(resultSet.getInt("archive_id"));
                archive.setTeacher(new UserDAOImpl(connection).find(resultSet.getInt("teacher_id")));
                archive.setStudent(new UserDAOImpl(connection).find(resultSet.getInt("student_id")));
                archive.setCourse(new CourseDAOImpl(connection).find(resultSet.getInt("course_id")));
                archive.setGrade(Archive.Grade.valueOf(resultSet.getString("grade")));
                archive.setDate(resultSet.getDate("grading_date"));
                archives.add(archive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return archives;
    }

    @Override
    public Archive update(Archive archive) {
        String sql = "UPDATE " + DBConnection.getDBName() + ".ARCHIVE SET STUDENT_ID = ?, COURSE_ID = ?, GRADE = ?, GRADING_DATE = ?, TEACHER_ID = ? WHERE ARCHIVE_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, archive.getStudent().getId());
            preparedStatement.setInt(2, archive.getCourse().getId());
            preparedStatement.setString(3, archive.getGrade().toString());
            preparedStatement.setDate(4, new java.sql.Date(archive.getDate().getTime()));
            preparedStatement.setInt(5, archive.getTeacher().getId());
            preparedStatement.setInt(6, archive.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return archive;
    }
}
