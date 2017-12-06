package com.example.bao.mvpgank.model;

import com.example.bao.mvpgank.model.entity.Meizi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by BAO on 2017/12/6.
 */

public class MeiziModelImpl implements MeiziModel {
    @Override
    public void onLoadImage(final OnLoadListener listener) {
        String url = "http://gank.io/api/random/data/福利/10";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailed(e,"load failed,,,");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                System.out.println("success get ");
                List<Meizi.ResultsBean> list
                        = new Gson().fromJson(result,Meizi.class).getResults();
                listener.onSuccess(list);

            }
        });
    }
}
