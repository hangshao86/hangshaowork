package com.zqh.mywork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mywork.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QueryCost_Tab2_Item_Activity extends Activity {
	private ListView lv;
	private List<Map<String, Object>> list;
	private int[] textviewSource={R.string.surplus_lastmonth,R.string.return_thismonth,R.string.gift_thismonth
			,R.string.recharge_thismonth};
	private int[] textviewSource2={R.string.data1,R.string.data2,R.string.data3,R.string.data4};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historypayment);
		lv=(ListView) findViewById(R.id.querycost_list);
		initlist();
	}
	public  void initlist(){
		list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<textviewSource.length;i++)
		{
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("text1",textviewSource[i]);
			map.put("text2", textviewSource2[i]);
			list.add(map);
		}
		MyBaseAdapter myBaseAdapter=new MyBaseAdapter();
		lv.setAdapter(myBaseAdapter);
	}
    public void OnClick(View view)
    {
    	if(lv.getVisibility()==View.GONE)
    	lv.setVisibility(View.VISIBLE);
    	else
         lv.setVisibility(View.GONE);
    	
    	
    }
    private class MyBaseAdapter extends BaseAdapter{

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
			ViewHolder viewHolder=null;
			if(convertView==null)
			{
				viewHolder=new ViewHolder();
				LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView=inflater.inflate(R.layout.mylistview_item4_activity,null);
				viewHolder.tv01=(TextView) convertView.findViewById(R.id.tv_item1);
				viewHolder.tv02=(TextView) convertView.findViewById(R.id.tv_item2);
				convertView.setTag(viewHolder);
			}
			else {
				viewHolder=(ViewHolder) convertView.getTag();
			}
			viewHolder.tv01.setText((Integer) list.get(position).get("text1"));
			viewHolder.tv02.setText((Integer) list.get(position).get("text2"));
			
			return convertView;
		}

    	
    }
    private  class ViewHolder {
		TextView tv01;
		TextView tv02;
	}
}
