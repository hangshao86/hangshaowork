package com.zqh.mywork;
import com.example.mywork.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class TActivity extends TabActivity implements  OnCheckedChangeListener{
	private TabHost mtabhost;
	private Intent  mfirstIntent;
	private Intent  msecondIntent;
	private Intent  mthirdIntent;
	private Intent  mfourthIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_activity);
		
		this.mfirstIntent=new Intent(this,Pager.class);
		this.msecondIntent=new Intent(this,BuyActivity.class);
		this.mthirdIntent=new Intent(this,clubActivity.class);
		this.mfourthIntent=new Intent(this,personal_information_Activity.class);
		
		((RadioButton) findViewById(R.id.radio_button0))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button3))
		.setOnCheckedChangeListener(this);
        
        setupintent();
	}
	public  void setupintent()
	{
		this.mtabhost=getTabHost();
		TabHost localhost=this.mtabhost;
		localhost.addTab(buildTabSpec("FIRST_TAB", R.string.home_page,
				R.drawable.icon_1_n, this.mfirstIntent));

		localhost.addTab(buildTabSpec("SECOND_TAB", R.string.more_one,
				R.drawable.icon_2_n, this.msecondIntent));

		localhost.addTab(buildTabSpec("THIRD_TAB",
				R.string.more_two, R.drawable.icon_3_n,
				this.mthirdIntent));

		localhost.addTab(buildTabSpec("FOURTH_TAB", R.string.mine,
				R.drawable.icon_4_n, this.mfourthIntent));

		
	}
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return this.mtabhost.newTabSpec(tag).setIndicator(getString(resLabel),
				getResources().getDrawable(resIcon)).setContent(content);
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			switch (buttonView.getId()) {
			case R.id.radio_button0:
				this.mtabhost.setCurrentTabByTag("FIRST_TAB");
				break;
			case R.id.radio_button1:
				this.mtabhost.setCurrentTabByTag("SECOND_TAB");
				break;
			case R.id.radio_button2:
				this.mtabhost.setCurrentTabByTag("THIRD_TAB");
				break;
			case R.id.radio_button3:
				this.mtabhost.setCurrentTabByTag("FOURTH_TAB");
				break;
			
			}
		}
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
