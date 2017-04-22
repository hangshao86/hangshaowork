package iet.jxufe.cn.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

public class UploadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//String file=new String(request.getParameter("fileName").getBytes("ISO-8859-1"),"UTF-8");
		String fileName=request.getParameter("fileName");
		String content=request.getParameter("content");	
		String path=getServletContext().getRealPath("/").trim();
		boolean flag=createFile(fileName, content, path);
		System.out.println("fileName="+fileName+",contetn="+content);
		System.out.println(path);
		out.print(flag);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
	public boolean createFile(String fileName,String content,String path){
		try{
			File newFile=new File(path,fileName);
			if(!newFile.exists()){
				newFile.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(newFile);
			fos.write(content.getBytes());
			if(fos!=null){
				fos.close();
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
