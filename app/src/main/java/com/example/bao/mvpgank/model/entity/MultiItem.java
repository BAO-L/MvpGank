package com.example.bao.mvpgank.model.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by BAO on 2017/12/18.
 */

public class MultiItem implements MultiItemEntity {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;

    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
