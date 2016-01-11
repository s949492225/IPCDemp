package cn.syiyi.com.ipcdemp.ibinder.google;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.syiyi.com.ipcdemp.R;
import cn.syiyi.com.ipcdemp.ibinder.google.aidl.IMusicAidlInterface;

public class GIbinderAC extends AppCompatActivity {
    private TextView mTv_status;
    private Button mBtn_play;
    private Button mBtn_stop;
    private Button mBtn_exit;
    private IMusicAidlInterface mProxy;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_ibinder);
        mTv_status = (TextView) findViewById(R.id.tv_status);
        mBtn_play = (Button) findViewById(R.id.btn_play);
        mBtn_stop = (Button) findViewById(R.id.btn_stop);
        mBtn_exit = (Button) findViewById(R.id.btn_exit);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mProxy = IMusicAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mProxy = null;
            }
        };
        bindService(new Intent(this, GService.class), connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mProxy.stop();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(connection);
        stopService(new Intent(this, GIbinderAC.class));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play: {
                try {
                    mProxy.start();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_stop: {
                try {
                    mProxy.stop();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_exit: {
                finish();
                break;
            }
        }
    }
}
