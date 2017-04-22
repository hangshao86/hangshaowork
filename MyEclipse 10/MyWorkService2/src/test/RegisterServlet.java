package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("username");
		String psd = request.getParameter("psd");
		String email=request.getParameter("email");
		String dormitory=request.getParameter("dormitory");
		System.out.println("name=" + name + ",psd=" + psd);
		DBUtil dbUtil = new DBUtil();
		boolean result=dbUtil.add("insert into user(user_name,user_psd,user_email,user_dormitory)values(?,?,?,?)", new String[]{name,psd,email,dormitory});
		out.print(result);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
