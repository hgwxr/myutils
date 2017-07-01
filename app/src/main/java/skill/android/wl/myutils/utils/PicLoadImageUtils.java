package skill.android.wl.myutils.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * @author wl
 * @date  2016/12/23.
 * @content 图片加载工具
 * 目前使用Glide加载 图片
 */

public class PicLoadImageUtils {


    private PicLoadImageUtils() {
    }

    public  void cancelLoadImage(Context context){
        Glide.with(context).onDestroy();
    }
    public  void pauseLoadImage(Context context){
        Glide.with(context).resumeRequests();
    }
    public  void reStartLoadImage(Context context){
        Glide.with(context).onStart();
    }
    public  void stopLoadImage(Context context){
        Glide.with(context).onStop();
    }
    /**
     * 实例化
     * @return
     */
    public static PicLoadImageUtils getInstance() {
        return new PicLoadImageUtils();
    }

    /**
     * 加载图片
     * @param context
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadImage(Context context, String url, @DrawableRes int error, @DrawableRes int placeHolder, ImageView target) {
         Glide.with(context).load(url).error(error).placeholder(placeHolder).crossFade(300).into(target);
    }
    /**
     * 加载图片NoAnimate
     * @param context
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadImageNoAnimate(Context context, String url, @DrawableRes int error, @DrawableRes int placeHolder, ImageView target) {
        Glide.with(context).load(url).dontAnimate().error(error).placeholder(placeHolder).into(target);
    }
    /**
     * 加载图片
     * @param context
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadImage(Activity context, String url, @DrawableRes int error, @DrawableRes int placeHolder, ImageView target) {
        Glide.with(context).load(url).error(error).placeholder(placeHolder).crossFade(300).into(target);
    }
    /**
     * 加载图片
     * @param context
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadImage(Fragment context, String url, @DrawableRes int error, @DrawableRes int placeHolder, ImageView target) {
        Glide.with(context).load(url).error(error).placeholder(placeHolder).crossFade(300).into(target);
    }
    /**
     * 加载图片
     * @param context
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadImage(FragmentActivity context, String url, @DrawableRes int error, @DrawableRes int placeHolder, ImageView target) {
        Glide.with(context).load(url).error(error).placeholder(placeHolder).crossFade(300).into(target);
    }
    /**
     * 加载图片NoAnimate
     * @param context
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadImageNoAnimate(FragmentActivity context, String url, @DrawableRes int error, @DrawableRes int placeHolder, ImageView target) {
        Glide.with(context).load(url).dontAnimate().error(error).placeholder(placeHolder).into(target);
    }

    /**
     * 圆图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadCircleImage(final Context t, String url, @DrawableRes int error, @DrawableRes int placeHolder, final ImageView target) {
        Glide.with(t).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    /**
     * 圆图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadCircleImage(final FragmentActivity t, String url, @DrawableRes int error, @DrawableRes int placeHolder, final ImageView target) {
        DrawableTypeRequest<String> load = Glide.with(t).load(url);
        load.error(error).placeholder(placeHolder);
        load.asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    /**
     * 圆图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadCircleImage(final Fragment t, String url, @DrawableRes int error, @DrawableRes int placeHolder, final ImageView target) {
        Glide.with(t).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    /**
     * 圆图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     */
    public void loadCircleImage(final Activity t, String url, @DrawableRes int error, @DrawableRes int placeHolder, final ImageView target) {
        Glide.with(t).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    /**
     * 圆角图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     * @param coners
     */
    public void loadRoundImage(final Context t, String url, @IdRes int error, @IdRes int placeHolder, final ImageView target, final int... coners) {
        Glide.with(t).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                Rect bounds = new Rect();
                if (coners.length < 4) {
                    bounds.set(coners[0], coners[0], coners[0], coners[0]);
                } else {
                    bounds.set(coners[0], coners[1], coners[2], coners[3]);
                }
                circularBitmapDrawable.setBounds(bounds);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });

    }
    /**
     * 圆角图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     * @param coners
     */
    public void loadRoundImage(final Activity t, String url, @IdRes int error, @IdRes int placeHolder, final ImageView target, final int... coners) {
        Glide.with(t).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                Rect bounds = new Rect();
                if (coners.length < 4) {
                    bounds.set(coners[0], coners[0], coners[0], coners[0]);
                } else {
                    bounds.set(coners[0], coners[1], coners[2], coners[3]);
                }
                circularBitmapDrawable.setBounds(bounds);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });

    }
    /**
     * 圆角图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     * @param coners
     */
    public void loadRoundImage(final Fragment t, String url, @IdRes int error, @IdRes int placeHolder, final ImageView target, final int... coners) {
        Glide.with(t).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                Rect bounds = new Rect();
                if (coners.length < 4) {
                    bounds.set(coners[0], coners[0], coners[0], coners[0]);
                } else {
                    bounds.set(coners[0], coners[1], coners[2], coners[3]);
                }
                circularBitmapDrawable.setBounds(bounds);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
    /**
     * 圆角图
     * @param t
     * @param url
     * @param error
     * @param placeHolder
     * @param target
     * @param coners
     */
    public void loadRoundImage(final FragmentActivity t, String url, @IdRes int error, @IdRes int placeHolder, final ImageView target, final int... coners) {
        Glide.with(t).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(t.getResources(), resource);
                Rect bounds = new Rect();
                if (coners.length < 4) {
                    bounds.set(coners[0], coners[0], coners[0], coners[0]);
                } else {
                    bounds.set(coners[0], coners[1], coners[2], coners[3]);
                }
                circularBitmapDrawable.setBounds(bounds);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });

    }
}
