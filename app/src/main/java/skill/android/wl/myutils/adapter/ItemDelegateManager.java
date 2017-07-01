package skill.android.wl.myutils.adapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wl on 2017/6/12.
 */

public class ItemDelegateManager {
    private static final String TAG = ItemDelegateManager.class.getSimpleName();
    public ItemDelegateManager(){
        mMap=new HashMap<>();
    }
    public void addItemDelegate(HashMap<Class,ItemDataDelegates> itemDataDelegates){
        mMap=itemDataDelegates;
    }
    private HashMap<Class,ItemDataDelegates> mMap;
    public void handlerWayForItem(Object data, BaseRecyclerAdapter.BaseViewHolder holder, int position) {
        ItemDataDelegates itemDataDelegates = mMap.get(data.getClass());
        if (itemDataDelegates!=null){
            itemDataDelegates.handlerWayForItem(data, holder, position);
        }
        else
        throw new IllegalArgumentException("no handler ItemDataDelegates");
    }

    public int getItemType(List list, int position) {
        Object o = list.get(position);
        ItemDataDelegates itemDataDelegates = mMap.get(o.getClass());
        if (itemDataDelegates!=null){
            return  itemDataDelegates.getItemType(list, position);
        }else
         throw  new IllegalArgumentException("itemDataDelegates.getItemType error");
    }
}
