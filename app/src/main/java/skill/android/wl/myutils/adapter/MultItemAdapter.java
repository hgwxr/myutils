package skill.android.wl.myutils.adapter;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by wl on 2017/6/12.
 */

public class MultItemAdapter  extends  BaseRecyclerAdapter {
    private  ItemDelegateManager mItemDataDelegates;
    public MultItemAdapter(Context mContext) {
        super(mContext);
        //声明 处理哪几种数据
        mItemDataDelegates=new ItemDelegateManager();
    }
    public  void addItemDelegate(HashMap<Class, ItemDataDelegates> itemDataDelegates){
        mItemDataDelegates.addItemDelegate(itemDataDelegates);
    }
    @Override
    protected void bindDataToView(BaseViewHolder holder, Object data, int position) {
          if (mItemDataDelegates!=null)
              mItemDataDelegates.handlerWayForItem(data,holder,position);
          else
              throw new IllegalArgumentException("no handler way");
    }
    @Override
    protected int getItemType(int position) {
        if (mItemDataDelegates!=null) {
            return mItemDataDelegates.getItemType(mTList,position);
        }
        else
        throw new IllegalArgumentException("No layout item ");
    }
}
