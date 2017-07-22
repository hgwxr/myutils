package skill.android.wl.selectionrecyclerview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * <pre>
 * @date 2017/7/20
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class Selection1Adapter  extends   BaseRecyclerAdapter<DataBean> {
    public Selection1Adapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected void bindDataToView(BaseViewHolder holder, final DataBean data, final int position) {
        ((TextView) holder.getView(R.id.title_head)).setText(data.getTitle());
        if (data.isHasChild()&&data.getChailds()!=null){
            final ImageView icon =  holder.getView(R.id.item_more);
            ViewCompat.setRotation(icon,icon.getRotation()+180);

            icon.setOnClickListener(new View.OnClickListener() {

                boolean open = false;
                @Override
                public void onClick(View v) {
                    ViewCompat.setRotation(icon,icon.getRotation()+180);
                    if (!open) {
                       if (data.getChailds().size()!=0)
                       { addChild(position);
                           open =!open;
                       }
                    }else{
                        if (data.getChailds().size()!=0)
                        { deleteChild(position);
                            open =!open;
                        }
                    }

                }

            });
        }
    }

    private void deleteChild(int position) {
        List<DataBean> chailds = mTList.get(position).getChailds();
        mTList.removeAll(chailds);
        notifyItemRangeChanged(position+1,mTList.size());
    }

    private void addChild(int position) {
        List<DataBean> chailds = mTList.get(position).getChailds();
        addAll(chailds,position);
    }

    @Override
    protected int getItemType(int position) {
        return R.layout.selection_item;
    }
}
