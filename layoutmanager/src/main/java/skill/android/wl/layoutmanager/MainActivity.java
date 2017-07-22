package skill.android.wl.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
        DemoAdapter demoAdapter = new DemoAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(demoAdapter);
        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("key","data"+i);
            hashMaps.add(map);
        }
        demoAdapter.addAll(hashMaps);
    }
}
