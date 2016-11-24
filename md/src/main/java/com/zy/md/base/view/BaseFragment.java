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
        setupComponent();
    }

    protected void setupComponent(){};

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mButterKnifeUnbinder.unbind();
    }

    <T extends View> T $(@IdRes int id){
        return (T) getView().findViewById( id );
    }
}
