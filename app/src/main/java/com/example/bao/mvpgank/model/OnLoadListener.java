package com.example.bao.mvpgank.model;

import com.example.bao.mvpgank.model.entity.Meizi;

import java.util.List;

/**
 * Created by BAO on 2017/12/6.
 */

public interface OnLoadListener {
    void onSuccess(List<Meizi.ResultsBean> list);
    void onFailed(Exception e,String s);
}
