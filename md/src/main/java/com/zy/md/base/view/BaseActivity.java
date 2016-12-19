package com.zy.md.base.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.zy.md.utils.TransitionHelper;

import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Simon on 2016/11/2.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    /**
     * 简写的findViewById, 自动强转
     */
    protected <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }

    public void start(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void start(Class clazz, Pair<View, String>... participants) {
        Intent intent = new Intent(this, clazz);

        BaseActivity.start(this, intent, participants);

    }

    public static void start(Activity activity, Intent intent, Pair<View, String>... participants) {
        Pair<View, String>[] allParticipants = TransitionHelper.createSafeTransitionParticipants(activity, true, participants);

        ActivityOptionsCompat transitionOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, allParticipants);

        ActivityCompat.startActivity(activity, intent, transitionOptions.toBundle());
    }

    protected Observable<Void> rxClick(@IdRes int id){
        return RxView.clicks($(id));
    }
}
