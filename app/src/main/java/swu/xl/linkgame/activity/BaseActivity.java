package swu.xl.linkgame.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import swu.xl.linkgame.constants.Constant;
import swu.xl.linkgame.manager.BackgroundMusicManager;
import swu.xl.linkgame.utils.StateUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // 判断是否进入后台
        if (StateUtil.isBackground(this)) {
            Log.d(Constant.TAG, "后台");

            // 暂停播放
            BackgroundMusicManager.getInstance(this).pauseBackgroundMusic();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // 继续播放背景音乐
        if (!BackgroundMusicManager.getInstance(this).isBackgroundMusicPlaying()) {
            BackgroundMusicManager.getInstance(this).resumeBackgroundMusic();
        }
    }
}
