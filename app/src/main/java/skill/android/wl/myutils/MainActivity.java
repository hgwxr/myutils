package skill.android.wl.myutils;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SwipeRefreshLayout swipe;
    private DemoAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = ((ListView) findViewById(R.id.list_view));
        swipe = ((SwipeRefreshLayout) findViewById(R.id.swipe));
        demoAdapter = new DemoAdapter(this);
        listView.setAdapter(demoAdapter);
        swipe.setRefreshing(true);

        swipe.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> datas= loadData();
                demoAdapter.addAll(datas);
                swipe.setRefreshing(false);
            }
        },1000);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> datas= loadDataNew();
                        //demoAdapter.addAll(datas);
                        demoAdapter.addAll(datas,0);
                        listView.setSelection(datas.size());
                        swipe.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private List<String> loadDataNew() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("===dataNew=="+i);
        }
        return strings;
    }

    private List<String> loadData() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
              strings.add("===data=="+i);
        }
        return strings;
    }
}
