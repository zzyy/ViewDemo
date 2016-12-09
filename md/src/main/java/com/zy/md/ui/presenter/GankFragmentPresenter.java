package com.zy.md.ui.presenter;

import com.orhanobut.logger.Logger;
import com.zy.md.base.view.BasePresenter;
import com.zy.md.data.model.GankDataModel;
import com.zy.md.ui.fragment.GankFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Simon on 2016/12/9.
 */

public class GankFragmentPresenter extends BasePresenter {
    List<Subscription> mSubscriptionList = new ArrayList<>();
    GankFragment mView;
    GankDataModel mGankDataModel;

    @Inject
    public GankFragmentPresenter(GankDataModel gankDataModel, GankFragment view) {
        mGankDataModel = gankDataModel;
        mView = view;
    }


    public void loadCatalogData(int pageNo) {
        final Subscription subscription = mGankDataModel.loadData(pageNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(datas -> mView.onLoadCatalogDataComplete(datas),
                        throwable -> {
                            Logger.e(throwable, "load catalog error");
                            mView.onLoadCatalogDataError();});

        addSubscription(subscription);
    }


    private void addSubscription(Subscription subscription) {
        if (subscription == null) return;
        mSubscriptionList.add(subscription);
    }

    private void unbindSubsciptions() {
        for (Subscription subscription : mSubscriptionList) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindSubsciptions();
    }
}
