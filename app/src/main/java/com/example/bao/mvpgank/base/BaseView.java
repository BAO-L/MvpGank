package com.example.bao.mvpgank.base;

import java.util.List;

/**
 * Created by BAO on 2017/12/7.
 */

public interface BaseView {

    void showErrorMsg(String msg);


    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
