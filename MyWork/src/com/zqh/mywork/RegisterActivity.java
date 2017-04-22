package com.zqh.mywork;

import servers.AccessServer;

import com.example.mywork.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Element;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity  extends Activity{
	private MySQL dbHelper;
	private SQLiteDatabase  db;
	private EditText register_etname;
	private EditText register_et_mailboxname;
	private EditText register_et_dormitoryname;
	private EditText register_etpassword;
	private EditText register_again_etpassword;
	private TextView register_errortip_name;
	private TextView register_errortip_mailbox;
	private TextView register_errortip_dormitory;
	private TextView register_errortip_password;
	private TextView register_errortip_again_password;
	private static int countsofdormitory=4;
	
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	   // TODO Auto-generated method stub
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.register_activity);
	   register_etname=(EditText) findViewById(R.id.register_etname);
	   register_et_mailboxname=(EditText) findViewById(R.id.register_et_mailboxname);
	   register_et_dormitoryname=(EditText) findViewById(R.id.register_et_dormitoryname);
	   register_etpassword=(EditText) findViewById(R.id.register_etpassword);
	   register_again_etpassword=(EditText) findViewById(R.id.register_again_etpassword);
	   register_errortip_name=(TextView) findViewById(R.id.register_errortip_name);
	   register_errortip_mailbox=(TextView) findViewById(R.id.register_errortip_mailbox);
	   register_errortip_dormitory=(TextView) findViewById(R.id.register_errortip_dormitory);
	   register_errortip_password=(TextView) findViewById(R.id.register_errortip_password);
	   register_errortip_again_password=(TextView) findViewById(R.id.register_errortip_again_password);
	    
	   dbHelper=new MySQL(this, "User.db", null, 1);
	   db=dbHelper.getWritableDatabase();
	  // FocusProc();
   }
   public void FocusProc()
   {
	  
	  
	   register_etname.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			 
			if(TextUtils.isEmpty(register_etname.getText()))
			{
				   register_errortip_name.setText(R.string.errortip_name2);
				   register_errortip_name.setVisibility(View.VISIBLE);
			}
			else
			{
				   register_errortip_name.setVisibility(View.INVISIBLE);
				   String sql_username="select count(*) from User2 where username='"+register_etname.getText().toString()+"'";
				   Cursor cursor=db.rawQuery(sql_username, null);
				   cursor.moveToFirst();
				   if( cursor.getLong(0)>0)
				   {
					   register_errortip_name.setText(R.string.errortip_name);
				       register_errortip_name.setVisibility(View.VISIBLE);
				   }
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
	   register_et_mailboxname.addTextChangedListener(new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(register_et_mailboxname.getText()))
			{
				register_errortip_mailbox.setText("ע�����䲻��Ϊ��");
				register_errortip_mailbox.setVisibility(View.VISIBLE);
			}
			else
			{
				
				 register_errortip_mailbox.setVisibility(View.INVISIBLE);
				 String sql_email="select count(*) from User2 where email='"+register_et_mailboxname.getText().toString()+"'";
				 Cursor cursor=db.rawQuery(sql_email, null);
				 cursor.moveToFirst();
				 if(cursor.getLong(0)>0)
				 {
					 register_errortip_mailbox.setText("ע�������Ѵ���");
					 register_errortip_mailbox.setVisibility(View.VISIBLE);
		         }
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
	   register_et_dormitoryname.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(register_et_dormitoryname.getText()))
			{
				register_errortip_dormitory.setText("ע������Ų���Ϊ��");
				register_errortip_dormitory.setVisibility(View.VISIBLE);
			}
			else
			{
				register_errortip_dormitory.setVisibility(View.INVISIBLE);
				String sqldormitory="select count(*) from User2 where dormitory='"+register_et_dormitoryname.getText().toString()+"'";
				Cursor cursor=db.rawQuery(sqldormitory, null);
				cursor.moveToFirst();
				if(cursor.getLong(0)>countsofdormitory)
				{
					register_errortip_dormitory.setText("��������ע����������");
					register_errortip_dormitory.setVisibility(View.VISIBLE);
				}
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
	   register_etpassword.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(register_etpassword.getText()))
			{
				register_errortip_password.setText("ע�����벻��Ϊ��");
				register_errortip_password.setVisibility(View.VISIBLE);
			}
			else
			{
				register_errortip_password.setVisibility(View.INVISIBLE);
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
	   register_again_etpassword.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(register_again_etpassword.getText()))
			{
				register_errortip_again_password.setText("�ظ��������벻��Ϊ��");
				register_errortip_again_password.setVisibility(View.VISIBLE);
			}
			else
			{
				register_errortip_again_password.setVisibility(View.INVISIBLE);
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
   public void  OnClick(View v)
   {
		if(!TextUtils.isEmpty(register_etpassword.getText()) && !register_etpassword.getText().toString().equals(register_again_etpassword.getText().toString()) )
		{
			register_errortip_again_password.setText("�����������벻һ��");
			register_errortip_again_password.setVisibility(View.VISIBLE);
		}
		else{
	  /*  String sql="insert into User (username,password,email,Dormitory) Values ('"
			  +register_etname.getText().toString() +"','"
			  +register_etpassword.getText().toString() +"','"
			  +register_et_mailboxname.getText().toString()+"','"
			  +register_et_dormitoryname.getText().toString()+"')";
	    if(!TextUtils.isEmpty(register_etname.getText()) && !TextUtils.isEmpty(register_etpassword.getText()) && 
			 !TextUtils.isEmpty(register_et_mailboxname.getText()) && !TextUtils.isEmpty(register_et_dormitoryname.getText()) && 
			  !TextUtils.isEmpty(register_again_etpassword.getText())){
		  db.execSQL(sql);
		  db.close();
	    	}*/
			
			//����ע��ɹ��Ի���
		    final  Builder builder=new AlertDialog.Builder(this);
			builder.setTitle("ע��ɹ���ʾ");
			builder.setMessage("ע��ɹ���");
			builder.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivity(new Intent(RegisterActivity.this,Login.class));
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
						Toast.makeText(RegisterActivity.this,"ע��ʧ�ܣ�", Toast.LENGTH_SHORT).show();
					}
				}
				
			};
			new Thread(new Runnable() {
				public void run() {
					
					AccessServer access=new AccessServer("http://10.0.2.2:8080/test/RegisterServlet");
					String result=access.doPost(new String[]{"username","psd","email","dormitory"},new String[]{register_etname.getText().toString(),
							                      register_etpassword.getText().toString(), 
							                      register_et_mailboxname.getText().toString(), 
							                      register_et_dormitoryname.getText().toString()});
					Message msg=new Message();
					msg.obj=result;
					myHandler.sendMessage(msg);
				}
			}).start();	
			
		}
	  
   }
 
   
	   
   
}