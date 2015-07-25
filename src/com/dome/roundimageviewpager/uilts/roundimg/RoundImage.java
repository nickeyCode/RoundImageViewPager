package com.dome.roundimageviewpager.uilts.roundimg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImage extends ImageView {
	
	private int mBorderThickness = 4;  
    private Context mContext;  
    private int defaultColor = 0xFFFFFFFF;  
  
    // 控件默认长、宽  
    private int defaultWidth = 0;  
    private int defaultHeight = 0; 
	
	public RoundImage(Context context) {  
        super(context);  
        mContext = context;  
    }  
  
    public RoundImage(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        mContext = context;   
    }  
  
    public RoundImage(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        mContext = context;  
    }  
	
    @Override  
    protected void onDraw(Canvas canvas) {  
        Drawable drawable = getDrawable();  
        if (drawable == null) {  
            return;  
        }  
  
        if (getWidth() == 0 || getHeight() == 0) {  
            return;  
        }  
        this.measure(0, 0);  
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();  
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);    
        defaultWidth = getWidth();    
        defaultHeight = getHeight();   
        int radius = 0;  
        radius = (defaultWidth < defaultHeight ? defaultWidth  
                    : defaultHeight) / 2 - mBorderThickness;  
        drawCircleBorder(canvas, radius,defaultColor );  
        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);  
        canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius, defaultHeight  
                / 2 - radius, null);  
    }  
  
    /** 
     * 获取裁剪后的圆形图片 
     *  
     * @param radius 
     *            半径 
     */  
    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {  
        Bitmap scaledSrcBmp;  
        int diameter = radius * 2;  
        int bmpWidth = bmp.getWidth();  
        int bmpHeight = bmp.getHeight();  
        int squareWidth = 0, squareHeight = 0;  
        int x = 0, y = 0;  
        Bitmap squareBitmap;  
        if (bmpHeight > bmpWidth) {// 高大于宽  
            squareWidth = squareHeight = bmpWidth;  
            x = 0;  
            y = (bmpHeight - bmpWidth) / 2;  
            // 截取正方形图片  
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,  
                    squareHeight);  
        } else if (bmpHeight < bmpWidth) {// 宽大于高  
            squareWidth = squareHeight = bmpHeight;  
            x = (bmpWidth - bmpHeight) / 2;  
            y = 0;  
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,  
                    squareHeight);  
        } else {  
            squareBitmap = bmp;  
        }  
  
        if (squareBitmap.getWidth() != diameter  
                || squareBitmap.getHeight() != diameter) {  
        	squareBitmap = Bitmap.createScaledBitmap(squareBitmap, diameter,  
                    diameter, true);  
  
        }
        Bitmap output = Bitmap.createBitmap(squareBitmap.getWidth(),  
        		squareBitmap.getHeight(), Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
  
        Paint paint = new Paint();  
        Rect rect = new Rect(0, 0, squareBitmap.getWidth(),  
        		squareBitmap.getHeight());  
  
        paint.setAntiAlias(true);  
        paint.setFilterBitmap(true);  
        paint.setDither(true);  
        canvas.drawARGB(0, 0, 0, 0);  
        canvas.drawCircle(squareBitmap.getWidth() / 2,  
        		squareBitmap.getHeight() / 2, diameter / 2,  
                paint);  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        canvas.drawBitmap(squareBitmap, rect, rect, paint);  
        // bitmap回收(recycle导致在布局文件XML看不到效果)  
        // bmp.recycle();  
        // squareBitmap.recycle();  
        // scaledSrcBmp.recycle();  
        bmp = null;  
        squareBitmap = null;  
        scaledSrcBmp = null;  
        return output;  
    }  
  
    /** 
     * 边缘画圆 
     */  
    private void drawCircleBorder(Canvas canvas, int radius, int color) {  
        Paint paint = new Paint();  
        /* 去锯齿 */  
        paint.setAntiAlias(true);  
        paint.setFilterBitmap(true);  
        paint.setDither(true);  
        paint.setColor(color);  
        /* 设置paint的　style　为STROKE：空心 */  
        paint.setStyle(Paint.Style.STROKE);  
        /* 设置paint的外框宽度 */  
        paint.setStrokeWidth(mBorderThickness);  
        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);  
    }  
    
	
}
