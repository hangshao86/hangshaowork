package iet.jxufe.cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoQueryServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");		
		PrintWriter out = response.getWriter();
		String bookStr = request.getParameter("book");
		String book=null,grade=null;
		String gradeStr = request.getParameter("grade");		
		String result = "";
		DBUtil dbUtil = new DBUtil();
		if (gradeStr!= null) {
			grade=new String(gradeStr.getBytes("ISO-8859-1"),"UTF-8");
			result=dbUtil.query(
							"select * from grade where num like ? or course_name like ?",
							new String[] { "%" + grade + "%", "%" + grade + "%" });
		} else if (bookStr!= null)  {
			book=new String(bookStr.getBytes("ISO-8859-1"),"UTF-8");
			result=dbUtil.query(
					"select * from book where book_name like ? or book_author like ?",
					new String[] { "%" + book + "%", "%" + book + "%" });
		}
		System.out.println("book=" + book + ",psd=" + grade);
		out.print(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
