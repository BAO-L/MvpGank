package com.example.bao.mvpgank.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.base.contract.MeiziContract;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.example.bao.mvpgank.presenter.MeiziPresenter;
import com.example.bao.mvpgank.ui.activity.ImgActivity;
import com.example.bao.mvpgank.ui.adapter.MeiziAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment implements MeiziContract.View, SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "MeiziFragment";

    @BindView(R.id.rv_meizi)
    RecyclerView rvMeizi;
    @BindView(R.id.refresh_meizi)
    SwipeRefreshLayout refreshMeizi;
    MeiziPresenter presenter;
    private List<TechNews.ResultsBean> data;
    private MeiziAdapter adapter;
    View view ;
    private int page = 1;


    public MeiziFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_meizi, container, false);
            ButterKnife.bind(this, view);
            presenter = new MeiziPresenter(this);
            init();
            initListeners();
            onRefresh();
        }

        return view;
    }

    private void initListeners() {
        adapter.setOnItemClickListener((adapter1, view1, position) -> {

            ActivityOptionsCompat optionsCompat =ActivityOptionsCompat.makeSceneTransitionAnimation
                    (getActivity(), view1,"meizi");
            Intent intent = new Intent();
            intent.putExtra("url",data.get(position).getUrl());
            intent.setClass(getActivity(),ImgActivity.class);
            ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());
        });

        adapter.setOnLoadMoreListener(() -> {
//            page++;
//            Log.d(TAG, "onLoadMoreRequested: current page: "+page);
            Log.d(TAG, "initListeners: getting meizis");
            presenter.getMeiziData();
            adapter.loadMoreEnd();
        }, rvMeizi);
        adapter.disableLoadMoreIfNotFullPage();

    }

    private void init() {

        rvMeizi.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new MeiziAdapter(getActivity().getApplicationContext());

        rvMeizi.getItemAnimator().setChangeDuration(0);

//        adapter.setNotDoAnimationCount(10);
//        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rvMeizi.setAdapter(adapter);
//        adapter.disableLoadMoreIfNotFullPage();
        refreshMeizi.setOnRefreshListener(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void showContent(List<TechNews.ResultsBean> list) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(list);
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
        refreshMeizi.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        refreshMeizi.setRefreshing(false);

    }


    @Override
    public void onRefresh() {
        if(data!=null){
            data.clear();
//            page = 1;
        }
        presenter.getMeiziData();
    }
}
