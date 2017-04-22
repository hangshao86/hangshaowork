package iet.jxufe.cn.android;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DownLoadActivity extends Activity {
	private String[] files;
	private ListView listFile;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		listFile = (ListView) findViewById(R.id.listFile);
		final Handler myHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.arg1 == 0x11) {
					String result = (String) msg.obj;
					if ("noFile".equals(result)) {
						Toast.makeText(DownLoadActivity.this, "没有资源可供下载！",
								Toast.LENGTH_LONG).show();
					} else {
						files = result.split("\\*");
						List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
						for (int i = 0; i < files.length; i++) {
							Map<String, Object> item = new HashMap<String, Object>();
							item.put("icon", R.drawable.file);
							item.put("name", files[i]);
							items.add(item);
						}
						SimpleAdapter simpleAdapter = new SimpleAdapter(
								DownLoadActivity.this, items, R.layout.item,
								new String[] { "icon", "name" }, new int[] {
										R.id.icon, R.id.fileName });
						listFile.setAdapter(simpleAdapter);
					}
				}else if(msg.arg1==0x12){
					String result = (String) msg.obj;
					final String[] content=result.split("\\*");						
					Builder builder=new AlertDialog.Builder(DownLoadActivity.this);
					builder.setTitle("请输入柏存的文件名");
					final EditText fileName=new EditText(DownLoadActivity.this);
					builder.setView(fileName);
					builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {						
						public void onClick(DialogInterface dialog, int which) {							
							try{
								File sdCardDir=Environment.getExternalStorageDirectory();
								File destFile=new File(sdCardDir.getCanonicalPath()+File.separator+"Download");
								if(!destFile.exists()){
									destFile.mkdir();
								}
								File saveFile=new File(destFile.getPath(),fileName.getText().toString());
								RandomAccessFile raf=new RandomAccessFile(saveFile, "rw");
								raf.write(content[1].getBytes());
								Toast.makeText(DownLoadActivity.this,"文件下载成功！",Toast.LENGTH_LONG).show();
								if(raf!=null){
									raf.close();
								}																
							}catch(Exception ex){
								ex.printStackTrace();
							}						
						}
					});
					builder.create().show();
				}
			};
		};
		new Thread(new Runnable() {
			public void run() {
				AccessToServer login = new AccessToServer(
						"http://10.0.2.2:8080/login/DownloadServlet");
				String result = login.doGet(null, null);
				System.out.println(result);
				Message msg = new Message();
				msg.arg1 = 0x11;
				msg.obj = result;
				myHandler.sendMessage(msg);
			}
		}).start();
		listFile.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final String file=files[position];
				new Thread(new Runnable() {
					public void run() {
						AccessToServer login = new AccessToServer(
								"http://10.0.2.2:8080/login/DownFile");
						String result = login.doGet(new String[]{"fileName"}, new String[]{file});
						System.out.println(result);
						Message msg = new Message();
						msg.arg1 = 0x12;
						msg.obj = result;
						myHandler.sendMessage(msg);
					}
				}).start();			
			}
			
		});
	}

}
