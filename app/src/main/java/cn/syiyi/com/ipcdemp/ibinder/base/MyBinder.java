package cn.syiyi.com.ipcdemp.ibinder.base;

import android.os.Binder;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * IPCDemp...........
 * cn.syiyi.com.ipcdemp.ibinder...........
 * Created by lintao.song on 2016/1/11.
 */
class MyBinder extends Binder {
    private Handler mHander;

    public MyBinder(Handler mHander) {
        this.mHander = mHander;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case ConsCode.START: {
                mHander.sendEmptyMessage(ConsCode.START);
                break;
            }
            case ConsCode.STOP: {
                mHander.sendEmptyMessage(ConsCode.STOP);
                break;
            }
        }
        return true;
    }
}
