package com.zmm.tmsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmm.tmsystem.application.AppApplication;
import com.zmm.tmsystem.dagger.component.AppComponent;
import com.zmm.tmsystem.mvp.presenter.BasePresenter;
import com.zmm.tmsystem.mvp.view.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/24
 * Time:下午1:25
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {



    private Unbinder mUnbinder;
    private AppApplication mAppApplication;
    private View mRootView;


    @Inject
    T mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(setLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);



        return mRootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAppApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mAppApplication.getAppComponent());

        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }


    @Override
    public void showLoading() {
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {
    }


    protected abstract void init();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract int setLayout();

}
