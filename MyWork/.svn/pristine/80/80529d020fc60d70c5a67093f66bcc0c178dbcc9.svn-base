package com.zqh.mywork;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {

	private PagerAdapter adapter;
	public MyPagerAdapter(PagerAdapter adapter) {
		// TODO Auto-generated constructor stub
	super();
	this.adapter=adapter;
}
@Override
public int getCount() {
	// TODO Auto-generated method stub
	return Integer.MAX_VALUE;
}
public int getRealCount() {
	return adapter.getCount();
}
public int getRealItemPosition(Object object) {
	return adapter.getItemPosition(object);
}
@Override
public void destroyItem(ViewGroup container, int position, Object object) {
	int realPosition = position % getRealCount();
	
	adapter.destroyItem(container, realPosition, object);
}
@Override
public Object instantiateItem(ViewGroup container, int position) {
	int realPosition = position % getRealCount();
	
	return adapter.instantiateItem(container, realPosition);
}

@Override
public void finishUpdate(ViewGroup container) {
// TODO Auto-generated method stub
	adapter.finishUpdate(container);
}

@Override
public boolean isViewFromObject(View arg0, Object arg1) {
	// TODO Auto-generated method stub
	return adapter.isViewFromObject(arg0, arg1);
}
@Override
public void restoreState(Parcelable state, ClassLoader loader) {
	// TODO Auto-generated method stub
	adapter.restoreState(state, loader);
}
@Override
public Parcelable saveState() {
	// TODO Auto-generated method stub
	return adapter.saveState();
}
@Override
public void startUpdate(View container) {
	// TODO Auto-generated method stub
		adapter.startUpdate(container);
	}

}
