package com.example.bao.mvpgank.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by BAO on 2017/12/6.
 */

public class MeiziAdapter extends BaseQuickAdapter<TechNews.ResultsBean,BaseViewHolder> {

    private Context context;
    private Map<Integer,Integer> mHeightMap;

    public MeiziAdapter(Context context) {
        super(R.layout.item_meizi);
        this.context = context;
        mHeightMap = new HashMap<>();
    }

    @Override
    protected void convert(BaseViewHolder helper, TechNews.ResultsBean item) {

        ImageView imageview = helper.getView(R.id.iv_meizi);

        int position = helper.getLayoutPosition();
        if (mHeightMap.size()< position){
            mHeightMap.put(position,generaHeight());
        }
        ViewGroup.LayoutParams lp = imageview.getLayoutParams();

        Integer height = mHeightMap.get(position);
        if (height == null) {
            height = generaHeight();
            mHeightMap.put(position, height);
        }
        lp.height = height;
        imageview.setLayoutParams(lp);
//        imageview.setOrigalSize(4,5);
//        System.out.println("url "+item.getUrl());
        Picasso.with(context).load(item.getUrl()+"?imageView2/0/w/150")
                .into(imageview);
//        helper.setText(R.id.tv_date,item.getDesc());
    }

    private int generaHeight() {
        return (int) (Math.random() * 200 + 400);
    }

}
