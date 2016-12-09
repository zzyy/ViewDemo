package com.zy.md.ui.presenter;

import com.zy.md.base.view.BasePresenter;
import com.zy.md.data.model.GankDataModel;
import com.zy.md.ui.fragment.GankFragment;

import javax.inject.Inject;

/**
 * Created by Simon on 2016/12/9.
 */

public class GankFragmentPresenter extends BasePresenter {
    GankFragment mView;
    GankDataModel mGankDataModel;

    @Inject
    public GankFragmentPresenter(GankDataModel gankDataModel, GankFragment view) {
        mGankDataModel = gankDataModel;
        mView = view;
    }


    public void loadCatalogData(int pageNo){
        mGankDataModel.loadData(pageNo);

    }


}
