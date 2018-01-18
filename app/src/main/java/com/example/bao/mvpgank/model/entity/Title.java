package com.example.bao.mvpgank.model.entity;

import java.io.Serializable;

/**
 * Created by BAO on 2017/12/22.
 */

public class Title extends MultiItem implements Serializable{
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
