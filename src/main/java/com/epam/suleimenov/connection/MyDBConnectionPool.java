package com.epam.suleimenov.connection;

import com.epam.suleimenov.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MyDBConnectionPool implements ConnectionPool {

    private static final String DB_PROPERTY = "db.properties";
    public static String dbName;
    private static Logger log = LoggerFactory.getLogger(MyDBConnectionPool.class.getName());
    private static String dbPath;
    private static String dbUser;
    private static String dbPassword;
    private static String dbDriver;

    static {
        dbDriver = Utils.getFile(DB_PROPERTY).getProperty("db_driver");
        dbPath = Utils.getFile(DB_PROPERTY).getProperty("db_path");
        dbUser = Utils.getFile(DB_PROPERTY).getProperty("db_user");
        dbPassword = Utils.getFile(DB_PROPERTY).getProperty("db_password");
        dbName = Utils.getFile(DB_PROPERTY).getProperty("db_name");

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            log.error("Oracle JDBC Driver is not found");
            e.printStackTrace();
        }
    }

    private boolean checkConnections = true;
    private boolean drained = false;
    private int maxConn = 10;
    private List<PooledConnection> pooledConnections;


    public MyDBConnectionPool() {
        pooledConnections = new ArrayList<PooledConnection>();
    }

    public static ConnectionPool getInstanceholder() {
        return InstanceHolder.myDBConnectionPool;
    }

    public void setCheckConnections(boolean checkConnections) {
        this.checkConnections = checkConnections;
    }

    public void setMaxConn(int maxConn) {
        this.maxConn = maxConn;
    }

    public Connection getConnection() throws SQLException {

        if (drained)
            throw new SQLException("ConnectionPool is drained");

        PooledConnection pooledConnection = null;
        synchronized (pooledConnections) {

            for (int i = 0; i < pooledConnections.size(); i++) {
                pooledConnection = pooledConnections.get(i);
                if (pooledConnection.use()) {

                    if (!checkConnections)
                        return pooledConnection;
                    else {

                        boolean isStatusGood = true;

                        try {
                            if (pooledConnection.isClosed() && pooledConnection.getWarnings() != null)
                                isStatusGood = false;
                        } catch (SQLException e) {
                            isStatusGood = false;
                            //ignore
                        }

                        if (isStatusGood) {
                            return pooledConnection;
                        } else {

                            try {
                                pooledConnection.expire();
                            } catch (SQLException e) {
                                //ignore
                            }
                            pooledConnections.remove(i);
                        }

                    }

                }
            }

        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbPath, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pooledConnection = new PooledConnection(connection);
        pooledConnection.use();

        synchronized (pooledConnections) {
            pooledConnections.add(pooledConnection);
        }

        return pooledConnection;
    }

    public synchronized void freeConnection(Connection connection) {

        synchronized (pooledConnections) {

            if (connection != null && pooledConnections.size() > maxConn) {
                pooledConnections.remove(connection);
                try {
                    PooledConnection pooledConnection = (PooledConnection) connection;
                    pooledConnection.expire();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public int getPoolSize() {
        synchronized (pooledConnections) {
            return pooledConnections.size();
        }
    }

    public int getFreeConnections() {
        int count = 0;
        for (PooledConnection pooledConnection : pooledConnections) {
            if (!pooledConnection.inUse())
                count++;
        }

        return count;
    }

    public int getBusyConnections() {
        int count = 0;
        for (PooledConnection pooledConnection : pooledConnections) {
            if (pooledConnection.inUse())
                count++;
        }

        return count;
    }

    public void drain() {
        drained = true;

        synchronized (pooledConnections) {
            for (int i = pooledConnections.size() - 1; i >= 0; i--) {
                PooledConnection pooledConnection = pooledConnections.get(i);

                if (pooledConnection.inUse()) {
                    log.info("Forced closing of a pooled connection");
                    pooledConnection.printStackTrace();
                }

                pooledConnections.remove(i);
                try {
                    pooledConnection.expire();
                } catch (SQLException e) {
                    //ignore
                }

            }
        }

    }

    private static class InstanceHolder {
        static final MyDBConnectionPool myDBConnectionPool = new MyDBConnectionPool();
    }

    class PooledConnection implements Connection {

        protected Connection connection;
        protected boolean inUse;
        protected boolean autoCommit;
        protected long timeOpened;
        protected long timeClosed;
        private Throwable throwable;

        public PooledConnection(Connection connection) {
            this.connection = connection;
            inUse = false;
            autoCommit = true;
        }

        public synchronized boolean use() {

            if (inUse)
                return false;
            else {
                inUse = true;
                timeOpened = System.currentTimeMillis();
                return true;
            }
        }

        public boolean inUse() {
            return inUse;
        }

        public synchronized long getTimeOpened() {
            return timeOpened;
        }

        public synchronized long getTimeClosed() {
            return timeClosed;
        }

        public void expire() throws SQLException {
            connection.close();
            connection = null;
        }

        public void printStackTrace() {
            throwable.printStackTrace(System.err);
        }

        @Override
        public synchronized void close() throws SQLException {

            if (inUse) {
                timeClosed = System.currentTimeMillis();
                inUse = false;

                if (autoCommit == false)
                    setAutoCommit(true);
            }

            freeConnection(this);

        }

        @Override
        public Statement createStatement() throws SQLException {
            throwable = new Throwable();
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            throwable = new Throwable();
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
            autoCommit = connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
    }
}


