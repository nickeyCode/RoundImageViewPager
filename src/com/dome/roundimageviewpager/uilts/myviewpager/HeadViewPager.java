package com.dome.roundimageviewpager.uilts.myviewpager;

import java.util.ArrayList;
import java.util.List;

import com.dome.roundimageviewpager.MyImageView;
import com.dome.roundimageviewpager.R;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HeadViewPager extends FrameLayout {

	private Context mContext;
	private ViewPager mViewPager;
	private List<Integer> mImageIds;
	private List<MyImageView> mImageViews;
	private ViewGroup mViewGroup;
	private List<ImageView> tips;
	private int tipsChoseImgId;
	private int tipsUnchoseImgId;
	
	public HeadViewPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		creatView(context);
	}

	public HeadViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		creatView(context);
	}

	public HeadViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		creatView(context);
	}
	
	public HeadViewPager(Context context,List<MyImageView> imgageList) {
		super(context);
		// TODO Auto-generated constructor stub
		creatView(context,imgageList);
	}
	
	public void creatView(Context context){
		this.mContext = context;
		LayoutInflater.from(context).inflate(R.layout.head_view_pager, this);
		mViewPager = (ViewPager)findViewById(R.id.viewpager);
		mViewGroup = (ViewGroup)findViewById(R.id.viewgroup);
		mImageViews = new ArrayList<MyImageView>();
		mImageIds = new ArrayList<Integer>();
		tips = new ArrayList<ImageView>();
		tipsChoseImgId = R.drawable.img_bg_chose;
		tipsUnchoseImgId = R.drawable.img_bg_unchose;
		build();
	}
	
	public void creatView(Context context,List<MyImageView> imgageList){
		this.mContext = context;
		LayoutInflater.from(context).inflate(R.layout.head_view_pager, this);
		mViewPager = (ViewPager)findViewById(R.id.viewpager);
		mViewGroup = (ViewGroup)findViewById(R.id.viewgroup);
		mImageViews = imgageList;
		mImageIds = new ArrayList<Integer>();
		tips = new ArrayList<ImageView>();
		tipsChoseImgId = R.drawable.img_bg_chose;
		tipsUnchoseImgId = R.drawable.img_bg_unchose;
		build();
	}
	
	public void build(){
		buildTips();
		mViewPager.setAdapter(new HeadViewPagerAdapter(mContext,mImageViews));
		//设置默认显示页面为第0页
		mViewPager.setCurrentItem(0);
		//设置选择页面时的动画
		mViewPager.setPageTransformer(true, new HeadViewPagerTransformer());
		//设置缓存View的个数，默认是3个，这表示缓存了5个
		mViewPager.setOffscreenPageLimit(4);
		//页面发生改变的监听器
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			//选择发生改变
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				changeTips(arg0);
			}
			//有滑动操作
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			//滑动操作或选择改变
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	//初始化底部导航圆点条
	public void buildTips(){
		for (int i = 0 ; i < mImageViews.size() ; i ++){
        	ImageView imageView = new ImageView(mContext);  
            imageView.setLayoutParams(new LayoutParams(10,10));   
            if(i == 0){  
            	imageView.setBackgroundResource(tipsChoseImgId);  
            }else{  
            	imageView.setBackgroundResource(tipsUnchoseImgId);  
            }  
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(12,12));  
            layoutParams.leftMargin = 5;  
            layoutParams.rightMargin = 5;  
            tips.add(imageView); 
            mViewGroup.addView(imageView, layoutParams);  
        }
	}
	//当选定的页面发生改变时，导航条也对应改变
	public void changeTips(int index){
		for (int i = 0 ; i < tips.size() ; i ++){  
            if(i == index){  
                tips.get(i).setBackgroundResource(tipsChoseImgId);  
            }else{  
            	tips.get(i).setBackgroundResource(tipsUnchoseImgId);  
            }  
        }
	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	public ViewPager getmViewPager() {
		return mViewPager;
	}

	public void setmViewPager(ViewPager mViewPager) {
		this.mViewPager = mViewPager;
	}

	public List<MyImageView> getmImageViews() {
		return mImageViews;
	}
	//改变图片队列时，要更新整个viewPager
	public void setmImageViews(List<MyImageView> mImageViews) {
		this.mImageViews = mImageViews;
		this.mViewPager.notify();
		this.mViewPager.setCurrentItem(0);
		
	}

	public ViewGroup getmViewGroup() {
		return mViewGroup;
	}

	public void setmViewGroup(ViewGroup mViewGroup) {
		this.mViewGroup = mViewGroup;
	}

	public int getTipsChoseImgId() {
		return tipsChoseImgId;
	}

	public void setTipsChoseImgId(int tipsChoseImgId) {
		this.tipsChoseImgId = tipsChoseImgId;
	}

	public int getTipsUnchoseImgId() {
		return tipsUnchoseImgId;
	}

	public void setTipsUnchoseImgId(int tipsUnchoseImgId) {
		this.tipsUnchoseImgId = tipsUnchoseImgId;
	}
	
	
	
	
	
	
}
