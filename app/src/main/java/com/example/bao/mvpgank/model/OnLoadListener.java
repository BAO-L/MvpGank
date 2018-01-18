package com.example.bao.mvpgank.model;


import java.util.List;

/**
 * Created by BAO on 2017/12/6.
 */

public interface OnLoadListener {
    void onSuccess(List<?> list);
    void onFailed(String s);
}
