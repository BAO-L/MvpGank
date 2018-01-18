package com.example.bao.mvpgank.ui.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.base.contract.NewsContract;
import com.example.bao.mvpgank.http.ApiService;
import com.example.bao.mvpgank.http.HttpUtil;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.example.bao.mvpgank.presenter.DailyPresenter;
import com.example.bao.mvpgank.ui.activity.ImgActivity;
import com.example.bao.mvpgank.ui.activity.WebActivity;
import com.example.bao.mvpgank.ui.adapter.DailyAdapter;
import com.example.bao.mvpgank.utils.CacheUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.rv_daily)
    RecyclerView rvDaily;
    @BindView(R.id.refresh_daily)
    SwipeRefreshLayout refreshDaily;
    @BindView(R.id.home_layout)
    FrameLayout homeLayout;
    private View view;
    private DailyAdapter adapter;
    private List data;
    private DailyPresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.bind(this, view);
            initViews();
            initListeners();
        }

        return view;
    }

    private void initViews() {

        presenter = new DailyPresenter(this);
        rvDaily.setLayoutManager(new LinearLayoutManager(getActivity()));
        data = CacheUtil.readObject("home");
        adapter = new DailyAdapter(getActivity(), data);
        rvDaily.setAdapter(adapter);
        refreshDaily.setOnRefreshListener(this);



        onRefresh();

    }

    private void initListeners() {
        adapter.setOnItemChildClickListener((adapter1, view1, position) -> {
            Intent intent = new Intent();
            TechNews.ResultsBean news = (TechNews.ResultsBean) data.get(position);
            intent.putExtra("url",news.getUrl());
            intent.putExtra("title",news.getDesc());
            switch (view1.getId()){
                case R.id.item_news:
                    intent.setClass(getActivity(), WebActivity.class);
                    break;
                case R.id.iv_meizi:
                    intent.setClass(getActivity(), ImgActivity.class);
                    break;
            }
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showContent(List<?> list) {
        if (data == null) {
            data = new ArrayList<>() ;
        }
        System.out.println("list size :"+list.size());
        data.addAll(list);
        CacheUtil.saveObject(data,"home");
        adapter.setNewData(data);

    }

    @Override
    public void showFailed(String s) {
        Snackbar.make(homeLayout,s,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {
        refreshDaily.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        refreshDaily.setRefreshing(false);
    }

    @Override
    public void onRefresh() {

        ApiService apiService = HttpUtil.getService();
        Call<ResponseBody> call = apiService.getHistory();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject object = new JSONObject(response.body().string());
                    JSONArray array = object.getJSONArray("results");
                    String date = (String) array.get(0);
                    System.out.println("newday "+date);
                    SharedPreferences preferences = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("newday",date);
                    editor.apply();
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

//                String[]  attrs = date.split("-");
//                year = Integer.parseInt(attrs[0]);
//                month = Integer.parseInt(attrs[1]);
//                day = Integer.parseInt(attrs[2]);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        if (data != null)
            data.clear();
        presenter.getNewsData(0);
    }
}
