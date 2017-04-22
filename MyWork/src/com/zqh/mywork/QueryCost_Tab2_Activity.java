package com.zqh.mywork;

import java.util.Calendar;

import com.example.mywork.R;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class QueryCost_Tab2_Activity extends ActivityGroup{
	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.querycost_tab2_activity);
		inittab();
	}
	public void inittab(){
		int currentmonth=getmonth();
		mTabHost=(TabHost) findViewById(R.id.mytabhost2);
		mTabHost.setup(this.getLocalActivityManager());
		//inflater.inflate(R.layout.historypayment, mTabHost.getTabContentView());
//		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(+dealmonth(currentmonth-4)+"月").setContent(R.id.mylayout2));
//		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(+dealmonth(currentmonth-3)+"月").setContent(R.id.mylayout2));
//		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(+dealmonth(currentmonth-2)+"月").setContent(R.id.mylayout2));
//		mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator(+dealmonth(currentmonth-1)+"月").setContent(R.id.mylayout2));
//		mTabHost.addTab(mTabHost.newTabSpec("tab5").setIndicator(+dealmonth(currentmonth)+"月").setContent(R.id.mylayout2));
	
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(+dealmonth(currentmonth-4)+"月").setContent(new Intent(QueryCost_Tab2_Activity.this,QueryCost_Tab2_Item_Activity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(+dealmonth(currentmonth-3)+"月").setContent(new Intent(QueryCost_Tab2_Activity.this,QueryCost_Tab2_Item_Activity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(+dealmonth(currentmonth-2)+"月").setContent(new Intent(QueryCost_Tab2_Activity.this,QueryCost_Tab2_Item_Activity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator(+dealmonth(currentmonth-1)+"月").setContent(new Intent(QueryCost_Tab2_Activity.this,QueryCost_Tab2_Item_Activity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("tab5").setIndicator(+dealmonth(currentmonth)+"月").setContent(new Intent(QueryCost_Tab2_Activity.this,QueryCost_Tab2_Item_Activity.class)));
		//mTabHost.setCurrentTab(4);
		
	}
	public int getmonth()
	{
		Calendar calendar=Calendar.getInstance();
		  return calendar.get(Calendar.MONTH)+1;
	}
	public int  dealmonth(int month) {
		if(month<=0)
			month=month+12;
			return month;
		
	}

}
