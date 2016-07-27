package com.epam.suleimenov.dao;

import com.epam.suleimenov.connection.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public class FacultyDAOFactory extends DAOFactory {

    private final Connection connection;

    public FacultyDAOFactory() {
        connection  = DBConnectionPool.getConnection();
    }

    public ArchiveDAO getArchiveDAO() {
        return new OracleArchiveDAO(connection);
    }

    public CourseDAO getCourseDAO() {
        return new OracleCourseDAO(connection);
    }

    public FacultyDAO getFacultyDAO() {
        return new OracleFacultyDAO(connection);
    }

    public StudentDAO getStudentDAO() {
        return new OracleStudentDAO(connection);
    }

    public TeacherDAO getTeacherDAO() {
        return new OracleTeacherDAO(connection);
    }

    public UserDAO getUserDAO() {
        return new OracleUserDAO(connection);
    }


    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
