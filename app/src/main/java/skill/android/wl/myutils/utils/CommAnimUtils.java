package skill.android.wl.myutils.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import skill.android.wl.myutils.widget.NXHooldeView;


/**
 * @author wl
 * @date 2017/3/10
 * @content
 */

public class CommAnimUtils<T extends Activity>  {


    public static <T extends Activity> void animToCart(ImageView picture , T activity, final ImageView animTarget) {
        NXHooldeView nxHooldeView = new NXHooldeView(activity, picture.getDrawable());
        int[] stopPosition=new int[2];
        int[] startPosition=new int[2];
        animTarget.getLocationInWindow(stopPosition);
        stopPosition[0]-=animTarget.getWidth()*0.8;
        stopPosition[1]-=animTarget.getHeight()*1.5;
        picture.getLocationInWindow(startPosition);
        animTarget.setVisibility(View.VISIBLE);
        ViewCompat.setScaleX(animTarget,1.0f);
        ViewCompat.setScaleY(animTarget,1.0f);
        nxHooldeView.setStartPosition(new Point(startPosition[0],startPosition[1]));
        nxHooldeView.setEndPosition(new Point(stopPosition[0],stopPosition[1]));
        FrameLayout animLayout = CommAnimUtils.createAnimLayout(activity);
        animLayout.addView(nxHooldeView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nxHooldeView.startBeizerAnimationNew();
        nxHooldeView.setAnimEndListener(new NXHooldeView.AnimEndListener() {
            @Override
            public void endAnim() {
               /* if (animTarget.getVisibility()==View.VISIBLE){
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(animTarget, "scaleX",  1.0f,1.5f,1.0f, 0.5f, 0.0f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(animTarget, "scaleY", 1.0f,1.5f, 1.0f, 0.5f, 0.0f);
                    AnimatorSet animatorSet=new AnimatorSet();
                    animatorSet.play(scaleX).with(scaleY);
                    animatorSet.setDuration(500).start();
                }*/
            }
        });
    }

    public  static  <T extends ViewGroup> void  addAnimViewToViewGroup(T  layout, Drawable drawable){
        ImageView imageView = new ImageView(layout.getContext());
        imageView.setImageDrawable(drawable);
        layout.addView(imageView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public  static void  animViewToStartPosition(ImageView animView , int[] startPosition){
        ViewCompat.setTranslationX(animView, startPosition[0]);
        ViewCompat.setTranslationY(animView, startPosition[1]);
        ViewCompat.setScaleX(animView, 1.0f);
        ViewCompat.setScaleY(animView, 1.0f);
        ViewCompat.setRotation(animView, 0.0f);
    }

    public static void animStart(AnimatorSet animatorSet){

        animatorSet.start();
    }
    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    public static <T extends Activity> FrameLayout createAnimLayout(T activity){
        ViewGroup rootView = (ViewGroup)activity.getWindow().getDecorView();
        FrameLayout animLayout = new FrameLayout(activity);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }












    public static <T extends Activity> void addAction(ImageView startView, ImageView stopView, T activity) {
        NXHooldeView nxHooldeView = new NXHooldeView(activity, startView.getDrawable());

        int position[] = new int[2];
        startView.getLocationInWindow(position);
        nxHooldeView.setStartPosition(new Point(position[0], position[1]));
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        rootView.addView(nxHooldeView);
        int endPosition[] = new int[2];
        stopView.getLocationInWindow(endPosition);
        nxHooldeView.setEndPosition(new Point(endPosition[0] , endPosition[1]));
        nxHooldeView.startBeizerAnimation();
    }

    /**
     * 加入购物车动画
     * @param start
     * @param stop
     * @param animView
     * @param stopPosition
     */
    public static void addCartAnimator(ImageView start, View stop, final ImageView animView, int[] stopPosition) {
        animView.setImageDrawable(start.getDrawable());
        animView.setVisibility(View.VISIBLE);
        int[] statPosition = new int[2];
        start.getLocationInWindow(statPosition);
        ViewCompat.setTranslationX(animView, statPosition[0]);
        ViewCompat.setTranslationY(animView, statPosition[1]);
        ViewCompat.setScaleX(animView, 1.0f);
        ViewCompat.setScaleY(animView, 1.0f);
        ViewCompat.setRotation(animView, 0.0f);
        //int[] stopPosition = new int[2];
      //  stop.getLocationInWindow(stopPosition);
        stopPosition[0] = stopPosition[0];
        stopPosition[1] = stopPosition[1];

        String translationX = "translationX";
        String translationY = "translationY";
        // 旋转
        String rotation = "rotation";
        // 缩放
        String scaleX = "scaleX";
        String scaleY = "scaleY";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            ObjectAnimator anim5 = ObjectAnimator.ofFloat(animView, rotation, 0.0f, 360.0f).setDuration(500);
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(animView, scaleX, 1f, 0.5f);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(animView, scaleY, 1f, 0.5f);
            ObjectAnimator anim3 = ObjectAnimator.ofFloat(animView, translationX, statPosition[0],stopPosition[0]).setDuration(500);
            ObjectAnimator anim4 = ObjectAnimator.ofFloat(animView, translationY, statPosition[1], stopPosition[1]).setDuration(500);
            ObjectAnimator anim8 = ObjectAnimator.ofFloat(animView, scaleX, 1.0f, 0.0f).setDuration(500);
            ObjectAnimator anim9 = ObjectAnimator.ofFloat(animView, scaleY, 1.0f, 0.0f).setDuration(500);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(anim3).with(anim4).with(anim5).with(anim8).with(anim9);//.after(anim2).after(anim1);
            animSet.start();
            animSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animView.setVisibility(View.GONE);
                 //   ObjectAnimator anim1 = ObjectAnimator.ofFloat(MainActivity.shop_number, "scaleX", 1f, 1.2f, 1.0f);
                   // ObjectAnimator anim2 = ObjectAnimator.ofFloat(MainActivity.shop_number, "scaleY", 1f, 1.2f, 1.0f);
                  //  AnimatorSet animSet = new AnimatorSet();
                  //  animSet.playTogether(anim1, anim2);
                //    animSet.start();

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

        } else {
           /* ViewCompat.animate(MainActivity.cartImageViewAnima).scaleX(0.0f).scaleY(0.0f).rotation(720.0f).translationX(stopPosition[0]).translationY(stopPosition[1])
                    .setDuration(1000)
                    .start();*/
        }

    }


}
