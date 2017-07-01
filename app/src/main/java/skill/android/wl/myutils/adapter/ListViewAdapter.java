package skill.android.wl.myutils.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * wl 2017/1/7
 * baseadapter  再封装
 * @param <D>
 */
public  abstract class ListViewAdapter<D> extends BaseAdapter {
    protected Context context;
    protected List<D> list;
    private int[] layoutIds;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, @NonNull List<D> list, int[] layoutIds) {
        this.context = context;
        this.list = list;
        this.layoutIds = layoutIds;
        inflater = LayoutInflater.from(context);
    }

    public ListViewAdapter(Context context, int layoutId) {
        this(context, new ArrayList<D>(), new int[]{layoutId});
    }

    public ListViewAdapter(Context context, int[] layoutIds) {
        this(context, new ArrayList<D>(), layoutIds);
    }
    public ListViewAdapter(Context context) {
        this(context, new ArrayList<D>(), null);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public D getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {

        return layoutIds==null?super.getViewTypeCount():layoutIds.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //convertView = inflater.inflate(layoutIds[getItemViewType(position)], parent, false);
            convertView = inflater.inflate(layoutIds[getItemViewType(position)], parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        D data = list.get(position);
          onBindView(viewHolder, data,position);
        return convertView;
    }

    public void add(D data) {
        list.add(data);
        notifyDataSetChanged();
    }
    public void add(int index, D data) {
        list.add(index, data);
        notifyDataSetChanged();
    }

    public void remove(D data) {
        list.remove(data);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        list.remove(index);
        notifyDataSetChanged();
    }
    public void addAll(Collection<? extends D> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }
    public void addAll(Collection<? extends D> collection, int index) {
        list.addAll(index,collection);
        notifyDataSetChanged();
    }
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
     public abstract void onBindView(ViewHolder holder, D data, int position);

    public  class ViewHolder {
        private View itemView;

        public View getItemView() {
            return itemView;
        }

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
        public  <T extends View> T get(@IdRes int id) {
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
}
