package com.epam.suleimenov.service;

import com.epam.suleimenov.dao.*;
import java.sql.Connection;


/**
 * Created by admin on 7/4/2016.
 */
public class Service {

    private static DAOFactory daoFactory;
    private static Connection connection;

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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
