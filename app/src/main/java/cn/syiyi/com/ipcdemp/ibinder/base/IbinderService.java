package cn.syiyi.com.ipcdemp.ibinder.base;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class IbinderService extends Service implements Handler.Callback {
    private PlayThread thread;
    private MyBinder myBinder;

    public IbinderService() {
        Handler mHander = new Handler(this);
        myBinder = new MyBinder(mHander);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myBinder = null;
        if (thread != null) {
            thread.stopThread();
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case ConsCode.START: {
                thread = new PlayThread();
                thread.start();
                break;
            }
            case ConsCode.STOP: {
                thread.stopThread();
                break;

            }
        }
        return false;
    }

    private class PlayThread extends Thread {

        public void stopThread() {
            yield();
            interrupt();
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Log.i("syiyi_ibinder", "音乐正在播放！！！");
                }
                Log.i("syiyi_ibinder", "音乐停止播放！！！");
            } catch (Exception e) {
                Log.i("syiyi_ibinder", "音乐线程终止===================");
            }
        }
    }
}

