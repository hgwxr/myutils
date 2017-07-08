package skill.android.wl.myutils;

import android.support.annotation.NonNull;

import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import skill.android.wl.myutils.citypicker.CityBean;

/**
 * <pre>
 * @date 2017/7/5
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class DataPickerHelper {
    private LoopView mPView;
    private LoopView mCView;
    private LoopView mAView;
   public  DataPickerHelper(@NonNull LoopView pView, @NonNull LoopView cView, @NonNull LoopView aView){
       mPView=pView;
       mCView=cView;
       mAView=aView;
   }
    public void handleLooperViewInit(final CityBean cityBean) {
        List<IDataLooper> ps=new ArrayList<>();
        for (CityBean.P p : cityBean.getpList()) {
            ps.add(p);
        }
        initDefaultData(mPView, ps);
        //设置初始位置
        mPView.setInitPosition(0);
        List<IDataLooper> cs=new ArrayList<>();
        CityBean.P pLs = cityBean.getpList().get(0);
        if (pLs!=null&pLs.getcList()!=null){
            for (CityBean.P.C p : pLs.getcList()) {
                cs.add(p);
            }
            initDefaultData(mCView, cs);
            mCView.setInitPosition(0);
        }
        List<IDataLooper> as=new ArrayList<>();
        CityBean.P.C c = cityBean.getpList().get(0).getcList().get(0);
        if (c.getaList()!=null) {
            for (CityBean.P.C.A p : c.getaList()) {
                as.add(p);
            }
            initDefaultData(mCView, as);
            mAView.setInitPosition(0);
        }else{
            ArrayList<String> items = new ArrayList<>();
            items.add("---");
            mAView.setItems(items);
        }
        mPView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                CityBean.P p = cityBean.getpList().get(index);
                List<CityBean.P.C> cList = p.getcList();
                if (cList !=null) {
                    List<IDataLooper> ps=new ArrayList<>();
                    for (CityBean.P.C c : cList) {
                        ps.add(c);
                    }
                    initDefaultData(mCView,ps);
                    mCView.setTag(index);
                    mCView.setInitPosition(0);
                    List<CityBean.P.C.A> aList = cList.get(0).getaList();
                    if (aList!=null){
                        List<IDataLooper> as=new ArrayList<>();
                        for (CityBean.P.C.A a : aList) {
                            as.add(a);
                        }
                        initDefaultData(mAView,as);
                        mAView.setInitPosition(0);
                    }else{
                        ArrayList<String> items = new ArrayList<>();
                        items.add("---");
                        mAView.setItems(items);
                    }
                }
            }
        });
        mCView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Object tag = mCView.getTag();
                if (tag !=null&&tag instanceof Integer) {
                    Integer position = (Integer) tag;
                    List<CityBean.P> pList = cityBean.getpList();
                    if (position<pList.size()) {
                        List<CityBean.P.C> cs = pList.get(position).getcList();
                        if (cs!=null){
                            CityBean.P.C c = cs.get(index);
                            if (c!=null&&c.getaList()!=null){
                                List<CityBean.P.C.A> aList = c.getaList();
                                List<IDataLooper> ps=new ArrayList<>();
                                for (CityBean.P.C.A a : aList) {
                                    ps.add(a);
                                }
                                initDefaultData(mAView,ps);
                                mAView.setInitPosition(0);
                            }else{
                                ArrayList<String> items = new ArrayList<>();
                                items.add("---");
                                mAView.setItems(items);
                            }
                        }
                    }
                }
            }
        });
    }
    private void initDefaultData(LoopView mPView, List<IDataLooper> iDataLoopers) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < iDataLoopers.size(); i++) {
            arrayList.add(iDataLoopers.get(i).getTextString());
        }
        mPView.setItems(arrayList);
    }
}
