package skill.android.wl.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class CountService extends Service {
    private static final String TAG = "CountService";
    int count;
    private boolean exit;
 private IBinderImpl binder;
    public    class IBinderImpl extends Binder{
      public   CountService   getCountService(){
            return  CountService.this;
        }
    }
    public CountService() {
        Log.e(TAG, "CountService: ");
    }

    public int getCount() {
        return count;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return binder;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: " );
        super.onCreate();
        binder=new IBinderImpl();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!exit) {
                        Thread.sleep(1000);
                        count++;
                        Log.e(TAG, "run: " + count);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " );
        exit=true;
        super.onDestroy();
    }
}
