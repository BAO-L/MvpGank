package com.example.bao.mvpgank.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.squareup.picasso.Picasso;


/**
 * Created by BAO on 2017/12/6.
 */

public class MeiziAdapter extends BaseQuickAdapter<TechNews.ResultsBean,BaseViewHolder> {

    private Context context;

    public MeiziAdapter(Context context) {
        super(R.layout.item_meizi);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TechNews.ResultsBean item) {

        ImageView imageview = helper.getView(R.id.iv_meizi);
//        imageview.setOrigalSize(4,5);
//        System.out.println("url "+item.getUrl());
        Picasso.with(context).load(item.getUrl()).centerCrop().resize(200,250)
                .into(imageview);
        helper.setText(R.id.tv_date,item.getDesc());
    }

}
