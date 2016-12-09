package com.zy.md.base.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Simon on 2016/11/24.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mButterKnifeUnbinder;

    protected abstract
    @LayoutRes
    int getLayoutId();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        mButterKnifeUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getPresenter();
    }

    /***
     * 用于获取presenter, 以便设置presenter的生命周期
     */
    protected BasePresenter getPresenter(){return null;}

    @Override
    public void onDestroyView() {
        getPresenter().onDestroyView();
        mButterKnifeUnbinder.unbind();
        super.onDestroyView();
    }

    <T extends View> T $(@IdRes int id){
        return (T) getView().findViewById( id );
    }


    /* 创建实例时复制参考
    public static BaseFragment newInstance(){
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    */
}
