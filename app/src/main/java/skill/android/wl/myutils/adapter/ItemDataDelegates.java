package skill.android.wl.myutils.adapter;

import android.support.annotation.LayoutRes;

import java.util.List;

/**
 * Created by wl on 2017/6/12.
 */

public interface ItemDataDelegates<T> {
     @LayoutRes
     int getItemType(List list, int position);
     void  handlerWayForItem(T data, BaseRecyclerAdapter.BaseViewHolder holder, int position);
}
