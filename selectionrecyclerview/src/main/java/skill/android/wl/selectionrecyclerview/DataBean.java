package skill.android.wl.selectionrecyclerview;

import java.util.List;

/**
 * <pre>
 * @date 2017/7/20
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class DataBean {
    private   boolean hasChild;
    private List<DataBean>  chailds;
    private String  title;

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public List<DataBean> getChailds() {
        return chailds;
    }

    public void setChailds(List<DataBean> chailds) {
        this.chailds = chailds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
