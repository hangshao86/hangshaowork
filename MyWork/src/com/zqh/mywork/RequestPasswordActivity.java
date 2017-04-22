package com.zqh.mywork;

import servers.AccessServer;

import com.example.mywork.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RequestPasswordActivity extends Activity {
	EditText user_name;
	EditText user_email;
	EditText user_newpsd;
	EditText user_newpsdagain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_password_activity);
		user_name=(EditText) findViewById(R.id.requestpassword_etname);
		user_email=(EditText) findViewById(R.id.requestpassword_et_mailboxname);
		user_newpsd=(EditText) findViewById(R.id.requestpassword_etpassword);
		user_newpsdagain=(EditText) findViewById(R.id.requestpassword_again_etpassword);
		
	}
	public void OnClick(View view){
		if(!TextUtils.isEmpty(user_name.getText().toString()) && 
		   !TextUtils.isEmpty(user_email.getText().toString()) &&
		   !TextUtils.isEmpty(user_newpsd.getText().toString()) &&
		   !TextUtils.isEmpty(user_newpsdagain.getText().toString()))
		{
			 final  Builder builder=new AlertDialog.Builder(this);
				builder.setTitle("修改密码成功提示");
				builder.setMessage("修改密码成功！");
				builder.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent selectIntent=new Intent(RequestPasswordActivity.this,Login.class);
						startActivity(selectIntent);
					}
				});
			final Handler myHandler=new Handler(){
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					String response=(String)msg.obj;
					System.out.println(response);
					if("true".equals(response))
					{
						builder.create().show();
						
					}else
					{
						Toast.makeText(RequestPasswordActivity.this,"修改失败！", Toast.LENGTH_SHORT).show();
					}
				}
				
			};
			new Thread(new Runnable() {
				public void run() {
					AccessServer access=new AccessServer("http://10.0.2.2:8080/test/AlterPsdServlet");
					String result=access.doPost(new String[]{"username","email","newpsd","newpsdagain"},new String[]{user_name.getText().toString(),user_email.getText().toString(),
							                     user_newpsd.getText().toString(),user_newpsdagain.getText().toString()});
					Message msg=new Message();
					msg.obj=result;
					
					myHandler.sendMessage(msg);
				}
			}).start();	
		}
		else {
			
		}
	}

}
