package skill.android.wl.layoutmanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

/**
 * <pre>
 * @date 2017/7/10
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class DemoAdapter extends BaseRecyclerAdapter<HashMap<String,String>> {
    public DemoAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected void bindDataToView(BaseViewHolder holder, HashMap<String, String> data, int position) {
        int r = (int) (Math.random()*255);
        int g = (int) (Math.random()*255);
        int b = (int) (Math.random()*255);
        setBackgroundOfVersion(holder.getView(R.id.tv_item),new ColorDrawable(Color.rgb(r,g,b)));
        ((TextView) holder.getView(R.id.tv_item)).setText("Data");
    }
    /**
     * 在API16以前使用setBackgroundDrawable，在API16以后使用setBackground
     * API16<---->4.1
     * @param view
     * @param drawable
     */
    private void setBackgroundOfVersion(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //Android系统大于等于API16，使用setBackground
            view.setBackground(drawable);
        } else {
            //Android系统小于API16，使用setBackground
            view.setBackgroundDrawable(drawable);
        }
    }


    @Override
    protected int getItemType(int position) {
        return R.layout.recycler_item;
    }
}
