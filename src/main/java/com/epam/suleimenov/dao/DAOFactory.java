package com.epam.suleimenov.dao;

import java.sql.Connection;

public abstract class DAOFactory {


    public static DAOFactory getDAOFactory(String whichFactory) {

        if(whichFactory.equalsIgnoreCase("facultyDAO"))
            return new FacultyDAOFactory();

        return null;
    }

    public abstract ArchiveDAO getArchiveDAO();

    public abstract CourseDAO getCourseDAO();

    public abstract FacultyDAO getFacultyDAO();

    public abstract StudentDAO getStudentDAO();

    public abstract TeacherDAO getTeacherDAO();

    public abstract UserDAO getUserDAO();

    public abstract void close();
}
