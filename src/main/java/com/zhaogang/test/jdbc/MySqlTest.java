package com.zhaogang.test.jdbc;


import java.sql.*;

/**
 * Created by guangyu.liu on 2018/8/17.
 */
public class MySqlTest {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.0.34.49:3306/scm_capital?useUnicode=true&characterEncoding=UTF8";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "ua_scm_test";
    static final String PASS = "V0N%JR@UTtdE";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "select p.PKID,s.* from scm_pur.po p, scm_sale.so s\n" +
                    "where p.PKID = s.PKID";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("PKID");
                String name = rs.getString("TT_NAME");
                String url = rs.getString("CODE");

                // 输出数据
                System.out.print("PKID: " + id);
                System.out.print(", TT_NAME: " + name);
                System.out.print(", CODEL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
