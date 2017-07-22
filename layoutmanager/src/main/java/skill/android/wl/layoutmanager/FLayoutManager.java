package skill.android.wl.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <pre>
 * @date 2017/7/10
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class FLayoutManager extends RecyclerView.LayoutManager {

    private int mDecoratedChildWidth;
    private int mDecoratedChildHeight;
    private int mFirstVisiblePosition;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //Scrap measure one child
        View scrap = recycler.getViewForPosition(0);
        addView(scrap);
        measureChildWithMargins(scrap, 0, 0);


          /*
     * We make some assumptions in this code based on every child
     * view being the same size (i.e. a uniform grid). This allows
     * us to compute the following values up front because they
     * won't change.
     */
        mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap);
        mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap);
        detachAndScrapView(scrap, recycler);

        updateWindowSizing();
        int childLeft;
        int childTop;

    /*
     * Reset the visible and scroll positions
     */
        mFirstVisiblePosition = 0;
        childLeft = childTop = 0;

        //Clear all attached views into the recycle bin
        detachAndScrapAttachedViews(recycler);
        //Fill the grid for the initial layout of views
//        fillGrid(DIRECTION_NONE, childLeft, childTop, recycler);
    }

    private void updateWindowSizing() {

    }
}
