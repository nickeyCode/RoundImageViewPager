package com.dome.roundimageviewpager.uilts.myviewpager;

import java.util.List;

import com.dome.roundimageviewpager.MyImageView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class HeadViewPagerAdapter extends PagerAdapter {

	private Context mContext;
	private List<MyImageView> mList;
	
	public HeadViewPagerAdapter(Context context,List<MyImageView> list){
		this.mContext = context;
		this.mList = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	//当缓存view的数量超过上限时，会销毁最先的一个
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		//Log.d("remove", mImageViews[position].hashCode() + "");
		container.removeView(mList.get(position));
	}
	//添加View
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(mList.get(position),0);
		return mList.get(position);
	}

}
