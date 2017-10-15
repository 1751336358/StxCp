package com.stx.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class JdbcUtils{
	private static Properties config = new Properties();	//����ģʽ
	static{	//��̬����飬��ȡ��Դ�ļ�����������
		try{
			config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));	//��̬����飬��ȡ�����ļ�
			System.out.println(config.getProperty("jdbc.driver"));
			System.out.println(config.getProperty("jdbc.username"));
			System.out.println(config.getProperty("jdbc.password"));
			System.out.println(config.getProperty("jdbc.url"));
			Class.forName(config.getProperty("jdbc.driver"));	//��������
			
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{	//��������
		
		return DriverManager.getConnection(config.getProperty("jdbc.url"),config.getProperty("jdbc.username"),config.getProperty("jdbc.password"));
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs){	//�ͷ���Դ
		if(rs != null){
			try{
				rs.close();
			}catch(Exception e){}
		}
		if(st != null){
			try{
				st.close();
			}catch(Exception e){}
		}
		if(conn != null){
			try{
				conn.close();
			}catch(Exception e){}
		}
	}
}