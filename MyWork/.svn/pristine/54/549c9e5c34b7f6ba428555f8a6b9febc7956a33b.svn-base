package com.zqh.mywork;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.example.mywork.R;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class clubActivity extends Activity{
	
	private List<Map<String, Object>> list;
	private ListView lv;
	private int[] clubimages={R.drawable.club_head1,R.drawable.club_head2};
	private int[] sourcesimages1={R.drawable.source1,R.drawable.source4};
	private int[] sourcesimages2={R.drawable.source2,R.drawable.source5};
	private int[] sourcesimages3={R.drawable.source3,R.drawable.source6};
	private int[] clubnames={R.string.clubname1,R.string.clubname2};
	private int[] themenames={R.string.clubtheme,R.string.clubtheme2};
	private int[] timenames={R.string.clubtime,R.string.clubtime2};
	private int[] adressnames={R.string.clubaddress,R.string.clubaddress2};
	private int[] patronnames={R.string.clubpatron,R.string.clubpatron2};
	private int[] remarknames={R.string.clubremark,R.string.clubremark2};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.club_activity);
		initdata();
	}
	public void initdata(){
		lv=(ListView) findViewById(R.id.list_club);
		list=new ArrayList<Map<String,Object>>();
		
		for(int i=0;i<clubimages.length;i++)
		{
			Map<String , Object> map=new HashMap<String, Object>();
			map.put("clubimage",clubimages[i]);
			map.put("sourceimage1",sourcesimages1[i]);
			map.put("sourceimage2",sourcesimages2[i]);
			map.put("sourceimage3",sourcesimages3[i]);
			map.put("clubname", clubnames[i]);
			map.put("themename", themenames[i]);
			map.put("timename", timenames[i]);
			map.put("adressname", adressnames[i]);
			map.put("patronname", patronnames[i]);
			map.put("remarkname", remarknames[i]);
			list.add(map);
		}
		MyBaseAdapter myBaseAdapter=new MyBaseAdapter();
		lv.setAdapter(myBaseAdapter);
	}
	
	public class MyBaseAdapter extends BaseAdapter{

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
				viewHolder=new ViewHolder();
				LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView=inflater.inflate(R.layout.mylistview_item3_activity, null);
				viewHolder.iv_clubimage=(ImageView) convertView.findViewById(R.id.iv_club_image);
				viewHolder.iv_sourceimage1=(ImageView) convertView.findViewById(R.id.iv_club1);
				viewHolder.iv_sourceimage2=(ImageView) convertView.findViewById(R.id.iv_club2);
				viewHolder.iv_sourceimage3=(ImageView)convertView.findViewById(R.id.iv_club3);
				viewHolder.tv_clubname=(TextView) convertView.findViewById(R.id.tv_club_name);
				viewHolder.tv_themename=(TextView) convertView.findViewById(R.id.tv_theme);
				viewHolder.tv_timename=(TextView) convertView.findViewById(R.id.tv_time);
				viewHolder.tv_patronname=(TextView) convertView.findViewById(R.id.tv_patron);
				viewHolder.tv_remarkname=(TextView) convertView.findViewById(R.id.tv_remark);
				viewHolder.tv_adressname=(TextView) convertView.findViewById(R.id.tv_adress);
				convertView.setTag(viewHolder);
				
				
			}else
			{
				viewHolder=(ViewHolder) convertView.getTag();
			}
		    viewHolder.iv_clubimage.setImageResource((Integer) list.get(position).get("clubimage"));
		    viewHolder.iv_sourceimage1.setImageResource((Integer) list.get(position).get("sourceimage1"));
		    viewHolder.iv_sourceimage2.setImageResource((Integer) list.get(position).get("sourceimage2"));
		    viewHolder.iv_sourceimage3.setImageResource((Integer) list.get(position).get("sourceimage3"));
		    viewHolder.tv_clubname.setText((Integer) list.get(position).get("clubname"));
		    viewHolder.tv_timename.setText((Integer) list.get(position).get("timename"));
		    viewHolder.tv_themename.setText((Integer) list.get(position).get("themename"));
		    viewHolder.tv_adressname.setText((Integer) list.get(position).get("adressname"));
		    viewHolder.tv_patronname.setText((Integer) list.get(position).get("patronname"));
		    viewHolder.tv_remarkname.setText((Integer) list.get(position).get("remarkname"));
			return convertView;
		}
		
	}
	static class ViewHolder{
		ImageView iv_clubimage;
		ImageView iv_sourceimage1;
		ImageView iv_sourceimage2;
		ImageView iv_sourceimage3;
		TextView tv_clubname;
		TextView tv_themename;
		TextView tv_timename;
		TextView tv_adressname;
		TextView tv_patronname;
		TextView tv_remarkname;
	}


	
}
