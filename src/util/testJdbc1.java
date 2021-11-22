package util;

import java.sql.*;

public class testJdbc1 {
    public static void main(String[] args){

        Connection connection = null;

        // 用 PreparedStatement 替代 Statement
        PreparedStatement preparedStatement = null;

        PreparedStatement preparedStatement1 = null;
//        Statement statement = null;
        ResultSet resultSet = null;
//        String username = "' or ' 1 = 1 ";
//        String pwd = "' or ' 1 = 1";

        // 1.
        try {

            // 加载驱动
            connection = JdbcUtils.getConnection();

            // 关闭自动提交
            connection.setAutoCommit(false);

//            String sql1="SELECT * FROM `users` WHERE `NAME`= "+"'"+username+"'"+" AND `loginpwd`="+"'"+pwd+"'";

            /**
             * 查询
             */
//            String sql1 = "select * from student where studentname = ? and loginpwd = ?";
//            preparedStatement1 = connection.prepareStatement(sql1);
//            preparedStatement1.setString(1, "张伟");
//            preparedStatement1.setString(2, "456789");
//            resultSet = preparedStatement1.executeQuery();
//
//            while(resultSet.next()){
//                System.out.println("studentno=" + resultSet.getString("studentno"));
//            }

            /**
             * 更新
             */
            String sql = "update student set loginpwd =? where studentname =?";
            // 预编译SQL
            preparedStatement = connection.prepareStatement(sql);
            // 添加参数
            preparedStatement.setString(1, "10");
            preparedStatement.setString(2, "张伟");
            preparedStatement.executeUpdate();
//            int s = 1/0;
            connection.commit();

//            String sql = "update `account` set money = money - 500 where `name` = 'a'";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.executeUpdate();

//            String sql1 = "update `account` set money = money + 500 where `name` = 'b'";

        } catch (Exception throwables) {
            try {
                // 失败则回滚
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JdbcUtils.release(connection, preparedStatement, resultSet);
        }
    }
}
