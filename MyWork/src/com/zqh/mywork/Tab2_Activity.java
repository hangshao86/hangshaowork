package com.zqh.mywork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mywork.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class Tab2_Activity extends Activity {
	private ListView mlist;
	private List<Map<String,Object>> list;
	private int[] foodshop_image={R.drawable.foodshop1,R.drawable.foodshop2,R.drawable.foodshop3};
	private int[] star_image={R.drawable.star,R.drawable.star2,R.drawable.star3};
	private int[] shop_name={R.string.shopname,R.string.shopname2,R.string.shopname3};
	private int[] permonth={R.string.sales_volume_permonth,R.string.sales_volume_permonth2,R.string.sales_volume_permonth3};
	private int[] bengin_end={R.string.begin_end,R.string.begin_end2,R.string.begin_end3};
	private int[] discounttext={R.string.discounttext1,R.string.discounttext4,R.string.discounttext4};
	private int[] discounttext2={R.string.discounttext2,R.string.discounttext3,R.string.discounttext3};
	private int[] discount={R.string.discount1,R.string.discount12,R.string.discount13};
	private int[] discount2={R.string.discount2,R.string.discount22,R.string.discount23};
	private int[] colors={R.color.ban,R.color.jian,R.color.jian};
	private int[] colors2={R.color.shou,R.color.zhe,R.color.zhe};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab2);
		
		TabHost mTabHost=(TabHost) findViewById(R.id.mytabhost);
		mTabHost.setup();
		LayoutInflater inflater=LayoutInflater.from(this);
		inflater.inflate(R.layout.tab2_item_activity,mTabHost.getTabContentView());
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("一饭").setContent(R.id.mylayout));
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("二饭").setContent(R.id.mylayout));
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("三饭").setContent(R.id.mylayout));
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("四饭").setContent(R.id.mylayout));
		mTabHost.setCurrentTab(0);
		initlistdata2();
	}
	public void initlistdata2(){
		mlist=(ListView) findViewById(R.id.tab2_list);
		list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<foodshop_image.length;i++)
		{
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("foodshop_image",foodshop_image[i] );
			map.put("star_image", star_image[i]);
			map.put("shop_name", shop_name[i]);
			map.put("permonth", permonth[i]);
			map.put("begin_end", bengin_end[i]);
			map.put("discounttext", discounttext[i]);
			map.put("discounttext2", discounttext2[i]);
			map.put("discount", discount[i]);
			map.put("discount2",discount2[i]);
			map.put("colors", colors[i]);
			map.put("colors2", colors2[i]);
			list.add(map);
		}
		BaseAdapter myBaseAdapter=new MyBaseAdapter();
		mlist.setAdapter(myBaseAdapter);
	}
	class MyBaseAdapter extends BaseAdapter{

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
			LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.mylistview_item2_activity, null);
			viewHolder=new ViewHolder();
			viewHolder.tv_shopname=(TextView) convertView.findViewById(R.id.tv_shopname);
			viewHolder.tv_sales_volume_permonth=(TextView) convertView.findViewById(R.id.tv_sales_volume_permonth);
			viewHolder.tv_begin_end=(TextView) convertView.findViewById(R.id.tv_begin_end);
			viewHolder.tv_discounttext1=(TextView) convertView.findViewById(R.id.tv_discounttext1);
			viewHolder.tv_discounttext2=(TextView) convertView.findViewById(R.id.tv_discounttext2);
			viewHolder.tv_discount1=(TextView) convertView.findViewById(R.id.tv_discount1);
			viewHolder.tv_discount2=(TextView) convertView.findViewById(R.id.tv_discount2);
			viewHolder.iv_foodshop_image=(ImageView) convertView.findViewById(R.id.iv_shopimage);
			viewHolder.iv_star_image=(ImageView) convertView.findViewById(R.id.iv_star);
			convertView.setTag(viewHolder);
			
			}else
			{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			viewHolder.tv_shopname.setText((Integer)list.get(position).get("shop_name"));
			viewHolder.tv_sales_volume_permonth.setText((Integer)list.get(position).get("permonth"));
			viewHolder.tv_begin_end.setText((Integer)list.get(position).get("begin_end"));
			viewHolder.tv_discounttext1.setBackgroundColor(getResources().getColor((Integer)list.get(position).get("colors")));
			viewHolder.tv_discounttext1.setText((Integer)list.get(position).get("discounttext"));
			viewHolder.tv_discounttext2.setText((Integer)list.get(position).get("discounttext2"));
			viewHolder.tv_discounttext2.setBackgroundColor(getResources().getColor((Integer)list.get(position).get("colors2")));
			viewHolder.tv_discount1.setText((Integer) list.get(position).get("discount"));
			viewHolder.tv_discount2.setText((Integer) list.get(position).get("discount2"));
			viewHolder.iv_foodshop_image.setImageResource((Integer) list.get(position).get("foodshop_image"));
			viewHolder.iv_star_image.setImageResource((Integer) list.get(position).get("star_image"));
			return convertView ;
		}
		
	}
	static class ViewHolder
	{
		TextView tv_shopname;
		TextView tv_sales_volume_permonth;
		TextView tv_begin_end;
		TextView tv_discounttext1;
		TextView tv_discounttext2;
		TextView tv_discount1;
		TextView tv_discount2;
		ImageView iv_foodshop_image;
		ImageView iv_star_image;
		
	}
	public void initlistdata(){
		mlist=(ListView) findViewById(R.id.tab2_list);
		list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<foodshop_image.length;i++)
		{
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("foodshop_image",foodshop_image[i] );
			map.put("star_image", star_image[i]);
			map.put("shop_name", shop_name[i]);
			map.put("permonth", permonth[i]);
			map.put("begin_end", bengin_end[i]);
			map.put("discounttext", discounttext[i]);
			map.put("discounttext2", discounttext2[i]);
			map.put("discount", discount[i]);
			map.put("discount2",discount2[i]);
			map.put("colors", colors[i]);
			map.put("colors2", colors2[i]);
			list.add(map);
		}
		
		SimpleAdapter simpleAdapter=new SimpleAdapter(this, list,R.layout.mylistview_item2_activity,
				new String[]{"foodshop_image","star_image","shop_name","permonth",
						      "begin_end","discounttext","discounttext2","discount",
						      "discount2","colors","colors2"}, new int[]{R.id.iv_shopimage
						       ,R.id.iv_star,R.id.tv_shopname,R.id.tv_begin_end,R.id.tv_discounttext1
						       ,R.id.tv_discounttext2,R.id.tv_discount1,R.id.tv_discount2,
						       R.id.tv_discount1,R.id.tv_discount2
						      });
		mlist.setAdapter(simpleAdapter);
	}

}
