package com.epam.suleimenov.dao;

import java.sql.Connection;

public abstract class DAOFactory {


    public static DAOFactory getDAOFactory(String whichFactory) {

        if(whichFactory.equalsIgnoreCase("facultyDAO"))
            return new FacultyDAOFactory();

        return null;
    }

    public abstract ArchiveDAO getArchiveDAO(Connection connection);

    public abstract CourseDAO getCourseDAO(Connection connection);

    public abstract FacultyDAO getFacultyDAO(Connection connection);

    public abstract StudentDAO getStudentDAO(Connection connection);

    public abstract TeacherDAO getTeacherDAO(Connection connection);

    public abstract UserDAO getUserDAO(Connection connection);
}
