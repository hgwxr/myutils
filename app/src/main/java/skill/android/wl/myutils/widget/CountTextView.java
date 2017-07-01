package skill.android.wl.myutils.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import skill.android.wl.myutils.R;


/**
 * @author wl
 * @version :
 * @date 2017/5/19
 * @描述  验证码 自定义view 倒计时 开始计时，计时完恢复  计时中 变换背景
 */

public class CountTextView extends android.support.v7.widget.AppCompatTextView{


    private String getCheckCode;
    private String reGetCheckCode;
    private String checkCodeError;
    private String getCheckCodeCount;

    public CountTextView(Context context) {
        this(context,null);
    }

    public CountTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        /**
         get_check_code
         re_get_check_code
         check_code_error
         get_check_code_count
         */
        getCheckCode = getResources().getString(R.string.get_check_code);
        reGetCheckCode = getResources().getString(R.string.re_get_check_code);
        checkCodeError = getResources().getString(R.string.check_code_error);
        getCheckCodeCount = getResources().getString(R.string.get_check_code_count);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setText(getCheckCode);
    }

    private final int RUN=0;
    private final int STOP=1;
    private final int DEFAULT_COUNT=60;
    private  int start=DEFAULT_COUNT;
    private int delayMillis=1100;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RUN:
                    if (start==-1){
                        countOver();
                    }else {
                        setText(String.format(getCheckCodeCount, start--));
                        mHandler.sendEmptyMessageDelayed(RUN, delayMillis);
                    }
                    break;
                case STOP:
                    removeMessages(RUN);
                    countOver();
                    break;
            }
        }
    };
  private  int drawableId;//R.drawable.phone_check_code;

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    private void countOver() {
        setText(reGetCheckCode);
        setClickable(true);
        start=DEFAULT_COUNT;
        setBackgroundDrawable(ContextCompat.getDrawable(getContext(),getDrawableId()==0?getDrawableId():getDrawableId()));
    }

    public  void getCheckCodeError(){
        mHandler.removeMessages(RUN);
        setText(checkCodeError);
        setClickable(true);
        start=DEFAULT_COUNT;
    }
    public  void stopCount(){
        mHandler.sendEmptyMessage(STOP);
    }
    public void startCount(){
        setClickable(false);
        setBackgroundDrawable(ContextCompat.getDrawable(getContext(),getDrawableId()));
        mHandler.sendEmptyMessage(RUN);
    }
}
