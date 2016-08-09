package com.epam.suleimenov.dao;

import com.epam.suleimenov.connection.MyDBConnectionPool;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;

public class FacultyDAOFactory extends DAOFactory {

    private Logger log = LoggerFactory.getLogger(FacultyDAOFactory.class.getName());
    private Connection connection = null;

    public FacultyDAOFactory() {
        try {
            connection  = MyDBConnectionPool.getInstanceholder().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public <T> GenericDAO<T> createDAO(Class<T> myClass) {
        switch (myClass.getSimpleName()) {
            case "Archive":
                return (GenericDAO<T>) new ArchiveDAOImpl(connection);
            case "Course":
                return (GenericDAO<T>) new CourseDAOImpl(connection);
            case "User":
                return (GenericDAO<T>) new UserDAOImpl(connection);
            default:
                return null;
        }
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
