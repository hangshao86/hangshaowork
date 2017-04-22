package edu.whu.musicplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import edu.whu.util.HttpUtil;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Bundle bundle = getIntent().getExtras();
		int id = bundle.getInt("id");
		ImageView image = (ImageView) findViewById(R.id.detail_img);
		TextView name = (TextView) findViewById(R.id.name);
		TextView price = (TextView) findViewById(R.id.price);
		TextView author = (TextView) findViewById(R.id.author);
		TextView remark = (TextView) findViewById(R.id.remark);
		TextView detail = (TextView) findViewById(R.id.detail);

		List<NameValuePair> lists = new ArrayList<NameValuePair>();
		lists.add(new BasicNameValuePair("id", id+"1"));
		// 网络请求
		Thread httpThread = new Thread(new HttpUtil(HttpUtil.httpUrl + "DetailServlet",
				lists));
		httpThread.start();
		// 直到新线程出栈，主线程运行
		try {
			httpThread.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 这段代码用来显示从MainActivty中已经得到的信息
		Map<String, Object> map = new HashMap<String, Object>();
		map = MainActivity.list.get(id);
		image.setImageDrawable((Drawable) map.get("img"));
		name.setText((CharSequence) map.get("title"));
		price.setText((CharSequence) map.get("list_item"));


		// 这段代码用来显示从服务器中得到的信息
		String results[] = HttpUtil.result.split(";");
		author.setText(results[0]);
		remark.setText(results[2]);
		detail.setText(results[1]);
	}

}
