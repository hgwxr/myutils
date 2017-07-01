package skill.android.wl.myutils.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;


/**
 * @author wl
 * @version :
 * @date 2017/3/29
 * @描述
 */

public class LoadingAnimProcessBar extends ProgressBar {
    private static final String TAG = LoadingAnimProcessBar.class.getSimpleName();
    private Context mContext;
    private boolean isRunning;
    private ValueAnimator animator;
    private long PROCESS_ANIM_TIME=1000;

    public LoadingAnimProcessBar(Context context) {
        this(context,null);
    }

    public LoadingAnimProcessBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingAnimProcessBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
    }
    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
          if (!isRunning&&progress!=0){
              if (getTag()!=null){
                  return ;
              }
              animator = ValueAnimator.ofInt(progress).setDuration(PROCESS_ANIM_TIME);
              this.setTag(animator);
              this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
              animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                  @Override
                  public void onAnimationUpdate(ValueAnimator animation) {
                      if (LoadingAnimProcessBar.this != null) {
                        //  LogUtil.e(TAG,animation.getAnimatedValue()+" ");
                          setProgress(((Integer) animation.getAnimatedValue()));
                      } else {
                          animation.cancel();
                          isRunning=false;
                      }
                  }
              });
              animator.addListener(new Animator.AnimatorListener() {
                  @Override
                  public void onAnimationEnd(Animator animation) {
                          isRunning=false;
                      LoadingAnimProcessBar.this.setTag(null);
                      LoadingAnimProcessBar.this.setLayerType(View.LAYER_TYPE_NONE, null);
                  }
                  @Override
                  public void onAnimationCancel(Animator animation) {
                  }
                  @Override
                  public void onAnimationRepeat(Animator animation) {
                  }
                  @Override
                  public void onAnimationStart(Animator animation) {
                      isRunning=true;
                  }
              });
              animator.start();
          }

        }
     public void resetAnimProcessBar(){
         if (animator!=null){
             animator.cancel();
             animator=null;
             isRunning=false;
         }
     }
}
