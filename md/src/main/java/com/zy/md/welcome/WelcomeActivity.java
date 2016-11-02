package com.zy.md.welcome;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import com.zy.md.R;
import com.zy.md.base.utils.SharedPreferenceUtils;
import com.zy.md.base.view.BaseActivity;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.vv_video_play)
    VideoView mVideoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferenceUtils.with(this).put( SplashActivity.IS_FIRST_ENTER_APP, false );

        mVideoPlayerView.setVideoURI( getVideoUri() );

        mVideoPlayerView.start();
    }


    private Uri getVideoUri(){
        String uriStr  = "android.resource://" + getPackageName() + "/" + R.raw.intro_video;
        return Uri.parse(uriStr);
    }

}
