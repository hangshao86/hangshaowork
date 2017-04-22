package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//这个类是用来将发送到客户端的图片进行Base64编码处理，同时将客户端图片进行Base64解码


public class ImageOutput { 
	String imagePath=null;
	public HttpServletResponse resp;
	public ImageOutput(String imagePath,HttpServletResponse resp){
		this.imagePath=imagePath;
		this.resp = resp;
}
	
//取得文件并输出输出文件流
public  void  printImage(){ 
File file =new File(imagePath); 
try {
	InputStream inputstream = new FileInputStream(file); 
	OutputStream out = resp.getOutputStream();
	//拷贝输出
			byte[] buf = new byte[1024];
			   int count = 0;
		        while ((count =inputstream.read(buf)) >= 0) {
		            out.write(buf, 0, count);
		        }
		        inputstream.close();
		        out.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
} 

//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
public  String GetImageStr() {
 InputStream in = null;
 byte[] data = null;
 //读取图片字节数组
 try {
  in = new FileInputStream(imagePath);
  data = new byte[in.available()];
  in.read(data);
  in.close();
 } catch (IOException e) {
  e.printStackTrace();
 }
 //对字节数组Base64编码
 BASE64Encoder encoder = new BASE64Encoder();
 return encoder.encode(data);			//返回Base64编码过的字节数组字符串
}


//将Base64字符串编码转换为图片存储在硬盘空间
public static boolean GenerateImage(String imgStr)
{//对字节数组字符串进行Base64解码并生成图片
    if (imgStr == null) //图像数据为空
        return false;
    BASE64Decoder decoder = new BASE64Decoder();
    try 
    {
        //Base64解码
        byte[] b = decoder.decodeBuffer(imgStr);
        for(int i=0;i<b.length;++i)
        {
            if(b[i]<0)
            {//调整异常数据
                b[i]+=256;
            }
        }
        //生成jpeg图片
        String imgFilePath = "d:\\222.jpg";//新生成的图片
        OutputStream out = new FileOutputStream(imgFilePath);    
        out.write(b);
        out.flush();
        out.close();
        return true;
    } 
    catch (Exception e) 
    {
        return false;
    }
}
}
