package iet.jxufe.cn.servlet;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownFile extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fileName=request.getParameter("fileName");
		String path = getServletContext().getRealPath("/").trim();
		String result = "";
		result+=fileName+"*";
		File file = new File(path+File.separator+fileName);
		try{			
			FileInputStream fis=new FileInputStream(file);
			byte[] buffer=new byte[64];
			int hasRead;
			while((hasRead=fis.read(buffer))!=-1){
				result+=new String(buffer,0,hasRead);
			}
			if(fis!=null){
				fis.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		out.print(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
