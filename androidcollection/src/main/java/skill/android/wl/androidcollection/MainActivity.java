package skill.android.wl.androidcollection;

import android.support.v4.util.Pair;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(1,"data1");
        sparseArray.put(2,"data1");
        sparseArray.put(3,"data3");
        sparseArray.put(1,"data1");
        sparseArray.append(4,"data4");//{1=data1, 2=data1, 3=data3, 4=data4}
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(4,4);
        SparseArrayCompat<Integer> sparseArrayCompat = new SparseArrayCompat<>();
        sparseArrayCompat.append(1,1);
        Pair<String, String> pair = Pair.create("a", "b");
        String first = pair.first;//a
        String second = pair.second;//b
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            ArrayMap<String, String> arrayMap = new ArrayMap<>();
            arrayMap.put("2","data2");
            arrayMap.put("2","data2");
            arrayMap.put("3","data2");
            arrayMap.put("4","data2");
            String data = arrayMap.get("2");
            Set<Map.Entry<String, String>> entries = arrayMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {

            }
            HashMap<String, String> hashMap = new HashMap<>();
            Collection<String> values = hashMap.values();
            for (String value : values) {

            }
            //arrayMap no Iterator
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                ArraySet<String> arraySet = new ArraySet<>();
                arraySet.add("asdas");
                arraySet.add("asdas");
                arraySet.add("asdas2");
                arraySet.add("asdas3");
                for (String s : arraySet) {
                }
                Iterator<String> iterator = arraySet.iterator();
                while (iterator.hasNext()){
                    iterator.next();
                }
            }
        }
    }
}
