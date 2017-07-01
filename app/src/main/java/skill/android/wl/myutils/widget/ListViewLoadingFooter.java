package skill.android.wl.myutils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import skill.android.wl.myutils.R;


/**
 * @author wl
 * @date 2017/3/23
 * @content
 */

public class ListViewLoadingFooter extends ListView {
    private Context mContext;
    private LinearLayout loading;
    private RelativeLayout complete;
    private RelativeLayout error;

    public ListViewLoadingFooter(Context context) {
        this(context,null);
    }

    public ListViewLoadingFooter(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListViewLoadingFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initFooter();
    }

    private void initFooter() {
        View footer = LayoutInflater.from(mContext).inflate(R.layout.activity_comm_footer, null, false);
        loading = ((LinearLayout) footer.findViewById(R.id.loading));
        complete = ((RelativeLayout) footer.findViewById(R.id.complete));
        error = ((RelativeLayout) footer.findViewById(R.id.error));
        addFooterView(footer);
        loading();
    }
    public  void loading(){
        loading.setVisibility(VISIBLE);
        complete.setVisibility(GONE);
        error.setVisibility(GONE);
    }
    public  void complete(){
        loading.setVisibility(GONE);
        complete.setVisibility(VISIBLE);
        error.setVisibility(GONE);
    }
    public  void completeError(){
        loading.setVisibility(GONE);
        complete.setVisibility(GONE);
        error.setVisibility(VISIBLE);
    }
}
