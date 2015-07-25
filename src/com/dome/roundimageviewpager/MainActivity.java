package com.dome.roundimageviewpager;

import java.util.ArrayList;
import java.util.List;

import com.dome.roundimageviewpager.uilts.myviewpager.HeadViewPager;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private HeadViewPager mHeadViewPager;
	private List<MyImageView> myImageViews;
	
	private int[] mImageIds;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化显示的图片
        mImageIds = new int[]{
    			R.drawable.bg2,
    			R.drawable.bigger1,
    			R.drawable.text1,
    			R.drawable.text2
    		};
        
        
        mHeadViewPager = (HeadViewPager)findViewById(R.id.viewpager_show);
        
        myImageViews = new ArrayList<MyImageView>();
		for (int i = 0 ; i < mImageIds.length ; i ++){
			MyImageView v = new MyImageView(this);
			v.setImage(mImageIds[i]);
			myImageViews.add(v);
		}
		
		mHeadViewPager.creatView(this, myImageViews);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
