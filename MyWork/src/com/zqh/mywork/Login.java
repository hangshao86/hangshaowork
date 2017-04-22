package com.zqh.mywork;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Struct;
import java.util.logging.LogRecord;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import servers.AccessServer;

import com.example.mywork.R;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private EditText et_username,et_password;
	private CheckBox cb_savepwd,cb_autologin;
	private TextView tv_errortip_username,tv_error_password;
	private MySQL dbHelper;
	private SQLiteDatabase  db;
	
	static String YES = "yes";
	static String NO = "no";
	static String name, password,check1,check2;
	private String checktemp="",checktemp2="";
	private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
	private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
	private SharedPreferences sp = null;//声明一个SharedPreferences
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);	
		et_username=(EditText)findViewById(R.id.et_username);
		et_password=(EditText)findViewById(R.id.et_pwd);
		cb_savepwd=(CheckBox)findViewById(R.id.savepwd);
		cb_autologin=(CheckBox) findViewById(R.id.autologin);
		tv_error_password=(TextView) findViewById(R.id.login_etpassword);
		tv_errortip_username=(TextView) findViewById(R.id.login_etname);
		 dbHelper=new MySQL(this, "User.db", null, 1);
		db=dbHelper.getWritableDatabase();
		savepassword();
		et_username.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(et_username.getText()))
				{
					tv_errortip_username.setVisibility(View.INVISIBLE);
				}
				else
				{
					tv_errortip_username.setText("*用户名输入不能为空");
					tv_errortip_username.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	et_password.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if(!TextUtils.isEmpty(et_password.getText()))
			{
				tv_error_password.setVisibility(View.INVISIBLE);
			}
			else
			{
				tv_error_password.setText("*密码不能为空");
				tv_error_password.setVisibility(View.VISIBLE);
			}
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	});
	}
	private void savepassword()
	{
		sp = getSharedPreferences(FILE, MODE_PRIVATE);
		isMemory = sp.getString("isMemory", NO);
		
		if(isMemory.equals(YES))
		{
			name = sp.getString("name", "");
			password = sp.getString("password", "");
			checktemp=sp.getString("check1","false");
			et_username.setText(name);
			et_password.setText(password);
			if(checktemp=="true")
				cb_savepwd.setChecked(true);
		}
		   
	}
	public void onsavepassword() {
		if (cb_savepwd.isChecked()) {
		if (sp == null) {
		sp = getSharedPreferences(FILE, MODE_PRIVATE);
		}
		checktemp="true";
		Editor edit = sp.edit();
		edit.putString("name", et_username.getText().toString());
		edit.putString("password", et_password.getText().toString());
		edit.putString("isMemory", YES);
		edit.putString("check1", checktemp);
		edit.commit();
		} 
		else if (!cb_savepwd.isChecked())
		{
		if (sp == null) {
		sp = getSharedPreferences(FILE, MODE_PRIVATE);
		}
		Editor edit = sp.edit();
		edit.putString("isMemory", NO);
		edit.commit();
		}
	}
	public void OnClick(View view)
	{
		switch (view.getId()) {
		case R.id.btn_login:
			onsavepassword();
			if(TextUtils.isEmpty(et_username.getText()))
			{
				tv_errortip_username.setText("*用户名不能为空");
				tv_errortip_username.setVisibility(View.VISIBLE);
			}
			else if(TextUtils.isEmpty(et_password.getText()))
			{
				tv_error_password.setText("*密码不能为空");
				tv_error_password.setVisibility(View.VISIBLE);
			}
			else {
				final Handler myHandler=new Handler(){
					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						String response=(String)msg.obj;
						System.out.println(response);
						Toast.makeText(Login.this,"dd="+response, Toast.LENGTH_SHORT).show();
						if("true".equals(response))
						{
							Intent selectIntent=new Intent(Login.this,TActivity.class);
							startActivity(selectIntent);
						}else
						{
							Toast.makeText(Login.this,"用户名和密码不匹配！", Toast.LENGTH_SHORT).show();
						}
					}
					
				};
				new Thread(new Runnable() {
					public void run() {
						AccessServer access=new AccessServer("http://10.0.2.2:8080/test/LoginServlet");
						String result=access.doPost(new String[]{"username","psd"},new String[]{et_username.getText().toString(),et_password.getText().toString()});
						Message msg=new Message();
						msg.obj=result;
						
						myHandler.sendMessage(msg);
					}
				}).start();	
				
		
			}
			
			
			break;

		case R.id.btn_register:
			Intent registerIntent=new Intent(this,RegisterActivity.class);
			startActivity(registerIntent);
			break;
		case R.id.getpwd:
			Intent requestpasswordIntent=new Intent(this,RequestPasswordActivity.class);
			startActivity(requestpasswordIntent);
			break;
		}
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
