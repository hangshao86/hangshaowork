package edu.whu.musicplayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.InputFilter.LengthFilter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;
import edu.whu.util.FormatTools;
import edu.whu.util.HttpUtil;

public class MainActivity extends Activity implements OnScrollListener {
	private static final String TAG = null;
	private int count=0;
	public  SimpleAdapter simpleAdapter;
	ListView listview;
	int lastItemIndex;
	private int times = 1;// 表示第几次刷新，从而从数据库中调出不同的数据
	public static List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	View moreView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (ListView) findViewById(R.id.listview);
	//	Button button = (Button) findViewById(R.id.button_main);
		moreView = getLayoutInflater().inflate(R.layout.load, null);

		// 点击listview的某一项而进入
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this,
						DetailActivity.class);
				intent.putExtra("id",position);
				startActivity(intent);
			}
		});

		// // 点击button获取更新
		// button.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// String[] items = { "img", "title", "list_item" };
		// int[] ids = { R.id.list_item, R.id.list_name, R.id.list_detail };
		// SimpleAdapter simpleAdapter = new SimpleAdapter(
		// MainActivity.this, getData(),
		// R.layout.activity_list, items, ids);
		// listview.setAdapter(simpleAdapter);
		//
		// // 这段代码是现实自定义位图所必需的，较为简单，直接放上即可
		// simpleAdapter.setViewBinder(new ViewBinder() {
		// public boolean setViewValue(View view, Object data,
		// String textRepresentation) {
		// if (view instanceof ImageView
		// && data instanceof Drawable) {
		// ImageView iv = (ImageView) view;
		// iv.setImageDrawable((Drawable) data);
		// return true;
		// } else
		// return false;
		// }
		// });
		//
		// }
		// });

		// 向上滑动获取更新，这个更适合一般的常见的操作方式
		count = list.size();
		String[] items = { "img", "title", "list_item" };
		int[] ids = { R.id.list_item, R.id.list_name, R.id.list_detail };
		simpleAdapter = new SimpleAdapter(this, getData(times),
				R.layout.activity_list, items, ids);
		listview.addFooterView(moreView);
		listview.setAdapter(simpleAdapter);
		// 这段代码是现实自定义位图所必需的，较为简单，直接放上即可
		simpleAdapter.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				if (view instanceof ImageView && data instanceof Drawable) {
					ImageView iv = (ImageView) view;
					iv.setImageDrawable((Drawable) data);
					return true;
				} else
					return false;
			}
		});
		// 滑动获取更新，这个更适合一般的常见的操作方法,this是因为集成了相应的接口可以直接这样写，然后必须有其全部实现
		listview.setOnScrollListener(this);
	}

	// 从服务器获取数据并将要现实的数据封装成List
	List<HashMap<String, Object>> getData(int num) {
		times=times+1;
		List<NameValuePair> lists = new ArrayList<NameValuePair>();
		// 使得请求格式较为统一
		lists.add(new BasicNameValuePair("id", num + ""));
		// 网络请求
		Thread httpThread = new Thread(new HttpUtil(HttpUtil.httpUrl + "show",
				lists));
		httpThread.start();
		// 直到新线程出栈，主线程运行
		try {
			httpThread.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String result = HttpUtil.result;
		String[] results = result.split(";");
		for (int i = 0; i < results.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String[] item = results[i].split(",");
			String name = item[2];
			String price = item[1];
			String pic = item[0];
			String tag = "item";
			Log.v(tag, item[0]);
			FormatTools format = new FormatTools();
			map.put("img",
					format.Bytes2Drawable(Base64.decode(pic, Base64.DEFAULT)));
			map.put("title", name);
			map.put("list_item", price);
			list.add(map);
		}
		count=list.size();
		return list;
	}

	
	//滑动到底部
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (lastItemIndex == simpleAdapter.getCount()-1 && scrollState == this.SCROLL_STATE_IDLE&&lastItemIndex<25) {
			Log.i(TAG, "拉到最底部");
			moreView.setVisibility(view.VISIBLE);
			mHandler.sendEmptyMessage(0);
		}
		if(lastItemIndex>=25){
		Toast.makeText(getApplicationContext(), "没有更多了", Toast.LENGTH_SHORT).show();
		listview.removeFooterView(moreView);
		}
	}
//滑动监视器
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		 lastItemIndex = firstVisibleItem + visibleItemCount - 1 -1;  
	}

	// Handler用于处理UI交互
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				getData(times); // 向服务器请求数据
				//直接用这个即可以更新里adapter和listview
				simpleAdapter.notifyDataSetChanged();
				moreView.setVisibility(View.GONE);
				break;
			case 1:

				break;
			default:
				break;
			}
		};
	};

}
