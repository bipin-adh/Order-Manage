package com.example.bpn8adh.ordermanage.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.TabPagerAdapter;
import com.example.bpn8adh.ordermanage.fragments.MainCourseFragment;
import com.example.bpn8adh.ordermanage.fragments.SoupFragment;
import com.example.bpn8adh.ordermanage.fragments.StartersFragment;
import com.example.bpn8adh.ordermanage.fragments.TodaysSpecialFragment;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tabLayoutEvents)
    TabLayout tabLayoutEvents;
    @BindView(R.id.viewPagerMain)
    ViewPager viewPager;
    @BindView(R.id.tv_cart_notify)
    TextView textViewCartListItemNum;

    private int numOfItemsInCart;
    private List<FoodDetails> savedCartListToOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTab();
    }

    @OnClick({R.id.cart_toolbar_notification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cart_toolbar_notification:
                CartActivity.launchActivity(MainActivity.this);
                break;
        }
    }

    private void setTypeface(final int position, final int textStyle) {
        final Thread t = new Thread() {
            @Override
            public void run() {
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run() {
                        LinearLayout tabLayout = (LinearLayout) ((ViewGroup) tabLayoutEvents.getChildAt(0)).getChildAt(position);
                        TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                        tabTextView.setTypeface(GeneralUtils.getTypeface(MainActivity.this), textStyle);
                    }
                });
            }
        };
        t.start();
    }

    private void initTab() {
        setupViewPager(viewPager);
        tabLayoutEvents.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayoutEvents.setupWithViewPager(viewPager);

        setTypeface(0, Typeface.BOLD);
        tabLayoutEvents.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTypeface(tab.getPosition(), Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTypeface(tab.getPosition(), Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        String tabTitles[] = getResources().getStringArray(R.array.tab_titles);
        adapter.addFragWithTitle(new TodaysSpecialFragment(), tabTitles[0]);
        adapter.addFragWithTitle(new StartersFragment(), tabTitles[1]);
        adapter.addFragWithTitle(new SoupFragment(), tabTitles[2]);
        adapter.addFragWithTitle(new MainCourseFragment(), tabTitles[3]);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }
}