package skill.android.wl.myutils.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by liuhuiliang on 16/6/7.
 */
public class NXHooldeView extends AppCompatImageView implements ValueAnimator.AnimatorUpdateListener {

    public static final int VIEW_SIZE = 20;
    private static final String TAG = NXHooldeView.class.getSimpleName();

    protected Context mContext;
    protected Paint mPaint4Circle;
    protected int radius;

    protected Point startPosition;
    protected Point endPosition;

    private Drawable drawable;

    private AnimEndListener animEndListener;

    public void setAnimEndListener(AnimEndListener animEndListener) {
        this.animEndListener = animEndListener;
    }

    public AnimEndListener getAnimEndListener() {
        return animEndListener;
    }

    public NXHooldeView(Context context, Drawable drawable) {
        this(context, null,drawable);
    }

    public NXHooldeView(Context context, AttributeSet attrs, Drawable drawable) {
        this(context, attrs, 0,drawable);
    }

    public NXHooldeView(Context context, AttributeSet attrs, int defStyleAttr, Drawable drawable) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mPaint4Circle = new Paint();
       // mPaint4Circle.setColor(Color.RED);
        mPaint4Circle.setAntiAlias(true);


        setImageDrawable(drawable);

        //init();
        /*setGravity(Gravity.CENTER);
        setText("1");
        setTextColor(Color.WHITE);
        setTextSize(12);*/
    }

        public  void setDrawAble(Drawable drawAble){
            setDrawAble(drawAble);
            setScaleType(ScaleType.FIT_XY);
        }
    public void setStartPosition(Point startPosition) {
        //startPosition.y -= 10;
        this.startPosition = startPosition;
    }

    public void setEndPosition(Point endPosition) {
        this.endPosition = endPosition;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // int PX4SIZE = (int) convertDpToPixel(VIEW_SIZE, mContext);
       // setMeasuredDimension(PX4SIZE, PX4SIZE);
       // radius = PX4SIZE / 2;
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, mPaint4Circle);
        super.onDraw(canvas);
    }

    public void startBeizerAnimationNew() {
        if (startPosition == null || endPosition == null) return;
        int pointX = (startPosition.x + endPosition.x) / 2;
        int pointY = (int) (startPosition.y + convertDpToPixel(0, mContext));
        Point controllPoint = new Point(pointX, pointY);

        BezierEvaluator bezierEvaluator = new BezierEvaluator(controllPoint);
        ValueAnimator anim = ValueAnimator.ofObject(bezierEvaluator, startPosition, endPosition);
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        anim.addUpdateListener(this);
        anim.setDuration(500);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(NXHooldeView.this);
                if (animEndListener!=null){
                    animEndListener.endAnim();
                }
                NXHooldeView.this.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(this,"translationX", startPosition.x, endPosition.x).setDuration(500);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(this,"translationY", startPosition.y, endPosition.y).setDuration(500);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.00f).setDuration(500);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.00f).setDuration(500);
       // final ObjectAnimator anim5 = ObjectAnimator.ofFloat(this, "scaleX", 0.05f, 0.1f).setDuration(300);
        //final ObjectAnimator anim6 = ObjectAnimator.ofFloat(this, "scaleY", 0.05f, 0.1f).setDuration(300);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2).with(anim3).with(anim4).with(anim);
        animSet.start();
        //final AnimatorSet animSetEnd = new AnimatorSet();
        //animSetEnd.play(anim5).with(anim6);



    }
    public void startBeizerAnimation() {
        if (startPosition == null || endPosition == null) return;
        int pointX = (startPosition.x + endPosition.x) / 2;
        int pointY = (int) (startPosition.y - convertDpToPixel(200, mContext));
          Point controllPoint = new Point(pointX, pointY);

        BezierEvaluator bezierEvaluator = new BezierEvaluator(controllPoint);
        ValueAnimator anim = ValueAnimator.ofObject(bezierEvaluator, startPosition, endPosition);
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        anim.addUpdateListener(this);
        anim.setDuration(500);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(NXHooldeView.this);
                if (animEndListener!=null){
                    animEndListener.endAnim();
                }
                NXHooldeView.this.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(this,"translationX", startPosition.x, endPosition.x).setDuration(500);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(this,"translationY", startPosition.y, endPosition.y).setDuration(500);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.0f).setDuration(500);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.0f).setDuration(500);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2).with(anim3).with(anim4).with(anim);
        animSet.start();


    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Point point = (Point) animation.getAnimatedValue();
        setX(point.x);
        setY(point.y);
        invalidate();
    }


    public class BezierEvaluator implements TypeEvaluator<Point> {

        private Point controllPoint;

        public BezierEvaluator(Point controllPoint) {
            this.controllPoint = controllPoint;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue) {
            int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);
            int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);
            return new Point(x, y);
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
    public interface AnimEndListener{
        void endAnim();
    }
}
