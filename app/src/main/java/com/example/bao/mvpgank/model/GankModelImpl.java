package com.example.bao.mvpgank.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.example.bao.mvpgank.app.App;
import com.example.bao.mvpgank.app.Constants;
import com.example.bao.mvpgank.http.ApiService;
import com.example.bao.mvpgank.http.HttpUtil;
import com.example.bao.mvpgank.model.entity.Daily;
import com.example.bao.mvpgank.model.entity.MultiItem;
import com.example.bao.mvpgank.model.entity.Title;
import com.example.bao.mvpgank.model.entity.TechNews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BAO on 2017/12/6.
 */

public class GankModelImpl implements GankModel {
    @Override
    public void onLoadImage(final OnLoadListener listener, int page) {

        ApiService service = HttpUtil.getService();

//        Call<TechNews> call = service.getMeizi(Constants.PAGE_SIZE, page);
        Call<TechNews> call = service.getRandomMeizi(Constants.PAGE_SIZE);
        call.enqueue(new Callback<TechNews>() {
            @Override
            public void onResponse(Call<TechNews> call, Response<TechNews> response) {
                List<TechNews.ResultsBean> list = response.body().getResults();
                listener.onSuccess(list);
            }

            @Override
            public void onFailure(Call<TechNews> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadAndroid(OnLoadListener listener, int page) {

        ApiService service = HttpUtil.getService();
        retrofit2.Call<TechNews> call = service.getAndroid(Constants.PAGE_SIZE, page);
        call.enqueue(new retrofit2.Callback<TechNews>() {
            @Override
            public void onResponse(retrofit2.Call<TechNews> call, retrofit2.Response<TechNews> response) {
                List<TechNews.ResultsBean> list = response.body().getResults();
                listener.onSuccess(list);
            }

            @Override
            public void onFailure(retrofit2.Call<TechNews> call, Throwable t) {
                listener.onFailed((Exception) t, "哎呀！网络连接失败啦！！！/(ㄒoㄒ)/~~");

            }
        });


    }

    @Override
    public void onLoadIos(OnLoadListener listener, int page) {


        ApiService service = HttpUtil.getService();
        retrofit2.Call<TechNews> call = service.getIOS(Constants.PAGE_SIZE, page);
        call.enqueue(new retrofit2.Callback<TechNews>() {
            @Override
            public void onResponse(retrofit2.Call<TechNews> call, retrofit2.Response<TechNews> response) {

                List<TechNews.ResultsBean> list = response.body().getResults();
                listener.onSuccess(list);
            }

            @Override
            public void onFailure(retrofit2.Call<TechNews> call, Throwable t) {
                listener.onFailed((Exception) t, "failed");

            }
        });


    }

    @Override
    public void onLoadDaily(OnLoadListener listener, int year, int month, int day) {
        ApiService apiService = HttpUtil.getService();

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(App.getContext());
        String date = preferences.getString("newday", "2017-12-15");
        String[] attrs = date.split("-");
        year = Integer.parseInt(attrs[0]);
        month = Integer.parseInt(attrs[1]);
        day = Integer.parseInt(attrs[2]);

        Call<Daily> call = apiService.getDaily(year, month, day);
        call.enqueue(new Callback<Daily>() {
            @Override
            public void onResponse(Call<Daily> call, @NonNull Response<Daily> response) {
                Daily.ResultsBean result = response.body().getResults();
                List list = new ArrayList();
                TechNews.ResultsBean fuli = result.getFuli().get(0);
                fuli.setItemType(1);
                list.add(fuli);
//                Title title = new Title();
//                title.setItemType(0);
//                title.setTitle("Android");
//                list.add(title);
//                List<Daily.ResultsBean.AndroidBean> androids = result.getAndroid();
//                for (int i = 0; i < androids.size(); i++) {
//                    Daily.ResultsBean.AndroidBean data = androids.get(i);
//                    data.setItemType(2);
//                    list.add(data);
//                }
//                title.setTitle("IOS");
//                list.add(title);

                List<TechNews.ResultsBean> android = result.getAndroid();
                List<TechNews.ResultsBean> ios = result.getiOS();
                List<TechNews.ResultsBean> blind = result.getBlind();
                List<TechNews.ResultsBean> web = result.getWeb();
                List<TechNews.ResultsBean> video = result.getVideo();
                List<TechNews.ResultsBean> app = result.getApp();

                addDataByTitle("Android",android,list);
                addDataByTitle("IOS",ios,list);
                addDataByTitle("前端",web,list);
                addDataByTitle("APP",app,list);
                addDataByTitle("瞎推荐",blind,list);
                addDataByTitle("休息视频",video,list);

                listener.onSuccess(list);
            }

            @Override
            public void onFailure(Call<Daily> call, Throwable t) {
                listener.onFailed((Exception) t, "hehehhehhe");

            }
        });

    }


    private static void addDataByTitle(String title,
                                       List<TechNews.ResultsBean> data, List list){
        if (data != null){
            Title t = new Title();
            t.setTitle(title);
            t.setItemType(MultiItem.TYPE_TITLE);
            list.add(t);
            for (int i = 0; i < data.size(); i++) {
                TechNews.ResultsBean news = data.get(i);
                news.setItemType(MultiItem.TYPE_TWO);
                list.add(news);
            }
        }

    }
}
