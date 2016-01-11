package cn.syiyi.com.ipcdemp.ibinder.advanced;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

import cn.syiyi.com.ipcdemp.ibinder.advanced.ia.IPlay;

/**
 * IPCDemp...........
 * cn.syiyi.com.ipcdemp.ibinder.advanced...........
 * Created by lintao.song on 2016/1/11.
 */
public abstract class PlaySub extends Binder implements IPlay {
    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        reply.writeString(data.readString() + "----MP3");
        switch (code) {
            case 0:
                this.start();
                break;
            case 1:
                this.stop();
                break;
        }
        return true;
    }

}
