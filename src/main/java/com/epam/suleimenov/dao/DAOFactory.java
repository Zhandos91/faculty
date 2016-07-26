package com.epam.suleimenov.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Zhandos_Suleimenov on 7/25/2016.
 */
public interface DAOFactory {

    ArchiveDAO getArchiveDAO(Connection connection);
    CourseDAO getCourseDAO(Connection connection);
    FacultyDAO getFacultyDAO(Connection connection);
    StudentDAO getStudentDAO(Connection connection);
    TeacherDAO getTeacherDAO(Connection connection);
    UserDAO getUserDAO(Connection connection);
}
