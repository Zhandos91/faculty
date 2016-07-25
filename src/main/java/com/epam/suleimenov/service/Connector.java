package com.epam.suleimenov.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static String dbPath;
    private static String dbUser;
    private static String dbPassword;
    private static String dbDriver;

    static {
        Properties properties = new Properties();

        try {
            properties.load(Connector.class.getClassLoader().getResourceAsStream("db.properties"));
            dbDriver = properties.getProperty("db_driver");
            dbPath = properties.getProperty("db_path");
            dbUser = properties.getProperty("db_user");
            dbPassword = properties.getProperty("db_password");

        } catch (IOException e) {
            System.out.println("Property file is not set or found");
            e.printStackTrace();
        }

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver is not found");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbPath, dbUser, dbPassword);
    }

}
