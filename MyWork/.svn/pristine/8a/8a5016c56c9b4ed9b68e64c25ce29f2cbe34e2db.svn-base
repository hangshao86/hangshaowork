package com.zqh.mywork;

import com.example.mywork.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class Query_CostActivity extends TabActivity {
	private TabHost mtabhost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.querycost_activity);
		initTabHost();
	}
	public void initTabHost(){
		mtabhost=getTabHost();
		LayoutInflater inflater=LayoutInflater.from(this);
		inflater.inflate(R.layout.querycost_tab1_acitivity, mtabhost.getTabContentView());
		mtabhost.addTab(mtabhost.newTabSpec("tab01").setIndicator("账单查询").setContent(R.id.questcost_relativelayout1));
		mtabhost.addTab(mtabhost.newTabSpec("tab02").setIndicator("历史账单").setContent(new Intent(Query_CostActivity.this,QueryCost_Tab2_Activity.class)));
	}

}
