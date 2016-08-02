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
    private final String FACULTY_DAO_FACTORY = "faculty_DAO";
    private final String DAO_PROPERTY = "dao.properties";

    public Service() {

        Properties properties = new Properties();
        try {
            properties.load(Service.class.getClassLoader().getResourceAsStream(DAO_PROPERTY));
            daoFactory = DAOFactory.getDAOFactory(properties.getProperty(FACULTY_DAO_FACTORY));

        } catch (IOException e) {
            System.out.println("Service: property file is not set or found");
            e.printStackTrace();
        }
    }

    public static DAOFactory getDAOFactory() {
        return daoFactory;
    }

    @Override
    public void close() {
        if (daoFactory != null)
            daoFactory.close();
    }
}
