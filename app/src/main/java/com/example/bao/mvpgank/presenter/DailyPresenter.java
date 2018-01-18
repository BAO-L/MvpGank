package com.example.bao.mvpgank.presenter;

import com.example.bao.mvpgank.base.contract.MeiziContract;
import com.example.bao.mvpgank.base.contract.NewsContract;
import com.example.bao.mvpgank.model.GankModel;
import com.example.bao.mvpgank.model.GankModelImpl;
import com.example.bao.mvpgank.model.OnLoadListener;

import java.util.List;

/**
 * Created by BAO on 2017/12/11.
 */

public class DailyPresenter implements NewsContract.Presenter, OnLoadListener {

    NewsContract.View view;
    GankModel model;

    public DailyPresenter(NewsContract.View view) {
        this.view = view;
        model = new GankModelImpl();
    }

    @Override
    public void getNewsData(int page) {
        view.showProgress();
        model.onLoadDaily(this,2017,12,15);

    }

    @Override
    public void onSuccess(List<?> list) {
        view.hideProgress();
        view.showContent(list);
    }

    @Override
    public void onFailed(Exception e, String s) {
        view.hideProgress();
        view.showFailed(s);

    }
}
