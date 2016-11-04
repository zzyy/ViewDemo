package com.zy.md;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.zy.md.base.net.GankApi;
import com.zy.md.base.net.NetRequest;
import com.zy.md.base.pojo.GankData;
import com.zy.md.base.pojo.Results;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.welcome.WelcomeActivity;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NetRequest.getGankApi().getMenu(GankApi.TYPE_ANDROID, 3, 1)
                .subscribeOn(Schedulers.io())
                .subscribe(listGankData -> {
                    Logger.d("success");
                }, throwable -> {
                    Logger.e(throwable, "网络请求报错");
                });

    }




}
