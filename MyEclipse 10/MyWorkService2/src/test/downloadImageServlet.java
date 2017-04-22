package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import sun.misc.BASE64Encoder;

public class downloadImageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public downloadImageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	/*public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fileName=request.getParameter("filename");
		String path = getServletContext().getRealPath("/").trim();
		byte[] buffer=new byte[10240];
		String result = "";
		//result=getImageBinary(path+File.separator+fileName);
		result+=fileName+"*";
		File file = new File(path+File.separator+fileName);
		try{			
			FileInputStream fis=new FileInputStream(file);
			
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
		System.out.print(result);
		out.print(result);
		
	}*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		String fileName=request.getParameter("filename");
		String path = getServletContext().getRealPath("/").trim();
		System.out.print(path+File.separator+fileName);
		out.print(imageToBase64(path+File.separator+fileName));
	}
	public static String imageToBase64(String path) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
	    byte[] data = null;
	    // ��ȡͼƬ�ֽ�����
	    try {
	        InputStream in = new FileInputStream(path);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // ���ֽ�����Base64����
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);// ����Base64��������ֽ������ַ���
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                 this.doGet(request, response);
		
			
	}
	
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}