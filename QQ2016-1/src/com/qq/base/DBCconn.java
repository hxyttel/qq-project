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
	
	//��̬�����죬�������ݿ�
	static{
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,userpassword);
			System.out.println("���ݿ����ӳɹ�");
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	public static Connection openDB(){
		try {
			//isClosed()���ã��ж�����״̬���ж����ݿ��Ƿ����ӣ�Ĭ��Ϊfalse
			if(conn.isClosed()){
				//���û���ӣ���������
				conn=DriverManager.getConnection(url,username,userpassword);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//��������ˣ��ͷ���con
		return conn;
		
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*1.��̫�������������ݻ��ڴ�������֮ǰȥִ��
		 *2.����ľ�̬����������ʱ������ִ�о�̬��������������
		 * */
		Connection conn = DBCconn.openDB();
	}
}
