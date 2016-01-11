package cn.syiyi.com.ipcdemp.ibinder.advanced;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import cn.syiyi.com.ipcdemp.ibinder.advanced.ia.IPlay;

/**
 * IPCDemp...........
 * cn.syiyi.com.ipcdemp.ibinder.advanced...........
 * Created by lintao.song on 2016/1/11.
 */
public class PlayProxy implements IPlay {
    private IBinder mIbinder;
    private String status;

    public PlayProxy(IBinder mIbinder) {
        this.mIbinder = mIbinder;
    }

    @Override
    public void start() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeString("playing");
            mIbinder.transact(0, data, reply, 0);
            status = reply.readString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeString("stop");
            mIbinder.transact(1, data, reply, 0);
            status = reply.readString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getStatus() {
        return status;
    }
}
