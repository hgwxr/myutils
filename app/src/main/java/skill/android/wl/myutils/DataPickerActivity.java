package skill.android.wl.myutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.weigan.loopview.LoopView;

import skill.android.wl.myutils.citypicker.CityBean;

public class DataPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_picker2);
        LoopView pView = ((LoopView) findViewById(R.id.data_p));
        LoopView cView =((LoopView) findViewById(R.id.data_c));
        LoopView aView = ((LoopView) findViewById(R.id.data_a));
        pView.setNotLoop();
        cView.setNotLoop();
        aView.setNotLoop();
        String data = Util.getTextFromAssets(this, "city.json");
        CityBean cityBean1 = new Gson().fromJson(data, CityBean.class);
        DataPickerHelper pickerHelper = new DataPickerHelper(pView, cView, aView);
        pickerHelper.handleLooperViewInit(cityBean1);
    }




}
