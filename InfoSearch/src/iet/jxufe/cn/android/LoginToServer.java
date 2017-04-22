package iet.jxufe.cn.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class LoginToServer {
	private String url = "http://10.0.2.2:8080/login/LoginServlet";
	String result = "";

	public String doGet(String name, String psd) {
		HttpClient hc = new DefaultHttpClient();
		String urlStr = url + "?username=" + name + "&psd=" + psd;
		HttpGet hg = new HttpGet(urlStr);
		try {
			HttpResponse response = hc.execute(hg);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity het = response.getEntity();
				InputStream is = het.getContent();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					result = result + readLine;
				}
				is.close();
			} else {
				result = "error";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "exception";
		}
		return result;
	}

	public String doPost(String name, String psd) {
		HttpClient hc = new DefaultHttpClient();
		HttpPost hp = new HttpPost(url);
		NameValuePair param1 = new BasicNameValuePair("username", name);
		NameValuePair param2 = new BasicNameValuePair("psd", psd);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(param1);
		params.add(param2);
		HttpEntity he;
		try {
			he = new UrlEncodedFormEntity(params, "GBK");
			hp.setEntity(he);
			HttpResponse response = hc.execute(hp);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity het = response.getEntity();
				InputStream is = het.getContent();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					result = result + readLine;
				}
				is.close();
			} else {
				result = "error";

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "exception";
		}
		return result;
	}

}
