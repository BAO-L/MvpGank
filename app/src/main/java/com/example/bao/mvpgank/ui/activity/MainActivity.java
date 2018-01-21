package com.example.bao.mvpgank.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.app.Constants;
import com.example.bao.mvpgank.http.ApiService;
import com.example.bao.mvpgank.http.HttpUtil;
import com.example.bao.mvpgank.model.entity.TechNews;
import com.example.bao.mvpgank.ui.adapter.TabPagerAdapter;
import com.example.bao.mvpgank.ui.fragment.AndroidFragment;
import com.example.bao.mvpgank.ui.fragment.HomeFragment;
import com.example.bao.mvpgank.ui.fragment.IosFragment;
import com.example.bao.mvpgank.ui.fragment.MeiziFragment;
import com.example.bao.mvpgank.utils.DayNightHelper;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.senab.photoview.log.LoggerDefault;

public class MainActivity extends AppCompatActivity {
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
    SwitchCompat switchCompat;
    SwitchCompat switchTheme;
    DayNightHelper mDayNightHelper;
    private long exitTime = 0;

    private ImageView randomMeizi;

    private String url;
    private int theme;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        initTheme();
        getRandomMeizi();
        initViews();

        initListeners();


    }

    private void getRandomMeizi() {
        System.out.println("正在获取随机妹子图。。。。。");
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
                case R.id.nav_share:
                    Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "initListeners: change mode");
                    break;
                case R.id.nav_favor:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, FavorActivity.class);
                    new Handler().postDelayed(() -> startActivity(intent), 800);

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
            intent.putExtra("url", url);
            intent.setClass(MainActivity.this, ImgActivity.class);
            startActivity(intent);
            return true;
        });
        switchTheme.setOnCheckedChangeListener((compoundButton, b) -> {
            toggleThemeSetting();
            refreshUI();
        });

    }

    private void initViews() {

        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        switchTheme = (SwitchCompat) findViewById(R.id.switch_theme);

        switchTheme.setChecked(mDayNightHelper.isNight());

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
        theme = prefs.getInt("theme", 0);

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
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 刷新UI界面
     */
    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        TypedValue colorPrimary = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.themeBackground, background, true);
        theme.resolveAttribute(R.attr.themeTextColor, textColor, true);
        theme.resolveAttribute(R.attr.colorPrimary, colorPrimary, true);
        mainToolbar.setBackgroundResource(colorPrimary.resourceId);

        tabLayout.setBackgroundResource(colorPrimary.resourceId);

        navView.setBackgroundResource(background.resourceId);

//        Resources resources = getResources();

//        int childCount = mRecyclerView.getChildCount();
//        for (int childIndex = 0; childIndex < childCount; childIndex++) {
//            ViewGroup childView = (ViewGroup) mRecyclerView.getChildAt(childIndex);
//            childView.setBackgroundResource(background.resourceId);
//            View infoLayout = childView.findViewById(R.id.info_layout);
//            infoLayout.setBackgroundResource(background.resourceId);
//            TextView nickName = childView.findViewById(R.id.tv_nickname);
//            nickName.setBackgroundResource(background.resourceId);
//            nickName.setTextColor(resources.getColor(textColor.resourceId));
//            TextView motto = childView.findViewById(R.id.tv_motto);
//            motto.setBackgroundResource(background.resourceId);
//            motto.setTextColor(resources.getColor(textColor.resourceId));
//        }

        //让 RecyclerView 缓存在 Pool 中的 Item 失效
        //那么，如果是ListView，要怎么做呢？这里的思路是通过反射拿到 AbsListView 类中的 RecycleBin 对象，然后同样再用反射去调用 clear 方法
//        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
//        try {
//            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
//            declaredField.setAccessible(true);
//            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
//            declaredMethod.setAccessible(true);
//            declaredMethod.invoke(declaredField.get(mRecyclerView), new Object[0]);
//            RecyclerView.RecycledViewPool recycledViewPool = mRecyclerView.getRecycledViewPool();
//            recycledViewPool.clear();
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


    }

    private void initTheme() {
        mDayNightHelper = new DayNightHelper(this);
        if (mDayNightHelper.isDay()) {
            Log.d(TAG, "initTheme: DAY");
            setTheme(R.style.DayTheme);
        } else {
            Log.d(TAG, "initTheme: NIGHT");
            setTheme(R.style.NightTheme);
        }
    }

    private void toggleThemeSetting() {
        if (mDayNightHelper.isDay()) {
            mDayNightHelper.setMode(DayNightHelper.DayNight.NIGHT);
            setTheme(R.style.NightTheme);
        } else {
            mDayNightHelper.setMode(DayNightHelper.DayNight.DAY);
            setTheme(R.style.DayTheme);
        }
    }


}
