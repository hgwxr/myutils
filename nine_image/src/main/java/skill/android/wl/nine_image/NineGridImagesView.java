package skill.android.wl.nine_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * @date 2017/7/8
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class NineGridImagesView extends View {
    private static final String TAG = "NineGridImagesView";
    private Paint mImgPaint;
    private RectF rectF;
    private Rect rect;
    private Bitmap bitmap;
    private RectF itemRectF;
    private Rect itemRect;
    private int mWidth;
    private int mHeight;
    private int itemWidth;
    private List<String> urls;
    private int spanCount;
    private double spanColum;
    private double spanRown;

    public NineGridImagesView(Context context) {
        this(context, null);
    }

    public NineGridImagesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridImagesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mImgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mImgPaint.setAntiAlias(true);
        urls = new ArrayList<>();
        urls.add("https://pic4.zhimg.com/02685b7a5f2d8cbf74e1fd1ae61d563b_xll.jpg");
        urls.add("https://pic4.zhimg.com/fc04224598878080115ba387846eabc3_xll.jpg");
        urls.add("https://pic3.zhimg.com/d1750bd47b514ad62af9497bbe5bb17e_xll.jpg");
        urls.add("https://pic4.zhimg.com/da52c865cb6a472c3624a78490d9a3b7_xll.jpg");
        urls.add("https://pic3.zhimg.com/0c149770fc2e16f4a89e6fc479272946_xll.jpg");
        urls.add("https://pic1.zhimg.com/76903410e4831571e19a10f39717988c_xll.png");
        urls.add("https://pic3.zhimg.com/33c6cf59163b3f17ca0c091a5c0d9272_xll.jpg");
        urls.add("https://pic4.zhimg.com/52e093cbf96fd0d027136baf9b5cdcb3_xll.png");
        urls.add("https://pic3.zhimg.com/f6dc1c1cecd7ba8f4c61c7c31847773e_xll.jpg");
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF = new RectF();
        rect = new Rect(0, 0, w, h);
        rectF.set(rect);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmapList = new ArrayList<>();
        itemRectF = new RectF();
        itemRect = new Rect();
        mWidth = w;
        mHeight = h;
        loadBitmap(urls.get(0));
    }

    private void loadBitmap(final String url) {
        RequestBuilder<Bitmap> load = Glide.with(this).asBitmap().load(url);

        load.into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                bitmapList.add(resource);
                if (bitmapList.size() != urls.size()) {
                    loadBitmap(urls.get(bitmapList.size()));
                }
                if (bitmapList.size() == 5) {
                      if (bitmapList.size()%3==0){
                          spanRown=1;
                          spanColum=1;
                      }
                      if (bitmapList.size()<3){
                          spanRown=1;
                          spanColum=3;
                      }else if (3<=bitmapList.size()&&bitmapList.size()<6){
                          spanRown=2;
                          spanColum=3;
                      }else if (bitmapList.size()>=6&&bitmapList.size()<9){
                          spanRown=3;
                          spanColum=3;
                      }
                      ViewCompat.postInvalidateOnAnimation(NineGridImagesView.this);
                }
            }
        });
    }

    private List<Bitmap> bitmapList;

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        itemWidth = mWidth / 3;
        if (!bitmapList.isEmpty())
        for (int i = 0; i <spanRown; i++) {
            for (int j = 0; j <  spanColum; j++) {
                canvas.save();
                int dx = itemWidth * j;
                int dy = itemWidth * i;
                canvas.translate(dx, dy);
                rect.set(0, 0, itemWidth, 250);
                rectF.set(rect);

                int index = i * 3 + j;
                if (index<bitmapList.size()) {
                    Bitmap bitmap = bitmapList.get(index);
                    Log.e(TAG, "onDraw() called with: canvas = [" + " " + index + "]");
                    canvas.drawBitmap(bitmap, rect, rectF, mImgPaint);
                }
                canvas.restore();

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

        }
        return super.onTouchEvent(event);
    }
}

