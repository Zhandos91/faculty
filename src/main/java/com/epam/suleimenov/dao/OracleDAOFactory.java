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

    private String dbPath;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;
    private Properties properties;


    public OracleDAOFactory() {

        properties = new Properties();

        try {
            properties.load(OracleDAOFactory.class.getClassLoader().getResourceAsStream("db.properties"));
            dbDriver = properties.getProperty("db_driver");
            dbPath = properties.getProperty("db_path");
            dbUser = properties.getProperty("db_user");
            dbPassword = properties.getProperty("db_password");

        } catch (IOException e) {
            System.out.println("Property file is not set or found");
            e.printStackTrace();
        }

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver is not found");
            e.printStackTrace();
        }
    }

    @Override
    public ArchiveDAO getArchiveDAO(Connection connection) {
        return new OracleArchiveDAO(connection);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbPath, dbUser, dbPassword);
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
