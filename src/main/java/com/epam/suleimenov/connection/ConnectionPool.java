package com.epam.suleimenov.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    void setCheckConnections(boolean checkConnections);
    void setMaxConn(int maxConn);
    void freeConnection(MyDBConnectionPool.PooledConnection connection);
    int getPoolSize();
    void drain();
    Connection getConnection() throws SQLException;

}
