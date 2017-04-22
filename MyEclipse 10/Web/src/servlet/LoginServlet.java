package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("hello world");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String pwd=request.getParameter("passwd");
		JSONObject json = new JSONObject();
		json.put("success", "success");
		json.put("name", name);
		json.put("passwd",pwd);
		PrintWriter out=response.getWriter();
		out.print(json);
	}

}
