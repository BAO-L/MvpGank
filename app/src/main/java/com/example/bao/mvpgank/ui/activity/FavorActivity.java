package com.example.bao.mvpgank.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.base.BaseActivity;
import com.example.bao.mvpgank.db.Favor;
import com.example.bao.mvpgank.ui.adapter.FavorAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class FavorActivity extends BaseActivity {

    @BindView(R.id.rv_favor)
    RecyclerView rvFavor;

    private FavorAdapter adapter;
    private List<Favor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favor);

        initViews();
    }

    private void initViews() {

        setTitle("我的收藏");
        list = DataSupport.findAll(Favor.class);
        System.out.println("favor size is:"+list.size());
        adapter = new FavorAdapter(getApplicationContext(), list);
        adapter.bindToRecyclerView(rvFavor);
        if (list.size() == 0) {
            View view = LayoutInflater.from(this).inflate(R.layout.empty_layout,null);
            adapter.setEmptyView(view);
        } else {
            rvFavor.setLayoutManager(new LinearLayoutManager(this));
        }
        rvFavor.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favor,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_favor_delete:
                DataSupport.deleteAll(Favor.class);
        }
        return true;
    }
}
