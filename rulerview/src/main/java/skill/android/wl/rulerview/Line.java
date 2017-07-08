package skill.android.wl.rulerview;

import android.graphics.Color;

/**
 * <pre>
 * @date 2017/7/6
 * @author wl
 * @描述 Line 样式bean
 * @email hgwxrwl@gmail.com
 * @version : 1.0
 * </pre>
 */

public class Line {
    public static int DEFAULT_COLOR=Color.BLACK;
    private float width;
    private float height;
    private int color= DEFAULT_COLOR;
    private String desc;
    private float textSize=12;
    private int textColor=DEFAULT_COLOR;

    public Line(float width, float height, int color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Line(float width, float height, int color, String desc) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.desc = desc;
    }

    public Line(float width, float height, int color, String desc, float textSize) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.desc = desc;
        this.textSize = textSize;
    }

    public Line(float width, float height, int color, String desc, float textSize, int textColor) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.desc = desc;
        this.textSize = textSize;
        this.textColor = textColor;
    }

    public static int getDefaultColor() {
        return DEFAULT_COLOR;
    }

    public static void setDefaultColor(int defaultColor) {
        DEFAULT_COLOR = defaultColor;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
