package com.wch.jdbc;

import java.sql.*;

/**
 * created at 2018/7/26
 * created by weichunhe
 */

/**
 * conclusion:
 *    mysql innodb TRANSACTION_REPEATABLE_READ can avoids 幻读
 *    https://github.com/Yhzhtk/note/issues/42
 */
public class Test {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.100.39:3306/sms_qas?useUnicode=true&characterEncoding=utf8", "sms_qas", "JLA-89NK");
            System.out.println("default Isolation is :" + connection.getTransactionIsolation());
            System.out.println("default autocommit is :" + connection.getAutoCommit());
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            // start the transaction
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test");
            while (resultSet.next()){
                System.out.print(resultSet.getInt(1)+":");
                System.out.print(resultSet.getInt(2)+":");
                System.out.println(resultSet.getString(3));
            }
            resultSet = statement.executeQuery("select * from test where id > 3");
            while (resultSet.next()){
                System.out.print(resultSet.getInt(1)+":");
                System.out.print(resultSet.getInt(2)+":");
                System.out.println(resultSet.getString(3));
            }
            connection.commit();
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
