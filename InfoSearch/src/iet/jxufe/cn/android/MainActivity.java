package iet.jxufe.cn.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button fileUpload, downLoad, infoQuery;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fileUpload = (Button) findViewById(R.id.fileUpload);
		downLoad = (Button) findViewById(R.id.downLoad);
		infoQuery = (Button) findViewById(R.id.infoQuery);
		MyListener myListener=new MyListener();
		fileUpload.setOnClickListener(myListener);
		downLoad.setOnClickListener(myListener);
		infoQuery.setOnClickListener(myListener);
	}

	private class MyListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent=null;
			switch (v.getId()) {
			case R.id.fileUpload:
				intent = new Intent(MainActivity.this,
						FileUploadActivity.class);
				break;
			case R.id.downLoad:
				intent = new Intent(MainActivity.this,
						DownLoadActivity.class);
				break;
			case R.id.infoQuery:
				intent = new Intent(MainActivity.this,
						InfoQueryActivity.class);
				break;
			}
			startActivity(intent);
		}
	}
}
