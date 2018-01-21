package com.example.bao.mvpgank.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.base.BaseActivity;
import com.example.bao.mvpgank.db.Favor;
import com.example.bao.mvpgank.model.entity.MultiItem;
import com.squareup.picasso.Picasso;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;
import uk.co.senab.photoview.PhotoView;

public class ImgActivity extends BaseActivity {

    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.img_layout)
    FrameLayout imgLayout;
    private Toolbar toolbar;
    private String url;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");

        ViewCompat.setTransitionName(photoView, "meizi");
        Picasso.with(this).load(url).into(photoView);

        toolbar = getToolbar();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        int theme = prefs.getInt("theme", 0);

        System.out.println("theme img :" + theme);


//        setState();


//        setContentView(R.layout.activity_img);

    }

    private void setLikeState() {
        List<Favor> list = DataSupport.where("url = ?",url).find(Favor.class);
        System.out.println("list size is :"+ list.size());
        if(list.size()!=0) {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
            menuItem.setChecked(true);
        } else {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            menuItem.setChecked(false);

        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_img;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_img,menu);
        menuItem = menu.findItem(R.id.menu_img_collect);
        setLikeState();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_img_share:
                showShare();
                break;
            case R.id.menu_img_collect:
                if (menuItem.isChecked()){
                    menuItem.setChecked(false);
                    menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
                    DataSupport.deleteAll(Favor.class,"url = ?",url);
                }else {
                    menuItem.setChecked(true);
                    menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
                    System.out.println("adding to favor");
                    Favor favor = new Favor();
                    favor.setUrl(url);
                    favor.setType("福利");
                    favor.setItemtype(MultiItem.TYPE_ONE);
                    favor.save();

                }

                break;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
