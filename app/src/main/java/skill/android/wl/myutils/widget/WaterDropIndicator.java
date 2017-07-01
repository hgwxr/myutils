package skill.android.wl.myutils.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import skill.android.wl.myutils.R;


/**
 * Created by yangcai on 17-1-7.
 */

public class WaterDropIndicator extends View implements ViewPager.OnPageChangeListener {
    private final static String TAG = WaterDropIndicator.class.getSimpleName();

    //点的大小 也就是直径
    private float waterDropSize;
    // 也就是半径
    private float waterDropRadius;
    //两个点之间的距离
    private float waterDropSpace;
    //点的个数
    private int waterDropCount = 2;
    //选中点的画笔
    private Paint selectedPaint;
    //未选点的画笔
    private Paint unSelectedPaint;
    //记录所有点圆心的X坐标
    private float[] centerXs = new float[0];
    //记录点圆心的Y坐标
    private float centerY;
    //点最顶端的Y坐标
    private float centerYtop;
    //点最底端的Y坐标
    private float centerYBottom;
    //viewpage 移动的位置
    private float pageOffset;
    //当前的page
    private int currentPosition = 0;
    //滑动时 将要滑到的下一个page
    private int nextPosition = 0;
    private float directionMerge;
    private ViewPager viewPager;
    //记录滑动状态
    private int scrollState;
    //是否开启了选择的的位移动画
    private boolean anim = false;

    public WaterDropIndicator(Context context) {
        this(context, null);
    }

