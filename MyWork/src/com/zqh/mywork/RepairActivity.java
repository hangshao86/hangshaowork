package com.zqh.mywork;

import com.example.mywork.R;
import com.example.mywork.R.array;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class RepairActivity extends Activity {
	private static int index=0;
	private Spinner spiner01,spiner02,spiner03,spiner04,spiner05,spiner06;
	private Button btn_decrease,btn_increase,btn_submit;
	private LinearLayout ll1;
	private LinearLayout ll2;
	private LinearLayout ll3;
	private String[] source;
	private String[] sourcefan;
	private String[] sourcebulb;
	private String[] sourcetap;
	private String[] sourcebedchair;
	private String[] sourcehotwater;
	private String[] sourcedoor;
	
	private ArrayAdapter<String> adapter;
	private ArrayAdapter<String> adapterfan;
	private ArrayAdapter<String> adapterbulb;
	private ArrayAdapter<String> adaptertap;
	private ArrayAdapter<String> adapterbedchair;
	private ArrayAdapter<String> adapterwater;
	private	ArrayAdapter<String> adapterdoor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repair_activity);
		source=getResources().getStringArray(R.array.repair_name);
		sourcefan=getResources().getStringArray(R.array.fan_location);
		sourcebulb=getResources().getStringArray(R.array.bulb_location);
		sourcetap=getResources().getStringArray(R.array.tap_location);
		 sourcebedchair=getResources().getStringArray(R.array.bedchair_location);
		 sourcehotwater=getResources().getStringArray(R.array.hotwater_location);
		 sourcedoor=getResources().getStringArray(R.array.door_location);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,source);
		adapterfan=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sourcefan);
		adapterbulb=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sourcebulb);
		adaptertap=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sourcetap);
		adapterbedchair=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sourcebedchair);
		adapterwater=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sourcehotwater);
		 adapterdoor=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sourcedoor);
		
		spiner01=(Spinner) findViewById(R.id.sp01);
		spiner02=(Spinner) findViewById(R.id.sp02);
		spiner03=(Spinner) findViewById(R.id.sp03);
		spiner04=(Spinner) findViewById(R.id.sp04);
		spiner05=(Spinner) findViewById(R.id.sp05);
		spiner06=(Spinner) findViewById(R.id.sp06);
		ll1=(LinearLayout) findViewById(R.id.repair_ll1);
		ll2=(LinearLayout) findViewById(R.id.repair_ll2);
		ll3=(LinearLayout) findViewById(R.id.repair_ll3);
		btn_decrease=(Button) findViewById(R.id.btn_decrease);
		btn_increase=(Button) findViewById(R.id.btn_increase);
	    btn_submit=(Button) findViewById(R.id.submit);

 		spiner01.setAdapter(adapter);
 	   spiner03.setAdapter(adapter);
 	    spiner05.setAdapter(adapter);
 	    spiner02.setAdapter(adapter);
 	    spiner04.setAdapter(adapter);
 	    spiner06.setAdapter(adapter);
 	   OnSpinnerClick();
		
	}
	public void OnSpinnerClick()
	{
		spiner01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					spiner02.setAdapter(adapterfan);
					break;
				case 1:
					spiner02.setAdapter(adapterbulb);
					break;
				case 2:
					spiner02.setAdapter(adaptertap);
					break;
				case 3:
				case 4:
					spiner02.setAdapter(adapterbedchair);
					break;
				case 5:
					spiner02.setAdapter(adapterwater);
					break;
				case 6:
					spiner02.setAdapter(adapterdoor);
					break;
				case 7:
					spiner02.setEnabled(false);
					break;	
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		spiner03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					spiner04.setAdapter(adapterfan);
					break;
				case 1:
					spiner04.setAdapter(adapterbulb);
					break;
				case 2:
					spiner04.setAdapter(adaptertap);
					break;
				case 3:
				case 4:
					spiner04.setAdapter(adapterbedchair);
					break;
				case 5:
					spiner04.setAdapter(adapterwater);
					break;
				case 6:
					spiner04.setAdapter(adapterdoor);
					break;
				case 7:
					spiner04.setEnabled(false);
					break;	
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		spiner05.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					spiner06.setAdapter(adapterfan);
					break;
				case 1:
					spiner06.setAdapter(adapterbulb);
					break;
				case 2:
					spiner06.setAdapter(adaptertap);
					break;
				case 3:
				case 4:
					spiner06.setAdapter(adapterbedchair);
					break;
				case 5:
					spiner06.setAdapter(adapterwater);
					break;
				case 6:
					spiner06.setAdapter(adapterdoor);
					break;
				case 7:
					spiner06.setEnabled(false);
					break;		
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void OnLinearLayoutDeal()
	{
		if(index==0)
		{
			ll1.setVisibility(View.GONE);
			ll2.setVisibility(View.GONE);
			ll3.setVisibility(View.GONE);
		}
		if(index==1)
		{
			ll1.setVisibility(View.VISIBLE);
			ll2.setVisibility(View.GONE);
			ll3.setVisibility(View.GONE);
		}
		if(index==2)
		{   ll1.setVisibility(View.VISIBLE);
			ll2.setVisibility(View.VISIBLE);
			ll3.setVisibility(View.GONE);
		}
		if(index==3)
		{
			ll1.setVisibility(View.VISIBLE);
			ll2.setVisibility(View.VISIBLE);
			ll3.setVisibility(View.VISIBLE);
		}
		if(index>=3)
		{
			index=3;
			btn_increase.setEnabled(false);
		}
		else {
			btn_increase.setEnabled(true);
		}
		if(index<=0)
		{
			index=0;
			btn_decrease.setEnabled(false);
			
		}
		else {
			btn_decrease.setEnabled(true);
		}
	}
	public void OnClick(View v)
	{
		switch (v.getId()) {
		case R.id.btn_decrease:
			index--;
			OnLinearLayoutDeal();
			break;
		case R.id.btn_increase:
			index++;
			OnLinearLayoutDeal();
			break;
		case R.id.submit:
			break;
		}
	}
}
