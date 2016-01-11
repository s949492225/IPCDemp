package cn.syiyi.com.ipcdemp.ibinder.base;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.syiyi.com.ipcdemp.R;

public class BaseIbinderAC extends AppCompatActivity {
    private TextView mTv_status;
    private Button mBtn_play;
    private Button mBtn_stop;
    private Button mBtn_exit;
    private IBinder mIbinder;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibinder);
        mTv_status = (TextView) findViewById(R.id.tv_status);
        mBtn_play = (Button) findViewById(R.id.btn_play);
        mBtn_stop = (Button) findViewById(R.id.btn_stop);
        mBtn_exit = (Button) findViewById(R.id.btn_exit);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mIbinder = service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mIbinder = null;
            }
        };
        bindService(new Intent(this, IbinderService.class), connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play: {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    mIbinder.transact(ConsCode.START, data, reply, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_stop: {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    mIbinder.transact(ConsCode.STOP, data, reply, 0);
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
