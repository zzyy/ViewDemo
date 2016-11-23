package com.zy.md.welcome;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.zy.md.MainActivity;
import com.zy.md.R;
import com.zy.md.utils.common.PreferenceUtils;
import com.zy.md.base.view.BaseActivity;

public class SplashActivity extends BaseActivity {

    static final String IS_FIRST_ENTER_APP = "IS_FIRST_ENTER_APP";

    private static final int WAIT_TIME = 500;
    private static final int MSG_GO = 1;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_GO:
                    goOtherActivity();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancleTimer();
    }

    private void goOtherActivity(){
        boolean isFirst = PreferenceUtils.with(this).get(IS_FIRST_ENTER_APP, true);
        if (isFirst){
            start( WelcomeActivity.class );
        }else {
            start(MainActivity.class);
        }
    }


    private void startTimer(){
        mHandler.sendEmptyMessageDelayed( MSG_GO, WAIT_TIME );
    }


    private void cancleTimer(){
        mHandler.removeMessages( MSG_GO );
    }
}
