package com.example.bao.mvpgank.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.app.Constants;
import com.example.bao.mvpgank.db.Favor;
import com.example.bao.mvpgank.http.ApiService;
import com.example.bao.mvpgank.http.HttpUtil;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.example.bao.mvpgank.ui.adapter.TabPagerAdapter;
import com.example.bao.mvpgank.ui.fragment.AndroidFragment;
import com.example.bao.mvpgank.ui.fragment.HomeFragment;
import com.example.bao.mvpgank.ui.fragment.IosFragment;
import com.example.bao.mvpgank.ui.fragment.MeiziFragment;
import com.example.bao.mvpgank.utils.ThemeManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ThemeManager.OnThemeChangeListener {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    List<Fragment> fragments;
    List<String> titles;
    TabPagerAdapter adapter;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.main_layout)
    CoordinatorLayout mainLayout;

    private long exitTime = 0;

    private ImageView randomMeizi;

    private String url;
    private int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ThemeManager.registerThemeChangeListener(this);
        getRandomMeizi();
        initViews();
        initListeners();


    }

    private void getRandomMeizi() {
        System.out.println("正在获取随机妹子图。。。。。");
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.GANK_RANDOM)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiService service = retrofit.create(ApiService.class);
        ApiService service = HttpUtil.getService();
        Call<TechNews> call = service.getRandomMeizi(1);
        call.enqueue(new Callback<TechNews>() {
            @Override
            public void onResponse(@NonNull Call<TechNews> call, @NonNull Response<TechNews> response) {
                System.out.println("获取成功。。。");
                TechNews.ResultsBean result = response.body().getResults().get(0);
                String meiziUrl = result.getUrl();
                System.out.println("------url :" + meiziUrl);
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext())
                        .edit();
                editor.putString("meizi", meiziUrl);
                editor.apply();
            }

            @Override
            public void onFailure(@NonNull Call<TechNews> call, Throwable t) {
//                Snackbar.make(drawerLayout, "哎呀！联网失败啦/(ㄒoㄒ)/~~", Snackbar.LENGTH_SHORT)
//                        .show();

            }
        });

    }

    private void initListeners() {

        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_mode:
                    int theme = ThemeManager.getThemeMode();
                    ThemeManager.setThemeMode(theme == ThemeManager.MODE_DAY
                            ? ThemeManager.MODE_NIGHT : ThemeManager.MODE_DAY);
                    break;
                case R.id.nav_favor:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,FavorActivity.class);
                    new Handler().postDelayed(()->startActivity(intent),800);

                default:
//                    drawerLayout.closeDrawers();
                    break;
            }
            drawerLayout.closeDrawers();

            return true;
        });
        mainToolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(Gravity.START));

        randomMeizi.setOnLongClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("url",url);
            intent.setClass(MainActivity.this,ImgActivity.class);
            startActivity(intent);
            return true;
        });
    }

    private void initViews() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        View headerLayout = navView.inflateHeaderView(R.layout.nav_header);
        randomMeizi = (ImageView) headerLayout.findViewById(R.id.iv_random);
        fragments.add(new HomeFragment());
        fragments.add(new MeiziFragment());
        fragments.add(new AndroidFragment());
        fragments.add(new IosFragment());

        titles.add("推荐");
        titles.add("妹纸");
        titles.add("Android");
        titles.add("IOS");
        adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments, titles);
        vpMain.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpMain);




        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        url = prefs.getString("meizi", Constants.DEFAULT_IMG);
        theme = prefs.getInt("theme",0);
        ThemeManager.setThemeMode(theme);
        System.out.println("meizi url is;" + url);
        Picasso.with(this).load(url).into(randomMeizi);





    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawers();
            } else {
                exit();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出APP判断，是否两次BACK的时间在2秒内
    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Snackbar.make(drawerLayout, "再按一次退出O(∩_∩)O~", Snackbar.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


    @Override
    public void onThemeChanged() {


        if (ThemeManager.getThemeMode() == ThemeManager.MODE_NIGHT) {

            System.out.println("Night Mode");
            int[][] states = new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            };
            int[] colors = new int[]{getResources().getColor(R.color.white),
                    getResources().getColor(R.color.gray)
            };
            ColorStateList csl = new ColorStateList(states, colors);
            navView.setItemTextColor(csl);


        }

        int toolbarColor = getResources()
                .getColor(ThemeManager
                        .getCurrentThemeRes(MainActivity.this, R.color.toolbarColor));
        mainToolbar.setBackgroundColor(toolbarColor);
        tabLayout.setBackgroundColor(toolbarColor);
        int bgColor = ThemeManager
                .getCurrentThemeRes(MainActivity.this, R.color.background);
        navView.setBackgroundColor(getResources().getColor(bgColor));

        mainLayout.setBackgroundColor(getResources().getColor(bgColor));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterThemeChangeListener(this);
    }
}
