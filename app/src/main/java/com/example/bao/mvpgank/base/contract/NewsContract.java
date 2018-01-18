package com.example.bao.mvpgank.base.contract;

import com.example.bao.mvpgank.model.entity.TechNews;

import java.util.List;

/**
 * Created by BAO on 2017/12/10.
 */

public class NewsContract {
    public interface View{
        void showContent(List<?> list);
        void showFailed(String s);
        void showProgress();
        void hideProgress();
    }

    public interface Presenter{
        void getNewsData(int page);
//        void getMoreMeiziData();
    }
}
