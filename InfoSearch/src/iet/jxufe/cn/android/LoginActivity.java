package iet.jxufe.cn.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText name,psd;
	private Button login,register,reset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.name);
        psd=(EditText)findViewById(R.id.psd);
        register=(Button)findViewById(R.id.register);   
        reset=(Button)findViewById(R.id.reset);   
        login=(Button)findViewById(R.id.login);       
        login.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				//发送请求，获取结果，相应处理
				final Handler myHandler=new Handler(){					
					public void handleMessage(Message msg) {						
						String response=(String)msg.obj;
						System.out.println(response);
						if("true".equals(response)){
							Intent intent=new Intent(LoginActivity.this,MainActivity.class);
							startActivity(intent);
						}else{
							Toast.makeText(LoginActivity.this, "用户名和密码不匹配！",Toast.LENGTH_LONG).show();
						}
					}
				};
				
				new Thread(new Runnable(){					
					public void run() {
						AccessToServer login=new AccessToServer("http://10.0.2.2:8080/login/LoginServlet");
						//LoginToServer login=new LoginToServer();
						String result=login.doPost(new String[]{"username","psd"},new String[]{name.getText().toString(),psd.getText().toString()});
						Message msg=new Message();
						msg.obj=result;
						myHandler.sendMessage(msg);
					}
				}).start();
			}
		});
        
        register.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
        
        reset.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				name.setText("");
				psd.setText("");
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
}
