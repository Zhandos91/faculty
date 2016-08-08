package com.epam.suleimenov.servlet;

import com.epam.suleimenov.connection.MyDBConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class SessionListener implements HttpSessionListener {
    private Logger log = LoggerFactory.getLogger(SessionListener.class.getName());
    private Connection connection;


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        try {
            connection =  MyDBConnectionPool.getInstanceholder().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.debug("Session in");
        System.out.println("Session in");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.debug("Session out");
        System.out.println("Session out");
    }
}
