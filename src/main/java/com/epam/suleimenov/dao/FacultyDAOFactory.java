package com.epam.suleimenov.dao;

import com.epam.suleimenov.connection.DBConnection;
import com.epam.suleimenov.connection.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class FacultyDAOFactory extends DAOFactory {

    private final Connection connection;

    public FacultyDAOFactory() {
        connection  = DBConnectionPool.getConnection();
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
