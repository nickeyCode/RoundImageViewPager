package com.dome.roundimageviewpager;

import com.dome.roundimageviewpager.uilts.roundimg.RoundImage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import android.widget.ImageView;
import android.widget.RelativeLayout;
//简单来说这个就是viewpager的一个页，放一张图片
public class MyImageView extends RelativeLayout {

	private RoundImage mImageView;
	
	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.my_image_view, this);
		mImageView = (RoundImage)findViewById(R.id.myImage);
	}
	
	public void setImage(int id){
		mImageView.setImageResource(id);
	}

}
