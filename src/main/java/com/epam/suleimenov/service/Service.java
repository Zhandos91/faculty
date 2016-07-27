package com.epam.suleimenov.service;

import com.epam.suleimenov.connection.DBConnection;
import com.epam.suleimenov.connection.DBConnectionPool;
import com.epam.suleimenov.dao.*;
import com.epam.suleimenov.servlet.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Created by admin on 7/4/2016.
 */
public class Service implements  AutoCloseable {

    private static DAOFactory daoFactory;
    private static Connection connection;
    private final String CONNECTION_POOLED = "connectionPooled";
    private final String FACULTY_DAO_FACTORY = "faculty_DAO";
    private final String DAO_PROPERTY = "dao.properties";

    public Service(String connection_type) {

        if(connection_type.equalsIgnoreCase(CONNECTION_POOLED))
            connection = DBConnectionPool.getConnection();
        else
            connection = DBConnection.getConnection();


        Properties properties = new Properties();
        try {
            properties.load(Controller.class.getClassLoader().getResourceAsStream(DAO_PROPERTY));
            daoFactory = DAOFactory.getDAOFactory(properties.getProperty(FACULTY_DAO_FACTORY));

        } catch (IOException e) {
            System.out.println("Property file is not set or found");
            e.printStackTrace();
        }
    }

    public static DAOFactory getDAOFactory() {
        return daoFactory;
    }
    public static ArchiveDAO getArchiveDAO() {
        return daoFactory.getArchiveDAO(connection);
    }
    public static CourseDAO getCourseDAO() {return  daoFactory.getCourseDAO(connection);}
    public static FacultyDAO getFacultyDAO() {
        return  daoFactory.getFacultyDAO(connection);
    }
    public static StudentDAO getStudentDAO() {
        return daoFactory.getStudentDAO(connection);
    }
    public static TeacherDAO getTeacherDAO() { return  daoFactory.getTeacherDAO(connection);}
    public static UserDAO getUserDAO() {
        return  daoFactory.getUserDAO(connection);
    }


    @Override
    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
