package iet.jxufe.cn.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBUtil {
	private String dbName = "infosearch";
	private String url = "jdbc:mysql://localhost:3306/" + dbName;
	private String user = "root";
	private String psd = "1234";
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, psd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean checkUser(String sql, String[] args) {
		boolean flag = false;
		conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setString(i + 1, args[i]);
				}
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean add(String sql, String[] args) {
		boolean flag = false;
		conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setString(i + 1, args[i]);
				}
			}
			int i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String query(String sql, String[] args) {
		String result = "";
		conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setString(i + 1, args[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < count; i++) {
					result += rs.getString(i+1) + "*";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
