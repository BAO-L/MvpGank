package com.example.bao.mvpgank.http;

import com.example.bao.mvpgank.app.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BAO on 2017/12/11.
 */

public class HttpUtil {
    private static ApiService apiService;
    public static void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GANK_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getService(){
        return apiService;
    }
}
