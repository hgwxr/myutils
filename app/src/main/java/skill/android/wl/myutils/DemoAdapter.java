package skill.android.wl.myutils;

import android.content.Context;
import android.widget.TextView;

import java.util.HashMap;

import skill.android.wl.myutils.adapter.ListViewAdapter;

/**
 * <pre>
 * @date 2017/7/1
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class DemoAdapter  extends ListViewAdapter<String> {
    public DemoAdapter(Context context) {
        super(context,R.layout.list_item);
    }

    @Override
    public void onBindView(ViewHolder holder,  String data, int position) {
        TextView textView =  holder.get(R.id.tv_item);
        textView.setText(data);
    }
}
