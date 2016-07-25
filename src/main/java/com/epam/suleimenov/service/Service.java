package com.epam.suleimenov.service;

import com.epam.suleimenov.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by admin on 7/4/2016.
 */
public class Service {

    private static DAOFactory daoFactory;

    public void setDaoFactory(DAOFactory daoFactory) {
        Service.daoFactory = daoFactory;
    }

    public static DAOFactory getDAOFactory() {
        return daoFactory;
    }

    public static ArchiveDAO getArchiveDAO(Connection connection) {
        return daoFactory.getArchiveDAO(connection);
    }
    public static Connection getConnection() throws SQLException {
        return daoFactory.getConnection();
    }
    public static CourseDAO getCourseDAO(Connection connection) {
        return  daoFactory.getCourseDAO(connection);
    }
    public static FacultyDAO getFacultyDAO(Connection connection) {
        return  daoFactory.getFacultyDAO(connection);
    }
    public static StudentDAO getStudentDAO(Connection connection) {
        return daoFactory.getStudentDAO(connection);
    }
    public static TeacherDAO getTeacherDAO(Connection connection) {
        return  daoFactory.getTeacherDAO(connection);
    }
    public static UserDAO getUserDAO(Connection connection) {
        return  daoFactory.getUserDAO(connection);
    }

}
