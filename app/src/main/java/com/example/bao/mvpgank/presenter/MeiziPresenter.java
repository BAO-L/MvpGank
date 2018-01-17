package com.example.bao.mvpgank.presenter;

import com.example.bao.mvpgank.base.contract.MeiziContract;
import com.example.bao.mvpgank.model.GankModel;
import com.example.bao.mvpgank.model.GankModelImpl;
import com.example.bao.mvpgank.model.OnLoadListener;
import com.example.bao.mvpgank.model.entity.TechNews;

import java.util.List;

/**
 * Created by BAO on 2017/12/7.
 */

public class MeiziPresenter implements MeiziContract.Presenter, OnLoadListener {
    private MeiziContract.View meiziView;
    private GankModel meiziModel;

    public MeiziPresenter(MeiziContract.View meiziView) {
        this.meiziView = meiziView;
        meiziModel = new GankModelImpl();
    }

    @Override
    public void getMeiziData(int page) {
        meiziView.showProgress();
        meiziModel.onLoadImage(this,page);

    }




    @Override
    public void onSuccess(List<?> list) {
        meiziView.hideProgress();
        meiziView.showContent((List<TechNews.ResultsBean>) list);
    }

    @Override
    public void onFailed(Exception e, String s) {
        meiziView.hideProgress();
        meiziView.showFailed(s);

    }
}
