package com.example.bao.mvpgank.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.model.entity.Meizi;
import com.example.bao.mvpgank.widget.RatioImageview;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BAO on 2017/12/6.
 */

public class MeiziAdapter extends BaseQuickAdapter<Meizi.ResultsBean,BaseViewHolder> {
//    private List<Meizi.ResultsBean> data;

    private Context context;
    public void setList(List<Meizi.ResultsBean> data){

        super.mData = data;
//        this.notifyDataSetChanged();
        System.out.println("data size is "+data.size());
    }


    public MeiziAdapter(Context context) {
        super(R.layout.item_meizi);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Meizi.ResultsBean item) {

        RatioImageview imageview = helper.getView(R.id.iv_meizi);
        imageview.setOrigalSize(4,5);
        System.out.println("url "+item.getUrl());
        Picasso.with(context).load(item.getUrl()).into(imageview);
        helper.setText(R.id.tv_date,item.getUrl());


    }
}
