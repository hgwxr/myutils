package skill.android.wl.rulerview;

/**
 * <pre>
 * @date 2017/7/6
 * @author wl
 * @描述 myutils
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public interface ILineCreator {

    Line getLine(int index,int parentHeight,float density);
}
