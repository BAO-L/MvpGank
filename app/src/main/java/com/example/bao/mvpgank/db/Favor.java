package com.example.bao.mvpgank.db;

import android.support.v7.view.menu.MenuView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.bao.mvpgank.model.entity.MultiItem;

import org.litepal.crud.DataSupport;

/**
 * Created by BAO on 2017/12/26.
 */

public class Favor extends DataSupport implements MultiItemEntity {
    private int id;
    private String title;
    private String url;
    private String type;
    private int itemtype;
    public int getItemtype() {
        return itemtype;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }



    public Favor() {
    }

    public Favor(String title, String url, String type) {
        this.title = title;
        this.url = url;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return itemtype;
    }
}
