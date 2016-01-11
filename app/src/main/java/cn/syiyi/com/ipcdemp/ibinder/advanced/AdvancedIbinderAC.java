package cn.syiyi.com.ipcdemp.ibinder.advanced;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.syiyi.com.ipcdemp.R;

public class AdvancedIbinderAC extends AppCompatActivity {
    private TextView mTv_status;
    private Button mBtn_play;
    private Button mBtn_stop;
    private Button mBtn_exit;
    private PlayProxy mProxy;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_ibinder);
        mTv_status = (TextView) findViewById(R.id.tv_status);
        mBtn_play = (Button) findViewById(R.id.btn_play);
        mBtn_stop = (Button) findViewById(R.id.btn_stop);
        mBtn_exit = (Button) findViewById(R.id.btn_exit);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mProxy = new PlayProxy(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mProxy = null;
            }
        };
        bindService(new Intent(this, AdvancedService.class), connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.stop();
        unbindService(connection);
        stopService(new Intent(this, AdvancedIbinderAC.class));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play: {
                mProxy.start();
                mTv_status.setText(mProxy.getStatus());
                break;
            }
            case R.id.btn_stop: {
                mProxy.stop();
                mTv_status.setText(mProxy.getStatus());
                break;
            }
            case R.id.btn_exit: {
                finish();
                break;
            }
        }
    }
}
