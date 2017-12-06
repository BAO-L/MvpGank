package com.example.bao.mvpgank.presenter;

import com.example.bao.mvpgank.model.entity.Meizi;
import com.example.bao.mvpgank.model.MeiziModel;
import com.example.bao.mvpgank.model.MeiziModelImpl;
import com.example.bao.mvpgank.model.OnLoadListener;
import com.example.bao.mvpgank.view.MeiziView;

import java.util.List;

/**
 * Created by BAO on 2017/12/6.
 */

public class MeiziPresenterImpl implements MeiziPresenter, OnLoadListener {

    private MeiziView meiziView;
    private MeiziModel meiziModel;

    public MeiziPresenterImpl(MeiziView meiziView) {
        this.meiziView = meiziView;
        meiziModel = new MeiziModelImpl();
    }

    @Override
    public void loadImagelList() {
        meiziView.showProgress();
        meiziModel.onLoadImage(this);
    }

    @Override
    public void onSuccess(List<Meizi.ResultsBean> list) {

        System.out.println("onSuccess-------------------");
        if (null != list){
            meiziView.showImages(list);
            meiziView.hideProgress();
        }
    }

    @Override
    public void onFailed(Exception e, String s) {
        meiziView.hideProgress();
        meiziView.showFailed(e,s);

    }
}
