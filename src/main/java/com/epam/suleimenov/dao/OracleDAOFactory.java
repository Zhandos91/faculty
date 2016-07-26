package com.epam.suleimenov.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public class OracleDAOFactory implements DAOFactory {

    @Override
    public ArchiveDAO getArchiveDAO(Connection connection) {
        return new OracleArchiveDAO(connection);
    }

    @Override
    public CourseDAO getCourseDAO(Connection connection) {
        return new OracleCourseDAO(connection);
    }

    @Override
    public FacultyDAO getFacultyDAO(Connection connection) {
        return new OracleFacultyDAO(connection);
    }

    @Override
    public StudentDAO getStudentDAO(Connection connection) {
        return new OracleStudentDAO(connection);
    }

    @Override
    public TeacherDAO getTeacherDAO(Connection connection) {
        return new OracleTeacherDAO(connection);
    }

    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new OracleUserDAO(connection);
    }


}
