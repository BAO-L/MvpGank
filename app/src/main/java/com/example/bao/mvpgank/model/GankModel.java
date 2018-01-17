package com.example.bao.mvpgank.model;

/**
 * Created by BAO on 2017/12/6.
 */

public interface GankModel {
    void onLoadImage(OnLoadListener listener,int page);
    void onLoadAndroid(OnLoadListener listener,int page);
    void onLoadIos(OnLoadListener listener,int page);
    void onLoadDaily(OnLoadListener listener,int year,int month,int day);
}
