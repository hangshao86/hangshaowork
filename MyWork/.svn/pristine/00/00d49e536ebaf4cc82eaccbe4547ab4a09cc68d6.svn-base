package com.zqh.mywork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mywork.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class personal_information_Activity extends Activity{
	private ListView listview;
	private List<Map<String, Object>> list;
	private int[] images={R.drawable.person1,R.drawable.person2,R.drawable.person3,
			R.drawable.person4,R.drawable.person5,R.drawable.person6,R.drawable.person7
			,R.drawable.person8,R.drawable.person9,R.drawable.person10};
	private String[] name={"个人信息","广工认证","分享软件","检测更新","清理缓存","加入我们","系统公告","意见反馈","关于我们","退出账号"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_acitivity);
		
		listview=(ListView) findViewById(R.id.lv);
		list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<images.length;i++)
		{
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("image", images[i]);
			map.put("name",name[i]);
			list.add(map);
			
		}
		
		SimpleAdapter simpleAdapter=new SimpleAdapter(this,list, R.layout.listview_item_activity, 
				new String[]{"image","name"},new int[]{R.id.iv,R.id.textView});
		listview.setAdapter(simpleAdapter);
	}
	
}
