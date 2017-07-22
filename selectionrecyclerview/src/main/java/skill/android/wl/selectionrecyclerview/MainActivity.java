package skill.android.wl.selectionrecyclerview;

import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PtrFrameLayout frame;
    public void setViewToStatusBarHeight(View view, boolean isShowing, int color) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height=isShowing ?0:getStatusBarHeight();
        view.setBackgroundColor(ContextCompat.getColor(this,color));
        view.setLayoutParams(layoutParams);
//        RecyclerView.AdapterDataObserver
    }

    //获取状态栏高度
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ViewGroup root = ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content));
//        View view = new View(this);
//        root.addView(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        setViewToStatusBarHeight(view,false,R.color.colorAccent);
        recyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
        Selection1Adapter adapter = new Selection1Adapter(this);
//       List<ItemSelection> list= initData();
        List<DataBean> beanList= initBaseData(20);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.addAll(beanList);
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decor);
        recyclerView.setAdapter(adapter);















        frame = ((PtrFrameLayout) findViewById(R.id.store_house_ptr_frame));
        // header
//        final StoreHouseHeader header = new StoreHouseHeader(this);
//        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
//
//        header.initWithPointList(getPointList());

        frame.setDurationToCloseHeader(1000);
        final TextView header = new TextView(this);

        frame.setHeaderView(header);
        header.setPadding(0, PtrLocalDisplay.dp2px(30), 0, 0);
        header.setGravity(Gravity.CENTER);
        header.setTextSize(15);
        header.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        frame.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {
                header.setText("onUIReset  重置");
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
                header.setText("onUIRefreshPrepare  准备");
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {
                  header.setText("start  开始");
            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                header.setText("onUIRefreshComplete  完成");
            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                frame.autoRefresh(false);
            }
        }, 100);

        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
               // int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                return  PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);//firstVisibleItemPosition==0;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 2000);
            }
        });
        frame.autoRefresh();
    }

    private List<DataBean> initBaseData(int count) {

        ArrayList<DataBean> dataBeen = new ArrayList<>();
         if (count>0) {
             for (int i = 0; i < count; i++) {
                 DataBean bean = new DataBean();
                 bean.setHasChild(i % 2 == 0);
                 if (bean.isHasChild()) {
                     bean.setChailds(initBaseData(count - 5));
                 }
                 bean.setTitle("数据 child"+ count/5+" " + i);
                 dataBeen.add(bean);
             }
         }
        return dataBeen;
    }

    private ArrayList<float[]> getPointList() {
        // this point is taken from https://github.com/cloay/CRefreshLayout
        List<Point> startPoints = new ArrayList<Point>();
        startPoints.add(new Point(240, 80));
        startPoints.add(new Point(270, 80));
        startPoints.add(new Point(265, 103));
        startPoints.add(new Point(255, 65));
        startPoints.add(new Point(275, 80));
        startPoints.add(new Point(275, 80));
        startPoints.add(new Point(302, 80));
        startPoints.add(new Point(275, 107));

        startPoints.add(new Point(320, 70));
        startPoints.add(new Point(313, 80));
        startPoints.add(new Point(330, 63));
        startPoints.add(new Point(315, 87));
        startPoints.add(new Point(330, 80));
        startPoints.add(new Point(315, 100));
        startPoints.add(new Point(330, 90));
        startPoints.add(new Point(315, 110));
        startPoints.add(new Point(345, 65));
        startPoints.add(new Point(357, 67));
        startPoints.add(new Point(363, 103));

        startPoints.add(new Point(375, 80));
        startPoints.add(new Point(375, 80));
        startPoints.add(new Point(425, 80));
        startPoints.add(new Point(380, 95));
        startPoints.add(new Point(400, 63));

        List<Point> endPoints = new ArrayList<Point>();
        endPoints.add(new Point(270, 80));
        endPoints.add(new Point(270, 110));
        endPoints.add(new Point(270, 110));
        endPoints.add(new Point(250, 110));
        endPoints.add(new Point(275, 107));
        endPoints.add(new Point(302, 80));
        endPoints.add(new Point(302, 107));
        endPoints.add(new Point(302, 107));

        endPoints.add(new Point(340, 70));
        endPoints.add(new Point(360, 80));
        endPoints.add(new Point(330, 80));
        endPoints.add(new Point(340, 87));
        endPoints.add(new Point(315, 100));
        endPoints.add(new Point(345, 98));
        endPoints.add(new Point(330, 120));
        endPoints.add(new Point(345, 108));
        endPoints.add(new Point(360, 120));
        endPoints.add(new Point(363, 75));
        endPoints.add(new Point(345, 117));

        endPoints.add(new Point(380, 95));
        endPoints.add(new Point(425, 80));
        endPoints.add(new Point(420, 95));
        endPoints.add(new Point(420, 95));
        endPoints.add(new Point(400, 120));
        ArrayList<float[]> list = new ArrayList<float[]>();

        int offsetX = Integer.MAX_VALUE;
        int offsetY = Integer.MAX_VALUE;

        for (int i = 0; i < startPoints.size(); i++) {
            offsetX = Math.min(startPoints.get(i).x, offsetX);
            offsetY = Math.min(startPoints.get(i).y, offsetY);
        }
        for (int i = 0; i < endPoints.size(); i++) {
            float[] point = new float[4];
            point[0] = startPoints.get(i).x - offsetX;
            point[1] = startPoints.get(i).y - offsetY;
            point[2] = endPoints.get(i).x - offsetX;
            point[3] = endPoints.get(i).y - offsetY;
            list.add(point);
        }
        return list;
    }
    private List<ItemSelection> initData() {
        ArrayList<SelectionBean> selectionsBean = new ArrayList<>();
        for (int i = 'A'; i <='Z' ; i++) {
            SelectionBean bean = new SelectionBean();
            bean.setData(String.valueOf(i));
            bean.setHasMore(i%3==0);
            selectionsBean.add(bean);
        }
        ArrayList<ItemSelection> selections = new ArrayList<>();
        for (int i = 0; i <selectionsBean.size() ; i++) {
            ItemSelection itemSelection = new ItemSelection();
            itemSelection.setTitles(selectionsBean);
            selections.add(itemSelection);

        }
        return selections;
    }
}
