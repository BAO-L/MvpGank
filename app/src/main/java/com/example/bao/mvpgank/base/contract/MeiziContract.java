package com.example.bao.mvpgank.base.contract;

import com.example.bao.mvpgank.base.BaseView;
import com.example.bao.mvpgank.model.entity.TechNews;

import java.util.List;

/**
 * Created by BAO on 2017/12/7.
 */

public class MeiziContract {
    public interface View{
        void showContent(List<TechNews.ResultsBean> list);
        void showFailed(String s);
        void showProgress();
        void hideProgress();
    }

    public interface Presenter{
        void getMeiziData();
    }
}
