package skill.android.wl.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CountService";
    private CountService countService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         findViewById(R.id.start_service).setOnClickListener(this );
         findViewById(R.id.stop_service).setOnClickListener(this );
         findViewById(R.id.bind_service).setOnClickListener(this);
         findViewById(R.id.unbind_service).setOnClickListener(this);
         findViewById(R.id.get_data).setOnClickListener(this);
        findViewById(R.id.start_foreground_service).setOnClickListener(this);
        findViewById(R.id.stop_foreground_service).setOnClickListener(this);
    }
  private ServiceConnection connection=new ServiceConnection() {
      @Override
      public void onServiceConnected(ComponentName name, IBinder service) {
          CountService.IBinderImpl serviceB = (CountService.IBinderImpl) service;
          countService = serviceB.getCountService();
          Log.e(TAG, "onServiceConnected: ");
      }
      @Override
      public void onServiceDisconnected(ComponentName name) {
      }
  };
    @Override
    public void onClick(View v) {
        Intent service = new Intent(this, CountService.class);
        Intent   serviceIntent = new Intent(this, ForegroundService.class);
        switch (v.getId()) {
            case R.id.start_service:
                startService(service);
                break;
            case R.id.stop_service:
                stopService(service);
                break;
            case R.id.bind_service:
               bindService(service, connection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.get_data:
                Log.e(TAG, "onClick: "+ countService.getCount());
                break;
            case R.id.start_foreground_service:
                serviceIntent.putExtra("stop_foreground",0);//0  start  1  stop
                startService(serviceIntent);
                break;
            case R.id.stop_foreground_service:
                serviceIntent.putExtra("stop_foreground",1);//0  start  1  stop
                startService(serviceIntent);
                break;
        }
    }
}
