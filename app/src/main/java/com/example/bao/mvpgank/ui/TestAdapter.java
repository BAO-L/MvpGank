package com.example.bao.mvpgank.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.model.entity.Meizi;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BAO on 2017/12/6.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyHolder> {

    private List<Meizi.ResultsBean> data;
    private Context context;

    public void setList(List<Meizi.ResultsBean> data){
        this.data = data;
//        this.notifyDataSetChanged();
        System.out.println("data size is "+data.size());
    }

    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewTypeview) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi,parent,false);
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getUrl()+"?imageView2/0/w/200").into(holder.imageView);
        holder.textView.setText(data.get(position).getDesc());



    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_meizi);
            textView = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
