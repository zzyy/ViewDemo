package com.zy.md;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.zy.md.base.net.GankApi;
import com.zy.md.base.net.NetRequest;
import com.zy.md.base.view.BaseActivity;
import com.zy.md.main.TestActivity;
import com.zy.md.main.ui.BaseRecyclerAdapter;
import com.zy.md.main.ui.DividerItemDecorarion;
import com.zy.md.main.ui.BannerAdapter;
import com.zy.md.main.ui.GankAdapter;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView mRecyclerView;
    GankAdapter mRecyclerAdapter;

    @BindView(R.id.vp_pic_show)
    ViewPager mPicShowViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private int pageNo = 1;

    private void initView() {
        BannerAdapter adapter = new BannerAdapter();
        mPicShowViewPager.setAdapter(adapter);

        NetRequest.getGankApi().getMenu(GankApi.TYPE_MEZI, 4, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listGankData -> {
                    adapter.setData( listGankData.getResults() );
                }, throwable -> {});


        mRecyclerAdapter = new GankAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration( new DividerItemDecorarion(20, 15, 20, 15));

        mRecyclerAdapter.setItemClickListener(new BaseRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.d(position);
                startActivity(TestActivity.class);
            }
        });

        mRecyclerAdapter.setLoadMoreListener(() -> {
            Logger.d("Load more");

            NetRequest.getGankApi().getMenu(GankApi.TYPE_ANDROID, 15, pageNo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(gankData -> {
                        Logger.d("success");
                        mRecyclerAdapter.addData(gankData.getResults());
                        pageNo++;
                    }, throwable -> {
                        Logger.e(throwable, "网络请求报错");
                    });


        });
        mRecyclerView.setAdapter(mRecyclerAdapter);


        NetRequest.getGankApi().getMenu(GankApi.TYPE_ANDROID, 15, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gankData -> {
                    Logger.d("success");
                    mRecyclerAdapter.setData(gankData.getResults());
                    pageNo++;
                }, throwable -> {
                    Logger.e(throwable, "网络请求报错");
                });

    }


}
