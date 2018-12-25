package data;

import java.sql.*;
import java.util.Properties;

public class DatabaseManager implements AutoCloseable
{
    private Connection connection;

    public DatabaseManager() throws Exception
    {
        Properties props = new Properties();
        String dbName = "RzdProject";
        String host = "localhost\\SQLEXPRESS";
        String connectionString = String.format("jdbc:sqlserver://%s;integratedSecurity=true;databaseName=%s", host, dbName);
        connection = DriverManager.getConnection(connectionString, props);
    }

    ResultSet executeQuery(String sql) throws SQLException
    {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public IDAO getDao()
    {
        return new DAO(this);
    }

    @Override
    public void close() throws Exception
    {
        connection.close();
    }
}
