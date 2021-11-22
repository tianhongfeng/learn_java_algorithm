package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {


    private static String driver = null;
    private static String url = null;
    private static String usename = null;
    private static String pws = null;


    static{
        try {
            ClassLoader classLoader = JdbcUtils.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream("resources/dp.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            usename = properties.getProperty("usename");
            pws = properties.getProperty("pws");

            // 加载驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usename, pws);
    }

    // 释放连接
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JdbcUtils jdbcUtils = new JdbcUtils();
    }
}
