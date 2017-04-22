package dao;

import java.util.List;

import entity.Book;

public interface Bookdao {
		public   List<Book> findAllBook();
		public List<Book>getBookById(int id);//根据分组获取书的信息
		public Book getBookBySpecialId(int id);
}
