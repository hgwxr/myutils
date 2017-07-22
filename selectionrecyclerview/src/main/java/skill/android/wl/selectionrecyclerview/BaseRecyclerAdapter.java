package skill.android.wl.selectionrecyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wl on 2016/12/26.
 */

public abstract class BaseRecyclerAdapter<T extends Object> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {
    protected List<T> mTList;
    protected Context mContext;

    protected  BaseRecyclerAdapter.OnItemClickListener<T> itemClickListener;
    private int ITEMADD=1;

    protected  int DEFAULTSPANSIZE=1;
    public void setItemClickListener(OnItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BaseRecyclerAdapter(Context mContext) {
        this.mTList = new ArrayList<>();
        this.mContext = mContext;

    }
    public T  getItem(int position){
        return mTList.get(position);
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(viewType, parent, false);
        BaseViewHolder holder = new BaseViewHolder(inflate);
        holder.setmContext(mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        final T data = mTList.get(position);
        bindDataToView(holder, data,position);
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null)
                itemClickListener.onClick(v,holder,data,position);
            }
        });
        holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemClickListener!=null)
                itemClickListener.onLongClick(v,holder,data,position);
                return false;
            }
        });
    }

    protected abstract void bindDataToView(BaseViewHolder holder, T data, int position) ;
    public void remove(int position){
        mTList.remove(position);
        notifyItemRangeRemoved(position,mTList.size()-position+1);
    }
    public void clear(){
        int size = mTList.size();
        mTList.removeAll(mTList);
        notifyItemRangeRemoved(0, size);
    }
    public void add(T t,int index){
        mTList.add(index,t);
        notifyItemRangeInserted(index, ITEMADD);
    }
    public void add(T t ){
        mTList.add(t);
        notifyItemRangeInserted(mTList.size()-ITEMADD, ITEMADD);
    }
    public  void addAll(List<T> tList){
         int start=mTList.size();
         mTList.addAll(tList);
        notifyItemRangeInserted(start,tList.size());
    }
    public  void addAll(List<T> tList, final int  position){
        final int start=mTList.size();
        this.mTList.addAll(position+1,tList);
//        notifyDataSetChanged();
        notifyItemRangeInserted(position+1,tList.size());
//        notifyItemRangeInserted(,mTList.size());
      /*  DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return start;
            }

            @Override
            public int getNewListSize() {
                return mTList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return oldList.get(position).equals(mTList.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return ;
            }
        });*/
//        diffResult.dispatchUpdatesTo(this);

    }
    @Override
    public int getItemCount() {
        return mTList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int TYPE = 1;
        TYPE=getItemType(position);
        return TYPE;
    }


    protected abstract @LayoutRes
    int getItemType(int position);

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class BaseViewHolder extends  RecyclerView.ViewHolder{


        public BaseViewHolder(View itemView) {
            super(itemView);
        }
        private Context mContext;

        public void setmContext(Context mContext) {
            this.mContext = mContext;
        }
        public Context getmContext() {
            return mContext;
        }

        public View getItemView(){
            return itemView;
        }
        public  <T extends View> T getView(int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) itemView.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                itemView.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = itemView.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }

    }
  public  interface OnItemClickListener<T>{
        void onClick(View view, BaseViewHolder holder, T data, int position);
        void onLongClick(View view, BaseViewHolder holder, T data, int position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemSpanSize(position,gridLayoutManager);
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    protected  int getItemSpanSize(int position, GridLayoutManager gridLayoutManager){

        return DEFAULTSPANSIZE;
    };
}