    public WaterDropIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterDropIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        float density = context.getResources().getDisplayMetrics().density;
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WaterDropIndicator, defStyleAttr, 0);
        //选中的点的颜色
        int colorSelected = a.getColor(R.styleable.WaterDropIndicator_indicator_selected_color, Color.parseColor("#FF33CC"));
        //未选中的点的颜色
        int colorUnselected = a.getColor(R.styleable.WaterDropIndicator_indicator_unselected_color, Color.parseColor("#FF6699"));
        waterDropSize = a.getDimensionPixelSize(R.styleable.WaterDropIndicator_indicator_size, (int) (8 * density));
        waterDropSpace = a.getDimensionPixelSize(R.styleable.WaterDropIndicator_indicator_space, (int) (12 * density));
        a.recycle();
        init(colorSelected, colorUnselected);
    }

    /**
     * 初始化画笔
     */
    private void init(int colorSelected, int colorUnselected) {
        selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedPaint.setAntiAlias(true);
        selectedPaint.setColor(colorSelected);
        selectedPaint.setStyle(Paint.Style.FILL);
        unSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        unSelectedPaint.setAntiAlias(true);
        unSelectedPaint.setColor(colorUnselected);
        unSelectedPaint.setStyle(Paint.Style.FILL);
        waterDropRadius = waterDropSize / 2;
        centerYtop = getPaddingTop();
        centerY = centerYtop + waterDropRadius;
        centerYBottom = centerYtop + waterDropSize;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        setWaterDropCount(viewPager.getAdapter().getCount());
        this.viewPager.getAdapter().registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                setWaterDropCount(WaterDropIndicator.this.viewPager.getAdapter().getCount());
            }
        });
        this.viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (scrollState == ViewPager.SCROLL_STATE_DRAGGING) {
            anim = false;
        }
        if (anim) {
            return;
        }
        if (positionOffset == 0) {
            return;
        }
        if (scrollState == ViewPager.SCROLL_STATE_DRAGGING) {
            currentPosition = viewPager.getCurrentItem();
            if (position == currentPosition) {//相等表示手指从右往左滑动
                nextPosition = currentPosition + 1;
            } else {//不相等表示手指从左往右滑动
                nextPosition = currentPosition - 1;
            }
        }
        if (position == currentPosition) {
            pageOffset = positionOffset;
        } else {//不相等时 position 的值是递减的，但是我们需要一个递增的值 所有...
            pageOffset = 1f - positionOffset;
        }
        postInvalidate();
    }

    @Override
    public void onPageSelected(int position) {
        startAnimation();
        anim = true;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        scrollState = state;
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            anim = false;
        }
    }

    private void setWaterDropCount(int waterDropCount) {
        if (this.waterDropCount == waterDropCount) {
            return;
        }
        this.waterDropCount = waterDropCount;
        requestLayout();
    }

    /**
     * 计算所有点的中心坐标的X坐标 所有点居中显示
     */
    private void calculationCenterXs() {
        //画完所有点需要的宽度
        float dotWidth = waterDropCount * waterDropSize + (waterDropCount - 1) * waterDropSpace;
        //横向 中心点X坐标
        float px = getMeasuredWidth() / 2;
        //第一个点圆心的X坐标
        float startPx = px - dotWidth / 2 + waterDropSize / 2 + getPaddingLeft();
        centerXs = new float[waterDropCount];
        for (int i = 0; i < waterDropCount; i++) {
            centerXs[i] = startPx + i * (waterDropSpace + waterDropSize);
        }
    }

    //计算高度 圆的直径+ paddingtop + paddingbottom
    //宽度始终 match_parent
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = (int) (waterDropSize + getPaddingTop() + getPaddingBottom());
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        calculationCenterXs();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //   drawTest(canvas);
        if (centerXs.length == 0) {
            return;
        }
        if (directionMerge != 0) {
            drawCircles(canvas, nextPosition, currentPosition);
            if (currentPosition > nextPosition) {
                drawMergeLeftByPosition(canvas, nextPosition, currentPosition);
            } else {
                drawMergeRightByPosition(canvas, currentPosition, nextPosition);
            }
            return;
        }
        if (pageOffset == 0 || pageOffset == 1) {
            drawCircles(canvas);
        } else if (0 < pageOffset && pageOffset < 0.5) {
            drawCircles(canvas, nextPosition, currentPosition);
            if (currentPosition > nextPosition) {
                drawRightByPosition(canvas, nextPosition);
                drawLeftByPosition(canvas, currentPosition);
            } else {
                drawRightByPosition(canvas, currentPosition);
                drawLeftByPosition(canvas, nextPosition);
            }
        } else if (pageOffset >= 0.5 && pageOffset < 1) {
            if (currentPosition > nextPosition) {
                drawRightByPosition(canvas, nextPosition, currentPosition);
                drawLeftByPosition(canvas, nextPosition, currentPosition);
            } else {
                drawRightByPosition(canvas, currentPosition, nextPosition);
                drawLeftByPosition(canvas, currentPosition, nextPosition);
            }
            drawCircles(canvas, nextPosition, currentPosition);
        }
        drawSelectByPosition(canvas, centerXs[currentPosition]);
    }

    /**
     * 是否包括 value
     *
     * @param arrays 指定的数组
     * @param value  指定的值
     * @return true 包含 false 不包含
     */
    private boolean arraysContains(int[] arrays, int value) {
        for (int position : arrays) {
            if (position == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * 画选中的圆
     */
    private void drawSelectByPosition(Canvas canvas, float centerX) {
        Path path = new Path();
        path.rewind();
        path.addCircle(centerX, centerY, waterDropRadius, Path.Direction.CCW);
        canvas.drawPath(path, selectedPaint);
    }

    /**
     * 画未选中的圆  pageoffset==0
     */
    private void drawCircles(Canvas canvas, int... exceptPosition) {
        Path path = new Path();
        path.rewind();
        for (int i = 0; i < waterDropCount; i++) {
            if (!arraysContains(exceptPosition, i)) {
                float centerX = centerXs[i];
                path.addCircle(centerX, centerY, waterDropRadius, Path.Direction.CCW);
            }
        }
        canvas.drawPath(path, unSelectedPaint);
    }

    /**
     * 画点点右边凸起的贝塞尔曲线图 画选中的圆  pageoffset>0 && pageoffset<0.5
     */
    private void drawRightByPosition(Canvas canvas, int position) {
        Path path = new Path();
        path.rewind();
        RectF mRectF = new RectF(centerXs[position] - waterDropRadius, centerYtop, centerXs[position] + waterDropRadius, centerYBottom);
        path.moveTo(centerXs[position], centerYBottom);
        path.arcTo(mRectF, 90, 180, true);
        float endX1 = centerXs[position] + waterDropRadius + waterDropSpace * pageOffset;
        float endY1 = centerY;
        float controlX1 = centerXs[position] + waterDropRadius / 2;
        float controlY1 = centerYtop;
        float controlX2 = endX1;
        float controlY2 = endY1 - waterDropRadius / 2;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX1, endY1);
        float endX2 = centerXs[position];
        float endY2 = centerYBottom;
        controlX1 = endX1;
        controlY1 = endY1 + waterDropRadius / 2;
        controlX2 = centerXs[position] + waterDropRadius / 2;
        controlY2 = centerY + waterDropRadius;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX2, endY2);
        canvas.drawPath(path, unSelectedPaint);
    }

    /**
     * 画点点左边边凸起的贝塞尔曲线图 画选中的圆 pageoffset>0 && pageoffset<0.5
     */
    private void drawLeftByPosition(Canvas canvas, int position) {
        Path path = new Path();
        path.rewind();
        RectF mRectF = new RectF(centerXs[position] - waterDropRadius, centerYtop, centerXs[position] + waterDropRadius, centerYBottom);
        path.moveTo(centerXs[position], centerYBottom);
        path.arcTo(mRectF, 90, -180, true);
        float endX1 = centerXs[position] - waterDropRadius - waterDropSpace * pageOffset;
        float endY1 = centerY;
        float controlX1 = centerXs[position] - waterDropRadius / 2;
        float controlY1 = centerYtop;
        float controlX2 = endX1;
        float controlY2 = endY1 - waterDropRadius / 2;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX1, endY1);
        float endX2 = centerXs[position];
        float endY2 = centerYBottom;
        controlX1 = endX1;
        controlY1 = endY1 + waterDropRadius / 2;
        controlX2 = centerXs[position] - waterDropRadius / 2;
        controlY2 = centerY + waterDropRadius;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX2, endY2);
        canvas.drawPath(path, unSelectedPaint);
    }

    /**
     * 画点点右边凸起的贝塞尔曲线图 画选中的圆  pageoffset>=0.5 && pageoffset<1
     */
    private void drawRightByPosition(Canvas canvas, int p1, int p2) {
        Path path = new Path();
        path.rewind();
        float adjustedFraction = (pageOffset - 0.2f) * 1.25f;
        RectF mRectF = new RectF(centerXs[p1] - waterDropRadius, centerYtop, centerXs[p1] + waterDropRadius, centerYBottom);
        path.moveTo(centerXs[p1], centerYBottom);
        path.arcTo(mRectF, 90, 180, true);
        float endX1 = centerXs[p1] + waterDropRadius + (waterDropSpace / 2);
        float endY1 = centerY - (adjustedFraction * waterDropRadius);
        float controlX1 = endX1 - (adjustedFraction * waterDropRadius);
        float controlY1 = centerYtop;
        float controlX2 = endX1 - ((1 - adjustedFraction) * waterDropRadius);
        float controlY2 = endY1;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX1, endY1);
        float endX2 = centerXs[p2];
        float endY2 = centerYtop;
        controlX1 = endX1 + ((1 - adjustedFraction) * waterDropRadius);
        controlY1 = endY1;
        controlX2 = endX1 + (adjustedFraction * waterDropRadius);
        controlY2 = centerYtop;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX2, endY2);
        canvas.drawPath(path, unSelectedPaint);
    }

    /**
     * 画点点左边边凸起的贝塞尔曲线图 画选中的圆 pageoffset>=0.5 && pageoffset<1
     */
    private void drawLeftByPosition(Canvas canvas, int p1, int p2) {
        Path path = new Path();
        path.rewind();
        float adjustedFraction = (pageOffset - 0.2f) * 1.25f;
        RectF mRectF = new RectF(centerXs[p2] - waterDropRadius, centerYtop, centerXs[p2] + waterDropRadius, centerYBottom);
        path.arcTo(mRectF, 270, 180, true);
        float endX1 = centerXs[p1] + waterDropRadius + (waterDropSpace / 2);
        float endY1 = centerY + (adjustedFraction * waterDropRadius);
        float controlX1 = endX1 + (adjustedFraction * waterDropRadius);
        float controlY1 = centerYBottom;
        float controlX2 = endX1 + ((1 - adjustedFraction) * waterDropRadius);
        float controlY2 = endY1;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX1, endY1);
        float endX2 = centerXs[p1];
        float endY2 = centerYBottom;
        controlX1 = endX1 - ((1 - adjustedFraction) * waterDropRadius);
        controlY1 = endY1;
        controlX2 = endX1 - (adjustedFraction * waterDropRadius);
        controlY2 = endY2;
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, endX2, endY2);
        canvas.drawPath(path, unSelectedPaint);
    }

    /**
     * 指定点的右移
     */
    private void drawMergeRightByPosition(Canvas canvas, int p1, int p2) {
        Path path = new Path();
        path.rewind();
        float dotPx = centerXs[p1] + directionMerge * (waterDropSpace + waterDropSize);
        RectF mRectF1 = new RectF(dotPx - waterDropRadius, centerYtop, dotPx + waterDropRadius, centerYBottom);
        RectF mRectF2 = new RectF(centerXs[p2] - waterDropRadius, centerYtop, centerXs[p2] + waterDropRadius, centerYBottom);
        path.arcTo(mRectF1, 90, 180, true);
        path.arcTo(mRectF2, 270, 180, true);
        path.addRect(dotPx, centerYtop, centerXs[p2], centerYBottom, Path.Direction.CCW);
        canvas.drawPath(path, unSelectedPaint);
        drawScaleByPosition(canvas, p1);
        drawSelectByPosition(canvas, dotPx);
    }

    /**
     * 指定点的左移
     */
    private void drawMergeLeftByPosition(Canvas canvas, int p1, int p2) {
        Path path = new Path();
        path.rewind();
        float dotPx = centerXs[p2] - directionMerge * (waterDropSpace + waterDropSize);
        RectF mRectF1 = new RectF(dotPx - waterDropRadius, centerYtop, dotPx + waterDropRadius, centerYBottom);
        RectF mRectF2 = new RectF(centerXs[p1] - waterDropRadius, centerYtop, centerXs[p1] + waterDropRadius, centerYBottom);
        path.arcTo(mRectF1, 270, 180, true);
        path.arcTo(mRectF2, 90, 180, true);
        path.addRect(dotPx, centerYtop, centerXs[p1], centerYBottom, Path.Direction.CCW);
        canvas.drawPath(path, unSelectedPaint);
        drawScaleByPosition(canvas, p2);
        drawSelectByPosition(canvas, dotPx);
    }

    /**
     * 指定点的放大
     */
    private void drawScaleByPosition(Canvas canvas, int position) {
        Path path = new Path();
        path.rewind();
        path.addCircle(centerXs[position], centerY, waterDropSize / 2 * directionMerge, Path.Direction.CCW);
        canvas.drawPath(path, unSelectedPaint);
    }

    /**
     * 开启选择点的位移动画
     */
    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1.0f);
        animator.setDuration(200);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                directionMerge = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                directionMerge = 0;
                pageOffset = 0;
                currentPosition = nextPosition;
                postInvalidate();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

}
