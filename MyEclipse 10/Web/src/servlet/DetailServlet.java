package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bookdao;
import dao.daoimpl.BookdaoImpl;
import entity.Book;

/**
 * Servlet implementation class DetailsServlet
 */

public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		id=(id+9)/10;
		Bookdao bookdao = new BookdaoImpl();
		Book book = bookdao.getBookBySpecialId(id); 
		String out="";
		out=book.getAuthor()+";"+book.getDetail()+";"+ book.getRemark();
		PrintWriter outs = response.getWriter();
		outs.print(out);
		outs.flush();
	}

}
