package com.example.test;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;


import android.R.integer;
import android.app.Activity;
import android.app.UiAutomation.OnAccessibilityEventListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ImageView iV;
    private Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iV=(ImageView) findViewById(R.id.iv);
		
	  new myAsyncTask().execute("http://10.0.2.2:8080/test/a.jpg");
	/*	final Handler myHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				String response=(String)msg.obj;
			    byte[] decodedString = Base64.decode(response, Base64.DEFAULT);
			    iV.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length));
			   // iV.setImageBitmap(bitmap);
				
				
			}
			
		};
		new Thread(new Runnable() {
			public void run() {
				AccessServer access=new AccessServer("http://10.0.2.2:8080/test/downloadImageServlet");
				String result=access.doPost(new String[]{"filename"},new String[]{"a.jpg"});
				Message msg=new Message();
				msg.what=3;
				msg.obj=result;
				
				myHandler.sendMessage(msg);
			}
		}).start();*/
	}
    class myAsyncTask extends AsyncTask<String , Void, Bitmap>
    {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			/*AccessServer access=new AccessServer(params[0]);
			String result=access.doPost(new String[]{"filename"},new String[]{"a.jpg"});
			byte[] decodedString = Base64.decode(result, Base64.DEFAULT);*/
			Bitmap bitmap=getHttpBitmap(params[0]);
		    //byte[] imagebyte=result.getBytes();
		    return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			// BitmapFactory.decodeByteArray(result, 0,result.length);
		//	 iV.setImageBitmap(BitmapFactory.decodeByteArray(result, 0,result.length));
			iV.setImageBitmap(result);
			 
			 
		}
    	
    }
    public static Bitmap getHttpBitmap(String url){

        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }        
        return bitmap;
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
