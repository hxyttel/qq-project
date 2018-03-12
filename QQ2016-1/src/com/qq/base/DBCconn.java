package com.qq.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCconn {
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localHost:1433;DatabaseName =QQ2016"; 
	private static String username = "sa";
	private static String userpassword = "sehun";
	private static Connection conn = null;
	
	//静态语气快，连接数据库
	static{
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,userpassword);
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	public static Connection openDB(){
		try {
			//isClosed()作用：判断连接状态，判断数据库是否连接，默认为false
			if(conn.isClosed()){
				//如果没连接，就在连接
				conn=DriverManager.getConnection(url,username,userpassword);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//如果连接了，就返回con
		return conn;
		
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*1.静太代码块里面的内容会在创建对象之前去执行
		 *2.当类的静态方法被调用时，会先执行静态代码块里面的内容
		 * */
		Connection conn = DBCconn.openDB();
	}
}
