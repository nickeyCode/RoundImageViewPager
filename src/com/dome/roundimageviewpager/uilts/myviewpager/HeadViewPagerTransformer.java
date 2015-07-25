package com.dome.roundimageviewpager.uilts.myviewpager;

import android.support.v4.view.ViewPager.PageTransformer;
import android.util.Log;
import android.view.View;

public class HeadViewPagerTransformer implements PageTransformer{
	private static final float MIN_SCALE = 0.75f; 
	//主要是设置在不同位置上的VIEW的活动动画
	@Override
	public void transformPage(View view, float position) {
		// TODO Auto-generated method stub
		 int pageWidth = view.getWidth();  
		  
	        if (position < -1) { // [-Infinity,-1)   
	            view.setAlpha(0);  
	        }
	        else if (position <= 0) { // [-1,0]  
	            view.setAlpha(1);  
	            view.setTranslationX(0);  
	            float x = -1.0f * (2f / 3f) * pageWidth * position;
		        view.setTranslationX(x);  
	            float scaleFactor = MIN_SCALE + (2 - MIN_SCALE) * (1 - Math.abs(position)); 
	            view.setScaleX(scaleFactor);  
	            view.setScaleY(scaleFactor); 
	        } else if (position <= 1) { // (0,1]  
	            view.setAlpha(1);  
	            float x = -1.0f * (2f / 3f) * pageWidth * position;
	            view.setTranslationX(x);  
	            float scaleFactor = MIN_SCALE + (2 - MIN_SCALE) * (1 - Math.abs(position));  
	            view.setScaleX(scaleFactor);  
	            view.setScaleY(scaleFactor);  
	  
	        }
	}

}
