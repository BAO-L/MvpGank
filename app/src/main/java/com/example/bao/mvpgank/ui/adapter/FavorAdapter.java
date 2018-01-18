package com.example.bao.mvpgank.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.db.Favor;
import com.example.bao.mvpgank.model.entity.MultiItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BAO on 2017/12/26.
 */

public class FavorAdapter extends BaseMultiItemQuickAdapter<Favor,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    private Context context;

    public FavorAdapter(Context context,List<Favor> data) {
        super(data);
        this.context = context;
        addItemType(MultiItem.TYPE_ONE, R.layout.item_meizi);
        addItemType(MultiItem.TYPE_TWO, R.layout.item_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, Favor item) {
        switch (helper.getItemViewType()){
            case MultiItem.TYPE_ONE:
                ImageView imageView = helper.getView(R.id.iv_meizi);
                Picasso.with(context).load(item.getUrl()).into(imageView);
                break;
            case MultiItem.TYPE_TWO:
                helper.setText(R.id.tv_title,item.getTitle())
                        .setText(R.id.tv_author,item.getType());
                break;
        }
    }
}
