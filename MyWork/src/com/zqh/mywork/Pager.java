package com.zqh.mywork;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import servers.AccessServer;

import com.example.mywork.R;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Pager extends Activity {
	private MyViewPager myViewPager;
	private int[] ImageId;
	private ImageView[] imageViews;
	private MyPagerAdapter myPagerAdapter;
	private Handler handler;
	private int sleeptime=3000;
	public boolean isRun = false;
	public boolean isDown = false;
	private int immageLength=5;
	private LinearLayout mBottonLayout;
	private ImageView imgCur;
	private Bitmap bitmap;
	private GridView gv;
	private List<Map<String,Object>> list;
	private int[] GridViewImage={R.drawable.g1,R.drawable.g2,R.drawable.g3,R.drawable.g4,R.drawable.g5,R.drawable.g6,R.drawable.g7};
	private String[] text={"查询费用","报修","购物","互借版块","社团","文件上传","待拓展2"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager_activity);
		imageViews = new ImageView[immageLength];
		for (int i = 0; i <immageLength; i++) {
			imageViews[i] = new ImageView(this);
			
		}
		 new myAsyncTask().execute("http://10.0.2.2:8080/test/downloadImageServlet");
		initView();	
		initGridView();
		handler=new Handler(){
			@Override
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 0:
					myViewPager.setCurrentItem(myViewPager.getCurrentItem() + 1,
							true);
					if (isRun && !isDown) {
						this.sendEmptyMessageDelayed(0, sleeptime);
					}
					break;

				case 1:
					if (isRun && !isDown) {
						this.sendEmptyMessageDelayed(0, sleeptime);
					}
					break;
				    
				}
			}
			
			
		};
		
	}
	private void initGridView(){
	    
		gv=(GridView) findViewById(R.id.gv);
		list=new ArrayList<Map<String,Object>>();
		
		for(int i=0;i<GridViewImage.length;i++){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("img",GridViewImage[i]);
			map.put("text",text[i]);
			list.add(map);
		}
		MyGridViewAdapter myGridViewAdapter=new MyGridViewAdapter();
		gv.setAdapter(myGridViewAdapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					startActivity(new Intent(Pager.this,Query_CostActivity.class));
					break;

				case 1:
					startActivity(new Intent(Pager.this,RepairActivity.class));
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				}
			}
			
		});
	}
 class MyGridViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder=null;
			if(convertView==null)
			{
				LayoutInflater layoutInflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView=layoutInflater.inflate(R.layout.griditem_activity, null);
				viewHolder=new ViewHolder();
				viewHolder.iView=(ImageView) convertView.findViewById(R.id.imageView);
				viewHolder.tView=(TextView) convertView.findViewById(R.id.textView);
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			viewHolder.tView.setText((CharSequence)list.get(position).get("text"));
			viewHolder.iView.setImageResource((Integer)list.get(position).get("img"));
			return convertView;
		}
		
	}
	static class ViewHolder{
		ImageView iView;
	     TextView tView;
	}
	private void initView() {
		myViewPager = (MyViewPager) findViewById(R.id.mp);
		mBottonLayout = (LinearLayout) findViewById(R.id.layout_bottom);
		
		
		myPagerAdapter = new MyPagerAdapter(new MyLoopViewPagerAdatper());
		myViewPager.setInfinateAdapter(this, handler, myPagerAdapter);
		myViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		setFaceCurPage(0);
		
	}
	private class MyOnPageChangeListener implements OnPageChangeListener {
		/**
		 * Indicates that the pager is in an idle, settled state. The current
		 * page is fully in view and no animation is in progress.
		 */
		public static final int SCROLL_STATE_IDLE = 0;

		/**
		 * Indicates that the pager is currently being dragged by the user.
		 */
		
		public static final int SCROLL_STATE_DRAGGING = 1;

		/**
		 * Indicates that the pager is in the process of settling to a final
		 * position.
		 */
		public static final int SCROLL_STATE_SETTLING = 2;

		@Override
		public void onPageScrollStateChanged(int state) {
			switch (state) {
			case SCROLL_STATE_IDLE:
				break;
			case SCROLL_STATE_DRAGGING:
				break;
			case SCROLL_STATE_SETTLING:
				break;
			}

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
		    
		   
		    setFaceCurPage(position);
		}

	}
	 class myAsyncTask extends AsyncTask<String , Void, byte[]>
	    {

			@Override
			protected byte[] doInBackground(String... params) {
				// TODO Auto-generated method stub
				AccessServer access=new AccessServer(params[0]);
				String result=access.doPost(new String[]{"filename"},new String[]{"a.jpg"});
				byte[] decodedString = Base64.decode(result, Base64.DEFAULT);
			    //byte[] imagebyte=result.getBytes();
			    return decodedString;
			}
			@Override
			protected void onPostExecute(byte[] result) {
				// BitmapFactory.decodeByteArray(result, 0,result.length);
				
				for (int i = 0; i <immageLength; i++) {
					imageViews[i].setImageBitmap(BitmapFactory.decodeByteArray(result, 0,result.length));
				}
				
				
				 
				 
			}
	    	
	    }
	private class MyLoopViewPagerAdatper extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViews.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == (View) object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			// return super.instantiateItem(container, position);
			container.addView(imageViews[position]);
			imageViews[position].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					
				}
			});
			return imageViews[position];
		}
	}
	 public void setFaceCurPage(int page) {
	        mBottonLayout.removeAllViews();
	        page=page%imageViews.length;
	        for (int i = 0; i < imageViews.length; i++) {
	            imgCur = new ImageView(this);
	            imgCur.setBackgroundResource(R.drawable.page_indicator);
	            if (page != i) {
	                imgCur.setBackgroundResource(R.drawable.page_indicator_focused);
	            } 
	            mBottonLayout.addView(imgCur);
	        }
	  /*      for (int i = 0; i < imageViews.length; i++) {
	            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	            params.rightMargin = 5; //
	            imgCur = new ImageView(this);
	            imgCur.setBackgroundResource(R.drawable.page_indicator_focused);
	            imgCur.setId(i);
	            //(imgCur.getId() == page) {
	                imgCur.setBackgroundResource(R.drawable.page_indicator);
	            }
	            mBottonLayout.addView(imgCur, params);
	        }*/
	    }
	 @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		isRun = false;
		handler.removeCallbacksAndMessages(null);
	}
	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isRun = true;
		handler.sendEmptyMessageDelayed(0, sleeptime);
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
