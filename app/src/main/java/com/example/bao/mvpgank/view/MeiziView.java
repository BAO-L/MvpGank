package com.example.bao.mvpgank.view;

import com.example.bao.mvpgank.model.entity.Meizi;

import java.util.List;

/**
 * Created by BAO on 2017/12/6.
 */

public interface MeiziView {
    void showImages(List<Meizi.ResultsBean> list);
    void showFailed(Exception e,String s);
    void showProgress();
    void hideProgress();
}
