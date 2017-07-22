package skill.android.wl.selectionrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * @date 2017/7/19
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class SelecttionAdapter  extends BaseRecyclerAdapter<ItemSelection> {
    public SelecttionAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected void bindDataToView(BaseViewHolder holder, ItemSelection data, int position) {
        data.onBindViewHolder(holder,position);
    }

    @Override
    protected int getItemType(int position) {
        return mTList.get(position).getLayoutId();
    }


}
