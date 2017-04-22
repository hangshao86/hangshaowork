package iet.jxufe.cn.servlet;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;




public class DownloadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		Map map = new HashMap();
	    map.put("id", "1");
        map.put("name", "Dana��Li");
        JSONObject jsonObject=new JSONObject();
        jsonObject.putAll(map);
        
             
		/*String path = getServletContext().getRealPath("/").trim();
		System.out.println(path);
		String result = "";
		File file = new File(path);
		File[] files = file.listFiles(myFilter);
		if (files == null || files.length == 0) {
			result = "noFile";
		} else {
			for (int i = 0; i < files.length; i++) {
				result += files[i].getName() + "*";
			}
		}*/
		out.print(jsonObject);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	FileFilter myFilter = new FileFilter() {
		public boolean accept(File pathname) {
			if (pathname.isFile()) {
				return true;
			} else {
				return false;
			}
		}
	};
}