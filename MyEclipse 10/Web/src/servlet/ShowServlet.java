package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ImageOutput;
import dao.Bookdao;
import dao.daoimpl.BookdaoImpl;
import entity.Book;

public class ShowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// @Override
	// public void service(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// Bookdao bookdao = new BookdaoImpl();
	// List<Book> list = bookdao.findAllBook();
	// // 将内容拼接成相应格式的字符串，其中没一组之间用分号分割，每一项之间用逗号分割。图片读取其信息并采用
	// // Base64编码传输
	// String out = null;
	//
	// for (Book book : list) {
	// String pic = null;
	// pic = book.getPic();
	// System.out.print(pic);
	// ImageOutput imageoutput = new ImageOutput("/home/shenlei/tupian/"+pic,
	// resp);
	// String pictostring = imageoutput.GetImageStr();
	// out = out + book.getPrice() + "," + book.getName() + "," + pictostring
	// + ";";
	// }
	// PrintWriter outs = resp.getWriter();
	// outs.print("<html>");
	// outs.print("<body>");
	// outs.print("helloworld");
	// outs.print("</body>");
	// outs.print("</html>");
	// }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int id=Integer.parseInt(req.getParameter("id"));
		resp.setCharacterEncoding("UTF-8");
		Bookdao bookdao = new BookdaoImpl();
		List<Book> list = bookdao.getBookById(id);
		
		// 将内容拼接成相应格式的字符串，其中没一组之间用分号分割，每一项之间用逗号分割。图片读取其信息并采用
		// Base64编码传输
		String out = "";

		for (Book book : list) {
			String pic = "";
			pic = book.getPic();
			ImageOutput imageoutput = new ImageOutput("/home/shenlei/tupian/"
					+ pic, resp);
			String pictostring = imageoutput.GetImageStr();
			out = out + pictostring + "," + book.getPrice() + ","
					+ book.getName() + ";";
		}
		out = out.substring(0, out.length() - 1);
		PrintWriter outs = resp.getWriter();
		outs.print(out);
		outs.flush();
	}
}
