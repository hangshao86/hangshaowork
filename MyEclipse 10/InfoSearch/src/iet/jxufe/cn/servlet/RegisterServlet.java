package iet.jxufe.cn.servlet;

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
		System.out.println("name=" + name + ",psd=" + psd);
		DBUtil dbUtil = new DBUtil();
		boolean result=dbUtil.add("insert into user(user_name,user_psd)values(?,?)", new String[]{name,psd});
		out.print(result);
		// if("gao".equals(name)&&"gao".equals(psd)){
		// out.print(true);
		// }else{
		// out.print(false);
		// }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
