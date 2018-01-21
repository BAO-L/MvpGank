package com.example.bao.mvpgank.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.model.entity.MultiItem;
import com.example.bao.mvpgank.model.entity.Title;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BAO on 2017/12/10.
 */

public class DailyAdapter extends BaseMultiItemQuickAdapter<MultiItem, BaseViewHolder> {

    private Context context;
    private int theme;

    public DailyAdapter(Context context, List data) {
        super(data);
        this.context = context;
        addItemType(MultiItem.TYPE_TITLE, R.layout.item_title);
        addItemType(MultiItem.TYPE_ONE, R.layout.item_meizi);
        addItemType(MultiItem.TYPE_TWO, R.layout.item_news);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItem item) {

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        theme = prefs.getInt("theme",0);
        switch (helper.getItemViewType()) {


            case MultiItem.TYPE_TITLE:
                setTitle(helper, (Title) item);
                break;
            case MultiItem.TYPE_ONE:
                setImage(helper, (TechNews.ResultsBean) item);
                break;
            case MultiItem.TYPE_TWO:
                setNews(helper, (TechNews.ResultsBean) item);
                break;
        }

    }

    private void setTitle(BaseViewHolder helper, Title tItle) {
        helper.setText(R.id.tv_category, tItle.getTitle());

    }

    private void setImage(BaseViewHolder helper, TechNews.ResultsBean fuli) {
        helper.addOnClickListener(R.id.iv_meizi);
        Picasso.with(context).load(fuli.getUrl()).into((ImageView) helper.getView(R.id.iv_meizi));
    }

    private void setNews(BaseViewHolder helper, TechNews.ResultsBean android) {
//
//        if (theme == ThemeManager.MODE_NIGHT){
//
//            CardView card = helper.getView(R.id.item_news);
//            card.setCardBackgroundColor(context.getResources().getColor(R.color.bg_news));
//            helper.setTextColor(R.id.tv_title,R.color.toolbarColor);
//        }

        helper.setText(R.id.tv_title, android.getDesc())
                .setText(R.id.tv_author, android.getWho())
                .setText(R.id.tv_publish_date, android.getPublishedAt().substring(0, 10))
                .addOnClickListener(R.id.item_news);
    }


}
