package com.example.bao.mvpgank.http;

import com.example.bao.mvpgank.model.entity.Daily;
import com.example.bao.mvpgank.model.entity.TechNews;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by BAO on 2017/12/7.
 */

public interface ApiService {

    /*
    * 获取干货发布的日期
    * */
    @GET("day/history")
    Call<ResponseBody> getHistory();

    /*
    * 获取妹纸列表
    * */
    @GET("data/福利/{size}/{page}")
    Call<TechNews> getMeizi(@Path("size") int size, @Path("page") int page);

    /*
    获取Android列表
     */
    @GET("data/Android/{size}/{page}")
    Call<TechNews> getAndroid(@Path("size") int size, @Path("page") int page);

    /*
    获取IOS列表
     */
    @GET("data/iOS/{size}/{page}")
    Call<TechNews> getIOS(@Path("size") int size, @Path("page") int page);

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Call<TechNews> getRandomMeizi(@Path("num") int num);

    @GET("day/{year}/{month}/{day}")
    Call<Daily> getDaily(@Path("year") int year,@Path("month") int month,@Path("day") int day);

}
