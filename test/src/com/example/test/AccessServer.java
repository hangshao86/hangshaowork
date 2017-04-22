package com.example.test;

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

import android.util.Log;
import android.widget.Toast;


public class AccessServer {
	private String url;
	String result="";
	public AccessServer(String url){
		this.url=url;
	}
	public String doGet(String[] keys,String[] values){
		HttpClient hc=new DefaultHttpClient();
		String urlStr=url;
		
		if(keys!=null && values!= null){
			urlStr+="?";
			for(int i=0;i<keys.length;i++){
				urlStr+=keys[i]+"="+values[i];
				if(i!=keys.length-1){
					urlStr+="&";
				}
			}
		}
		HttpGet hg=new HttpGet(urlStr);
		try{
			HttpResponse response=hc.execute(hg);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
			{
				HttpEntity het=response.getEntity();
				InputStream is=het.getContent();
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				String readLine=null;
				while((readLine=br.readLine())!=null)
				{
					result+=readLine;
				}
				is.close();
				return result;
			}else{
				return "error";
				
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return "exception";
		}
		
		
	}
	public String doPost(String[] keys,String[] values){
		HttpClient hc=new DefaultHttpClient();
		HttpPost hp=new HttpPost(url);
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		if(keys!=null && values!= null){
			for(int i=0;i<keys.length;i++){
				NameValuePair param=new BasicNameValuePair(keys[i], values[i]);
				params.add(param);
			}
		}
		HttpEntity he;
		try{
			he=new UrlEncodedFormEntity(params,"GBK");
			hp.setEntity(he);
			HttpResponse response=hc.execute(hp);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
			{
				HttpEntity het=response.getEntity();
				InputStream is=het.getContent();
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				String readLine=null;
				while((readLine=br.readLine())!=null)
				{
					result+=readLine;
				}
				is.close();
				return result;
			}else{
				return "error";
				
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return "exception";
		}
		
		
	}
}
