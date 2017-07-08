package skill.android.wl.myutils.citypicker;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import skill.android.wl.myutils.IDataLooper;

/**
 * <pre>
 * @date 2017/7/5
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class CityBean  {
    @SerializedName("citylist")
    private List<P> pList;

    public List<P> getpList() {
        return pList;
    }

    public void setpList(List<P> pList) {
        this.pList = pList;
    }



    public static class P implements IDataLooper {
        @SerializedName("p")
        private String p;
        @SerializedName("c")
        private List<C> cList;

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public List<C> getcList() {
            return cList;
        }

        public void setcList(List<C> cList) {
            this.cList = cList;
        }



        @Override
        public String getTextString() {
            return p;
        }

        public static class C implements IDataLooper {
            @SerializedName("n")
            private String n;
            @SerializedName("a")
            private List<A> aList;

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public List<A> getaList() {
                return aList;
            }

            public void setaList(List<A> aList) {
                this.aList = aList;
            }



            @Override
            public String getTextString() {
                return n;
            }

            public static class A implements IDataLooper {
                @SerializedName("s")
                private String s;

                public String getS() {
                    return s;
                }

                public void setS(String s) {
                    this.s = s;
                }



                @Override
                public String getTextString() {
                    return s;
                }
            }
        }

    }

}
