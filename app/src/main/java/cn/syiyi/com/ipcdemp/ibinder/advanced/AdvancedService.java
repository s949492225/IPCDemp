package cn.syiyi.com.ipcdemp.ibinder.advanced;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import cn.syiyi.com.ipcdemp.R;

public class AdvancedService extends Service {
    public AdvancedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder(this);
    }

    private class MusicBinder extends PlaySub {

        private MediaPlayer mediaPlayer;
        private Context mContxt;

        public MusicBinder(Context mContxt) {
            this.mContxt = mContxt;
        }

        @Override
        public void start() {
            mediaPlayer = MediaPlayer.create(mContxt, R.raw.music);
            try {
                mediaPlayer.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Log.i("syiyi-music", e.getMessage());
            }
        }

        @Override
        public void stop() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }

        @Override
        public String getStatus() {
            return null;
        }
    }
}
