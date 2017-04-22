package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sqlutil {
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/book";
	public static final String USER = "root";
	public static final String PASSWD = "shenlei";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static Connection sqlconn() throws SQLException {

		try {

			// 驱动加载
			Class.forName(DRIVER);

			// 连接数据库
			Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			return conn;


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动加载失败");
			e.printStackTrace();
		}
		return null;

	}

}
