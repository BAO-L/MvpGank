package com.example.bao.mvpgank.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bao.mvpgank.R;

import com.example.bao.mvpgank.model.entity.TechNews;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BAO on 2017/12/10.
 */

public class NewsAdapter extends BaseQuickAdapter<TechNews.ResultsBean, BaseViewHolder> {


    private Context context;

    public NewsAdapter(Context context) {
        super(R.layout.item_news);
        this.context = context;
    }

    public void setList(List<TechNews.ResultsBean> data) {

        super.mData = data;


    }


    @Override
    protected void convert(BaseViewHolder helper, TechNews.ResultsBean item) {
        helper.setText(R.id.tv_title, item.getDesc())
                .setText(R.id.tv_author, item.getWho())
                .setText(R.id.tv_publish_date,
                        TextUtils.substring(item.getPublishedAt(), 0, 10));
        if (item.getImages() != null) {
            ImageView imageView = helper.getView(R.id.iv_thumb);
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(context).load(item.getImages().get(0)+"?imageView2/0/w/100").into(imageView);
        }

    }
}
