package com.xiey94.topbar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xiey94.topbar.fragment.AssetsFragment;
import com.xiey94.topbar.fragment.HomeFragment;
import com.xiey94.topbar.fragment.MineFragment;
import com.xiey94.topbar.fragment.PayFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFrame;
    private BottomNavigationBar bottom_nevigation_bar;
    private List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSystemBar();

        initView();
        initListener();
        initData();

    }

    //判断android 版本然后设置Systembar颜色
    public void initSystemBar() {

        Window window = getWindow();
        //4.4版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //5.0版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initView() {
        mFrame = (FrameLayout) findViewById(R.id.mFrame);
        bottom_nevigation_bar = (BottomNavigationBar) findViewById(R.id.bottom_nevigation_bar);
        bottom_nevigation_bar.setMode(BottomNavigationBar.MODE_FIXED);
        bottom_nevigation_bar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Home"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Assets"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Pay"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Mine"))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void initListener() {
        bottom_nevigation_bar.setTabSelectedListener(onTabSelectedListener);
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(AssetsFragment.newInstance("Assets"));
        fragments.add(PayFragment.newInstance("Pay"));
        fragments.add(MineFragment.newInstance("Pay"));

        setDefaultFragment();
    }

    private BottomNavigationBar.OnTabSelectedListener onTabSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            if (fragments != null) {
                if (position < fragments.size()) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    //当前的Fragment
                    Fragment from = fm.findFragmentById(R.id.mFrame);
                    //点击即将跳转的fragment
                    Fragment fragment = fragments.get(position);
                    if (fragment.isAdded()) {
                        ft.hide(from).show(fragment);
                    } else {
                        //隐藏当前的fragment，add下一个到Activity中
                        ft.hide(from).add(R.id.mFrame, fragment);
                        if (fragment.isHidden()) {
                            ft.show(fragment);
                        }
                    }
                    ft.commitAllowingStateLoss();
                }
            }
        }

        @Override
        public void onTabUnselected(int position) {
            if (fragments != null) {
                if (position < fragments.size()) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment fragment = fragments.get(position);
                    //隐藏当前的fragment
                    ft.hide(fragment);
                    ft.commitAllowingStateLoss();
                }
            }
        }

        @Override
        public void onTabReselected(int position) {

        }
    };

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mFrame, fragments.get(0));
        ft.commit();
    }
}
