package skill.android.wl.rulerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * <pre>
 * @date 2017/7/6
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class RulerView  extends View {
    private static final String TAG = "RulerView";
    private Paint mLinePaint;
    private Paint mTextPaint;
    private ILineCreator mLineCreator;
    private int mWidth;
    private int mHeight;
    private ScrollerCompat mScroller;
    private GestureDetectorCompat mGestureDetectorCompat;
    private int mCenterX;
    private float mScrollDistanceX;
    private float mDensity;
    private float mSpaceWidth;
    private int mSpanCount=100;
    private int mStartIndex;
    private float mWillConsumedDis;
    private int mLastFlingX;

    public void setmStartIndex(int mStartIndex) {
        this.mStartIndex = mStartIndex;
    }

    public int getmStartIndex() {
        return mStartIndex;
    }

    public RulerView(Context context) {
        this(context,null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCenterX = mWidth/2;
        mWillConsumedDis=mSpaceWidth*mSpanCount;
    }
    private void init() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(0xFF333333);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(0xFF333333);
        if (null==mLineCreator) {
            mLineCreator=new DefaultILineCreator();
        }
        mScroller = ScrollerCompat.create(getContext());
        mGestureDetectorCompat =new GestureDetectorCompat(getContext(),new GestureListener());

        mDensity = getResources().getDisplayMetrics().density;
        mSpaceWidth=mDensity*8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画刻度线

        float x =mCenterX- mScrollDistanceX;
        for (int i = 0; i <= mSpanCount; i++) {
            Line line = mLineCreator.getLine(i, mHeight, mDensity);
            mLinePaint.setColor(line.getColor());
            mLinePaint.setStrokeWidth(line.getWidth());
            canvas.save();
            canvas.drawLine(x,0,x,line.getHeight(),mLinePaint);
            canvas.restore();
            x+= mSpaceWidth;
        }
        //画指示线
        mLinePaint.setColor(Color.parseColor("#1ea749"));
        mLinePaint.setStrokeWidth(4);
        canvas.save();
        canvas.translate(mCenterX,0);
        canvas.drawLine(0,0,0,mHeight/5*4,mLinePaint);
        canvas.restore();
    }

    static final Interpolator sQuinticInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };
    private class DefaultILineCreator implements ILineCreator {
        @Override
        public Line getLine(int index, int parentHeight, float density) {
            if (index%10==0){
                return new Line(density*2,mHeight/5*4, Color.BLACK,index+"",density*12);
            }else if (index % 5 == 0) {
                return new Line(density * 1, mHeight/5*3, Color.BLACK);
            } else {
                return new Line(density * 1, (float) (mHeight/5*2.2), Color.BLACK);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean handle = mGestureDetectorCompat.onTouchEvent(event);
        int action = MotionEventCompat.getActionMasked(event);
        if (!handle&&action==MotionEvent.ACTION_UP){
            //处理手指抬起时候的滑动
        }
        return handle;
    }

    private class GestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d(TAG, "onShowPress() called with: e = ["  + "]");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(TAG, "onSingleTapUp() called with: e = [" + "]");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll() called with: e1 = ["  + "], e2 = [" + "], distanceX = [" + distanceX + "], distanceY = [" + distanceY + "]");
             //canScrollHorizontally()
           boolean canScrollHorizontal= canScrollHorizontal(distanceX);
            if (canScrollHorizontal){
                scrollXBy(distanceX,true);
            }
            return true;
        }





        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "onFling() called with: e1 = [" + "], e2 = ["  + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "]");
            mLastFlingX =0;
            mScroller.fling(0, 0, (int) velocityX, 0,
                    Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
               ViewCompat.postInvalidateOnAnimation(RulerView.this);
            return true;
        }
    }
    private void scrollXBy(float distanceX, boolean scale) {
        mScrollDistanceX+=distanceX;
        mScrollDistanceX=mScrollDistanceX<0?0:mScrollDistanceX;
        mScrollDistanceX=  mScrollDistanceX>mWillConsumedDis?mWillConsumedDis:mScrollDistanceX;
        //  scrollBy((int) distanceX,0);
        ViewCompat.postInvalidateOnAnimation(RulerView.this);
    }
    private boolean canScrollHorizontal(float distanceX) {
        Log.d(TAG, "canScrollHorizontal() called with: distanceX = [" + distanceX + "]");
        if (mScrollDistanceX==0&&distanceX<0){
            //左边界
            return false;
        }
        else  if (mScrollDistanceX== mWillConsumedDis &&distanceX>=0){
            return false;
        }
        return true;
    }
    @Override
    public void computeScroll() {
//        super.computeScroll();
        ScrollerCompat scroller = this.mScroller;
        if (scroller.computeScrollOffset()){
            int x = scroller.getCurrX();
            int dX = mLastFlingX - x;
            mLastFlingX=x;
            if (!canScrollHorizontal(dX)){
                scroller.abortAnimation();
            }
            if (!scroller.isFinished()){
                scrollXBy(dX,false);
            }
        }
    }
}
