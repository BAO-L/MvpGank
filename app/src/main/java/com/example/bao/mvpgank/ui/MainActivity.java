package com.example.bao.mvpgank.ui;

import android.app.NotificationManager;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.model.entity.Meizi;
import com.example.bao.mvpgank.presenter.MeiziPresenter;
import com.example.bao.mvpgank.presenter.MeiziPresenterImpl;
import com.example.bao.mvpgank.view.MeiziView;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MeiziView, SwipeRefreshLayout.OnRefreshListener {

    private MeiziAdapter adapter;
    private List<Meizi.ResultsBean> data;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private MeiziPresenter presenter;
    private TestAdapter testadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        presenter = new MeiziPresenterImpl(this);
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        adapter = new MeiziAdapter(getApplicationContext());
//        testadapter = new TestAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        onRefresh();

    }

    @Override
    public void showImages(List<Meizi.ResultsBean> list) {
        if (data == null) {
            data = new ArrayList<>();
        }

        System.out.println("=========list size is " + list.size());
        data.addAll(list);
        adapter.setList(data);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showFailed(Exception e, String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRefresh() {
        if (data != null){
            data.clear();
        }
        presenter.loadImagelList();
    }
}
