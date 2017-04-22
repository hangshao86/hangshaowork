package edu.whu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpUtil implements Runnable {
	public static String httpUrl = "http://192.168.1.139:8080/Web/";
	public static String result = "";
	public static Bitmap bitmap = null;
	public String url = null;
	List<NameValuePair> list;

	public HttpUtil(String url, List<NameValuePair> list) {
		this.list = list;
		this.url = url;
	}

	@Override
	public void run() {
		try {
			// http post连接对象url
			HttpPost httppost = new HttpPost(url);
			// 设置字符集
			HttpEntity httpentity = new UrlEncodedFormEntity(list, "utf-8");
			// 设置查询参数
			httppost.setEntity(httpentity);
			// 创建一个http连接实例
			HttpClient httpclient = new DefaultHttpClient();
			// 取得默认的查询结果实例
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的字符串
				 result = EntityUtils.toString(response.getEntity(),"utf-8");
			//	 bitmap=BitmapFactory.decodeFile(result);
				 
			/*	// 取得图片字节流
				HttpEntity httpEntity = response.getEntity();
				InputStream is =  httpEntity.getContent();
				bitmap = BitmapFactory.decodeStream(is);
				is.close();*/
			} else {
				result = "请求错误!";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
