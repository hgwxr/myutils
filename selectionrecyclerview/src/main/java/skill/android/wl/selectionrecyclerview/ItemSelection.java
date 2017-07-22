package skill.android.wl.selectionrecyclerview;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ItemSelection {
    private List<SelectionBean> titles;
    public ItemSelection(){
        titles=new ArrayList<>();
    }

    public void setTitles(List<SelectionBean> titles) {
        this.titles = titles;
    }

    public @LayoutRes int getLayoutId(){
        return R.layout.selection_item;
    }
    public  void onBindViewHolder(final BaseRecyclerAdapter.BaseViewHolder holder, int position){
        final SelectionBean data = titles.get(position);
        if (!data.isHasMore()){
            holder.getView(R.id.item_more).setVisibility(View.INVISIBLE);
        }else{
            holder.getView(R.id.item_more).setVisibility(View.VISIBLE);
           /* holder.getView(R.id.item_more).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.getmContext(),"to do  expanded  child",Toast.LENGTH_SHORT).show();
                }
            });*/

        }

        final ImageView more =  holder.getView(R.id.item_more);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.isHasMore()){
                    ViewCompat.setRotation(more,more.getRotation()+180);
                    if (!data.isExpanded()){
                        addItemSelection();
                    }
                   data.setExpanded(!data.isExpanded());
                }else{
//                    Toast.makeText(holder.getmContext(),"no  child",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ((TextView) holder.getView(R.id.title_head)).setText((String.valueOf((char) Integer.parseInt(data.getData()))));
    }

    private void addItemSelection() {

    }
}
