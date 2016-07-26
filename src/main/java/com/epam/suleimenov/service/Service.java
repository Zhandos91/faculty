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
        this.daoFactory = daoFactory;
    }

    public static ArchiveDAO getArchiveDAO() {
        return daoFactory.getArchiveDAO(getConnection());
    }
    public static Connection getConnection() {
        return daoFactory.getConnection();
    }

    public static DAOFactory getDAOFactory() {
        return daoFactory;
    }
    public static CourseDAO getCourseDAO() {
        return  daoFactory.getCourseDAO(getConnection());
    }
    public static FacultyDAO getFacultyDAO() {
        return  daoFactory.getFacultyDAO(getConnection());
    }
    public static StudentDAO getStudentDAO() {
        return daoFactory.getStudentDAO(getConnection());
    }
    public static TeacherDAO getTeacherDAO() {
        return  daoFactory.getTeacherDAO(getConnection());
    }
    public static UserDAO getUserDAO() {
        return  daoFactory.getUserDAO(getConnection());
    }

}
