package com.example.bao.mvpgank.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.base.contract.NewsContract;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.example.bao.mvpgank.presenter.AndroidPresenter;
import com.example.bao.mvpgank.ui.activity.WebActivity;
import com.example.bao.mvpgank.ui.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends Fragment implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_android)
    RecyclerView rvAndroid;
    @BindView(R.id.refresh_android)
    SwipeRefreshLayout refreshAndroid;
    private AndroidPresenter presenter;
    private List<TechNews.ResultsBean> data;
    private NewsAdapter adapter;
    private View view;
    private int page = 1;

    public AndroidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_android, container, false);
            ButterKnife.bind(this, view);
            presenter = new AndroidPresenter(this);
            init();
            initListeners();
            onRefresh();
        }

        return view;
    }

    private void initListeners() {
        adapter.setOnItemClickListener((adapter1, view1, position) -> {
            Intent intent = new Intent();
            intent.putExtra("url",data.get(position).getUrl());
            intent.putExtra("title",data.get(position).getDesc());
            intent.setClass(getActivity(), WebActivity.class);
            startActivity(intent);
        });
        adapter.setOnLoadMoreListener(() -> {
            page++;
            presenter.getNewsData(page);
            adapter.loadMoreEnd();

        }, rvAndroid);
        adapter.disableLoadMoreIfNotFullPage();

    }

    private void init() {
        rvAndroid.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewsAdapter(getActivity());
//        adapter.setNotDoAnimationCount(10);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);

        rvAndroid.setAdapter(adapter);
        refreshAndroid.setOnRefreshListener(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void showContent(List<?> list) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll((Collection<? extends TechNews.ResultsBean>) list);

        adapter.setNewData(data);
//        adapter.setList(data);
//        adapter.notifyDataSetChanged();


    }

    @Override
    public void showFailed(String s) {
        Toast.makeText(getActivity(), s + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {
        refreshAndroid.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        refreshAndroid.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        if (data != null){
            data.clear();
            page = 1;
        }
        presenter.getNewsData(page);

    }
}
