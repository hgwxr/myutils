package skill.android.wl.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v7.app.NotificationCompat;

public class ForegroundService extends Service {

    /**
     * id不可设置为0,否则不能设置为前台service
     */
    private static final int NOTIFICATION_DOWNLOAD_PROGRESS_ID = 0x0001;
    private int   startForegound = 0;

    public ForegroundService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int flag = intent.getExtras().getInt("stop_foreground");

        if (flag== startForegound){
            createNoticetion();
        }else {
              stopForeground(true);
        }
        return super.onStartCommand(intent, flags, startId);
    }


        /**
         * Notification
         */
        public void createNoticetion(){
            //使用兼容版本
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            //设置状态栏的通知图标
            builder.setSmallIcon(R.mipmap.ic_launcher);
            //设置通知栏横条的图标
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
            //禁止用户点击删除按钮删除
            builder.setAutoCancel(false);
            //禁止滑动删除
            builder.setOngoing(true);
            //右上角的时间显示
            builder.setShowWhen(true);
            //设置通知栏的标题内容
            builder.setContentTitle("I am Foreground Service!!!");
            //设置点击

            //创建通知
            Notification notification = builder.build();

            //设置为前台服务
            startForeground(NOTIFICATION_DOWNLOAD_PROGRESS_ID,notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      return  null;
    }

}
