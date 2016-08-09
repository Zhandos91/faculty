package com.epam.suleimenov.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    void setCheckConnections(boolean checkConnections);
    void setMaxConn(int maxConn);
    void freeConnection(Connection connection);
    int getPoolSize();
    void drain();
    Connection getConnection() throws SQLException;
    public int getFreeConnections();
    public int getBusyConnections();

}
