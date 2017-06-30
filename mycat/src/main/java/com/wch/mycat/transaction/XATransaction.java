package com.wch.mycat.transaction;

import java.sql.*;

/**
 * @author weichunhe
 *         Created on 2017/4/12.
 * @version 1.0
 */
public class XATransaction {
    static final String url = "jdbc:mysql://10.5.16.14:8066/TESTDB", user = "root", pwd = "123456";

    public static void main(String[] args) {

        query();
    }

    public static void transaction() {
        Connection con = null; //定义一个MYSQL链接对象
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection(url, user, pwd); //链接本地MYSQL
            System.out.print("yes");
        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }

        try {
            con.setAutoCommit(false);
            String sql = "UPDATE company c SET c.name = concat(c.name,'9') WHERE c.id = 3";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.executeUpdate();

            sql = "UPDATE employee c SET c.name = concat(c.name,'9') WHERE c.id = 5";
            statement = con.prepareStatement(sql);
            statement.executeUpdate();
//            Thread.sleep(30000);
            con.commit();
            con.close();
            System.out.println("sss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void query() {
        Connection con = null; //定义一个MYSQL链接对象
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://10.65.213.14:8066/dpm?useLocalSessionState=false&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull", "reader", "reader"); //链接本地MYSQL
            System.out.print("yes");
        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }
        try {
            String sql = "SELECT SOURCE_ORDER_NO 外部订单号 from gop.t_gateway_access where SOURCE_ORDER_NO in ('2017041214390924490812404526')  ";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String lastName = rs.getString(1);
                System.out.println(lastName);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
