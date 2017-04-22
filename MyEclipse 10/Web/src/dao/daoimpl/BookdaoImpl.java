package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Sqlutil;
import dao.Bookdao;
import entity.Book;

public class BookdaoImpl implements Bookdao {
	
	@SuppressWarnings("null")
	@Override
	public   List<Book> findAllBook() {
		List<Book> list=new ArrayList<Book>() ;

		// TODO Auto-generated method stub
	try {
		
		Connection conn = Sqlutil.sqlconn();
		Statement statement=  conn.createStatement();
		ResultSet rs=statement.executeQuery("select * from booktbl");
		while (rs.next()){
			int id=rs.getInt("id");
			float price =rs.getFloat("price");
			String pic=rs.getString("pic");
			String author = rs.getString("author");
			String detail =rs.getString("detail");
			String name = rs.getString("name");
			Book book =new Book();
			book.setId(id);
			book.setAuthor(author);
			book.setDetail(detail);
			book.setPic(pic);
			book.setPrice(price);
			book.setName(name);
			list.add(book);
		}
		rs.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		return list;
	}
	@Override
	public List<Book> getBookById(int times) {
		List<Book> list=new ArrayList<Book>() ;
			// TODO Auto-generated method stub
		try {

			Connection conn = Sqlutil.sqlconn();
			//Statement statement= conn.createStatement();
			String sql="select * from booktbl where id between ? and ?";
			PreparedStatement ps=  conn.prepareStatement(sql);
			ps.setInt(1,times*8-7);
			ps.setInt(2,times*8);
	         ResultSet rs=ps.executeQuery();
			while (rs.next()){
				int id=rs.getInt("id");
				float price =rs.getFloat("price");
				String pic=rs.getString("pic");
				String author = rs.getString("author");
				String detail =rs.getString("detail");
				String name = rs.getString("name");
				Book book =new Book();
				book.setId(id);
				book.setAuthor(author);
				book.setDetail(detail);
				book.setPic(pic);
				book.setPrice(price);
				book.setName(name);
				list.add(book);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			return list;
	}
	@Override
	public Book getBookBySpecialId(int num) {
		Book book=new Book();
		// TODO Auto-generated method stub
	try {

		Connection conn = Sqlutil.sqlconn();
		//Statement statement= conn.createStatement();
		String sql="select * from booktbl where id = ?";
		PreparedStatement ps=  conn.prepareStatement(sql);
		ps.setInt(1,num);
         ResultSet rs=ps.executeQuery();
		while (rs.next()){
			int id=rs.getInt("id");
			float price =rs.getFloat("price");
			String pic=rs.getString("pic");
			String author = rs.getString("author");
			String detail =rs.getString("detail");
			String name = rs.getString("name");
			float remark =rs.getFloat("remark");
			book.setRemark(remark);
			book.setId(id);
			book.setAuthor(author);
			book.setDetail(detail);
			book.setPic(pic);
			book.setPrice(price);
			book.setName(name);
		}
		rs.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return book;
		
	}
			
}
