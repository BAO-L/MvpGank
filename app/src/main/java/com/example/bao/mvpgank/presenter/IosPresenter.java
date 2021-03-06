package com.example.bao.mvpgank.presenter;

import com.example.bao.mvpgank.base.contract.NewsContract;
import com.example.bao.mvpgank.model.GankModel;
import com.example.bao.mvpgank.model.GankModelImpl;
import com.example.bao.mvpgank.model.OnLoadListener;

import java.util.List;

/**
 * Created by BAO on 2017/12/10.
 */

public class IosPresenter implements NewsContract.Presenter, OnLoadListener {

    NewsContract.View view;
    GankModel model;

    public IosPresenter(NewsContract.View view) {
        this.view = view;
        model = new GankModelImpl();
    }

    @Override
    public void getNewsData(int page) {

        model.onLoadIos(this,page);
    }

    @Override
    public void onSuccess(List<?> list) {
        view.hideProgress();
        view.showContent(list);
    }

    @Override
    public void onFailed(String s) {
        view.hideProgress();
        view.showFailed(s);

    }
}
