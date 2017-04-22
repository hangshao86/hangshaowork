package iet.jxufe.cn.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText name, psd, repsd;
	private Button register, reset;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		name = (EditText) findViewById(R.id.name);
		psd = (EditText) findViewById(R.id.psd);
		repsd = (EditText) findViewById(R.id.psd);
		reset = (Button) findViewById(R.id.reset);
		register = (Button) findViewById(R.id.register);
		reset.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				name.setText("");
				psd.setText("");
				repsd.setText("");
			}
		});
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String checkResult = checkInput();
				if (checkResult != null) {
					Builder builder = new AlertDialog.Builder(
							RegisterActivity.this);
					builder.setTitle("出错提示");
					builder.setMessage(checkResult);
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									psd.setText("");
									repsd.setText("");
								}
							});
					builder.create().show();
				} else {
					final Handler myHandler = new Handler() {
						public void handleMessage(Message msg) {
							String response = (String) msg.obj;
							System.out.println(response);
							if ("true".equals(response)) {
								Intent intent = new Intent(
										RegisterActivity.this,
										LoginActivity.class);
								startActivity(intent);
							} else {
								Toast.makeText(RegisterActivity.this, "注册失败！",
										Toast.LENGTH_LONG).show();
							}
						}
					};

					new Thread(new Runnable() {
						public void run() {
							AccessToServer login = new AccessToServer(
									"http://10.0.2.2:8080/login/RegisterServlet");
							String result = login.doPost(new String[] {
									"username", "psd" }, new String[] {
									name.getText().toString(),
									psd.getText().toString() });
							Message msg = new Message();
							msg.obj = result;
							myHandler.sendMessage(msg);
						}
					}).start();

				}

			}
		});

	}

	public String checkInput() {
		if (name.getText().toString() == null
				|| "".equals(name.getText().toString().trim())) {
			return "用户名不能为空！";
		}
		if (psd.getText().toString().length() < 3
				|| psd.getText().toString().length() > 15) {
			return "密码长度应在3~15之间！";
		}
		if (!repsd.getText().toString().equals(psd.getText().toString())) {
			return "两次输入的密码不一致！";
		}
		return null;
	}

}
