package com.epam.suleimenov.dao;

import java.sql.*;

/**
 * Created by admin on 6/30/2016.
 */
public class OracleFacultyDAO implements FacultyDAO {

    private Connection connection;

    public OracleFacultyDAO(Connection connection) {
        this.connection = connection;
    }

    public int getNextIdBySequence(String sequence) {

        String sql = "SELECT " + sequence + "_sequence.NEXTVAL FROM DUAL";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        return 0;
    }
}