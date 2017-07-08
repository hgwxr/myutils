package skill.android.wl.rulerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RulerView rulerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rulerView = ((RulerView) findViewById(R.id.ruler_view));
//        rulerView.setmStartIndex(20);
    }
}
