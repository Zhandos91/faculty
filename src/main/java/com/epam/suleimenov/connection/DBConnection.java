package com.epam.suleimenov.connection;

import com.epam.suleimenov.dao.OracleDAOFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String dbPath;
    private static String dbUser;
    private static String dbPassword;
    private static String dbDriver;

    static {
        Properties properties = new Properties();

        try {
            properties.load(OracleDAOFactory.class.getClassLoader().getResourceAsStream("db.properties"));
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

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbPath, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {

        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}