package skill.android.wl.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * <pre>
 * @date 2017/7/10
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class CardLayoutManager  extends RecyclerView.LayoutManager{
    public static final int DEFAULT_GROUP_SIZE = 5;
    private int mGroupSize;
    private boolean isGravityCenter;
//    private Pool<> mItemFrames;

    public CardLayoutManager(boolean center) {
        this(DEFAULT_GROUP_SIZE, center);
    }
    public CardLayoutManager(int groupSize, boolean center) {
        mGroupSize = groupSize;
        isGravityCenter = center;
//        mItemFrames = new Pool<>(new Pool.New<Rect>() {
//            @Override
//            public Rect get() { return new Rect();}
//        });
    }
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
