package util;

import util.JdbcUtils;

import java.sql.*;

public class testJdbc {

    public static void main(String[] args) {
//        //1.加载驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        //2.数据库 url 和 用户信息
//        String url = "jdbc:mysql://localhost:3306/spider_sql?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";
//        String name = "root";
//        String pwd = "123456";
//
//        //3.连接 connection 代表数据库, 可以设置数据库自动提交,
//        Connection connection = DriverManager.getConnection(url, name, pwd);
//
//        //事务提交, 事务回滚
//        connection.commit();
//        connection.rollback();
//        connection.setAutoCommit(true);
//
//        //4.执行sql的对象 statement
//        Statement statement = connection.createStatement();
//
//        //5.用执行sql的对象去执行sql 可能存在结果，查看返回结果
//        String sql = "select * from student";
//
//        // 返回的结果集，结果集封装了所有的查询的结果
//        ResultSet resultSet = statement.executeQuery(sql);
//
//        // 执行所有sql
//        //statement.execute()
//
//        // 更新，插入，删除都用这个，返回受影响的行数
//        //statement.executeUpdate()
//
//        while(resultSet.next()){
//            System.out.println("studentno=" + resultSet.getString("studentno"));
//            System.out.println("studentname=" + resultSet.getString("studentname"));
//            System.out.println("address=" + resultSet.getString("address"));
//        }
//
//        //6.释放资源
//        resultSet.close();
//        statement.close();
//        connection.close();


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        // 1.
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "select * from student";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                System.out.println("studentno=" + resultSet.getString("studentno"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(connection, statement, resultSet);
        }
    }
}
