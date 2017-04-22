package iet.jxufe.cn.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class InfoQueryActivity extends Activity {
	private EditText key;
	private Button bookQuery, gradeQuery;
	private ListView resultList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		key = (EditText) findViewById(R.id.key);
		bookQuery = (Button) findViewById(R.id.bookQuery);
		gradeQuery = (Button) findViewById(R.id.gradeQuery);
		resultList=(ListView)findViewById(R.id.result);

		bookQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final Handler myHandler = new Handler() {
					public void handleMessage(Message msg) {
						String result = (String) msg.obj;
						String[] allItems = result.split("\\*");
						int record = allItems.length / 5;
						ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
						for (int i = 0; i < record; i++) {
							Map<String, Object> item = new HashMap<String, Object>();
							item.put("book_id", allItems[5 * i]);
							item.put("book_name", allItems[5 * i + 1]);
							item.put("book_price", allItems[5 * i + 2]);
							item.put("book_author", allItems[5 * i + 3]);
							item.put("book_press", allItems[5 * i + 4]);
							items.add(item);
						}
						SimpleAdapter simpleAdapter = new SimpleAdapter(
								InfoQueryActivity.this, items,
								R.layout.book_item, new String[] { "book_id",
										"book_name", "book_price",
										"book_author", "book_press" },
								new int[] { R.id.book_id, R.id.book_name,
										R.id.book_price, R.id.book_author,
										R.id.book_press });
						resultList.setAdapter(simpleAdapter);				
						
					}
				};

				new Thread(new Runnable() {
					public void run() {
						AccessToServer login = new AccessToServer(
								"http://10.0.2.2:8080/login/InfoQueryServlet");
						String result = login.doGet(new String[] { "book" },
								new String[] { key.getText().toString() });
						Message msg = new Message();
						msg.obj = result;
						myHandler.sendMessage(msg);
					}
				}).start();
			}
		});

		gradeQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final Handler myHandler = new Handler() {
					public void handleMessage(Message msg) {
						String result = (String) msg.obj;
						String[] allItems = result.split("\\*");
						int record = allItems.length / 5;
						ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
						for (int i = 0; i < record; i++) {
							Map<String, Object> item = new HashMap<String, Object>();
							item.put("grade_id", allItems[5 * i]);
							item.put("num", allItems[5 * i + 1]);
							item.put("course_name", allItems[5 * i + 2]);
							item.put("credit", allItems[5 * i + 3]);
							item.put("score", allItems[5 * i + 4]);
							items.add(item);
						}
						SimpleAdapter simpleAdapter = new SimpleAdapter(
								InfoQueryActivity.this, items,
								R.layout.grade_item, new String[] { "grade_id",
										"num", "course_name",
										"credit", "score" },
								new int[] { R.id.grade_id, R.id.num,
										R.id.course_name, R.id.credit,
										R.id.score });
						resultList.setAdapter(simpleAdapter);	
					}
				};

				new Thread(new Runnable() {
					public void run() {
						AccessToServer login = new AccessToServer(
								"http://10.0.2.2:8080/login/InfoQueryServlet");
						String result = login.doPost(new String[] { "grade" },
								new String[] { key.getText().toString() });
						Message msg = new Message();
						msg.obj = result;
						myHandler.sendMessage(msg);
					}
				}).start();
			}
		});

	}

}
