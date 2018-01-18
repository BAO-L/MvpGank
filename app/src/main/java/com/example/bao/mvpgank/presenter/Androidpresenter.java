package com.example.bao.mvpgank.presenter;

import com.example.bao.mvpgank.base.contract.NewsContract;
import com.example.bao.mvpgank.model.GankModel;
import com.example.bao.mvpgank.model.GankModelImpl;
import com.example.bao.mvpgank.model.OnLoadListener;

import java.util.List;

/**
 * Created by BAO on 2017/12/10.
 */

public class Androidpresenter implements NewsContract.Presenter, OnLoadListener {
    private GankModel model;
    private NewsContract.View view;

    public Androidpresenter(NewsContract.View view) {
        this.view = view;
        model = new GankModelImpl();
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

    @Override
    public void getNewsData(int page) {
        model.onLoadAndroid(this,page);
    }
}
