package com.zqh.mywork;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mywork.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class BuyActivity extends TabActivity{
	private TabHost tabHost;
	private ListView lv;
	private List<Map<String,Object>> list;
	
	private View tab2view;
	private TabHost tab2Host;
	private LayoutInflater inflater;
	
	private int[] images_head={R.drawable.head1,R.drawable.head2};
	private int[] images_shangbiao={R.drawable.shangbiao,R.drawable.shangbiao2};
	private String[] others={"销量11件      共12件宝贝","销量111件    共120件宝贝"};
	private String[] names={"航少86","航少87"};
	private int[] images_cloths1={R.drawable.cloth1,R.drawable.cloth4};
	private int[] images_cloths2={R.drawable.cloth2,R.drawable.cloth5};
	private int[] images_cloths3={R.drawable.cloth3,R.drawable.cloth6};
	
	
	
	
	static class ViewHolder{
		TextView tv_name;
		TextView tv_other;
		ImageView iv_head;
		ImageView iv_shangbiao;
		ImageView cloth1;
		ImageView cloth2;
		ImageView cloth3;
		Button btn_in;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.buy_activity);
		
		
		tabHost=getTabHost();
		LayoutInflater inflater=LayoutInflater.from(this);
		inflater.inflate(R.layout.tab1, tabHost.getTabContentView());
		//inflater.inflate(R.layout.tab2,tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("校购").setContent(R.id.LinearLayout01));
		tabHost.addTab(tabHost.newTabSpec("tab02").setIndicator("校内外卖").setContent(new Intent(this,Tab2_Activity.class)));
		setlistviewdata();
	
	}
    public void setlistviewdata(){
    	lv=(ListView) findViewById(R.id.tab1_lv);
    	list=new ArrayList<Map<String,Object>>();
    	for(int i=0;i<images_cloths1.length;i++)
    	{
    		Map<String ,Object> map=new HashMap<String, Object>();
    		map.put("im", images_head[i]);
    		map.put("is", images_shangbiao[i]);
    		map.put("ot", others[i]);
    		map.put("na", names[i]);
    		map.put("im1", images_cloths1[i]);
    		map.put("im2", images_cloths2[i]);
    		map.put("im3", images_cloths3[i]);
    		list.add(map);
    	}
    	SimpleAdapter simpleAdapter=new SimpleAdapter(this,list ,R.layout.mylistview_item_activity
    			, new String[]{"im","is","ot","na","im1","im2","im3"}, new int[]{R.id.iv_head,
    					R.id.iv_shangbiao,R.id.tv_other,R.id.tv_name,R.id.iv_display1,R.id.iv_display2
    					,R.id.iv_display3});
    	lv.setAdapter(simpleAdapter);
    }
    
}
