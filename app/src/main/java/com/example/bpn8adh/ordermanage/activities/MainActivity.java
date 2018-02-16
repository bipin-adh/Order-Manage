package com.example.bpn8adh.ordermanage.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.TabPagerAdapter;
import com.example.bpn8adh.ordermanage.fragments.MainCourseFragment;
import com.example.bpn8adh.ordermanage.fragments.SoupFragment;
import com.example.bpn8adh.ordermanage.fragments.StartersFragment;
import com.example.bpn8adh.ordermanage.fragments.TodaysSpecialFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
    }

    private void initTab() {
        viewPager = findViewById(R.id.viewPagerMain);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabLayoutEvents);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_tabs));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_tabs_one));
        tabLayout.getTabAt(2).setIcon(android.R.drawable.btn_star);
        tabLayout.getTabAt(3).setIcon(android.R.drawable.ic_menu_upload_you_tube);

        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.primary);
        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                super.onTabSelected(tab);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.primary);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                super.onTabSelected(tab);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.gray_color);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // custom tab view
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_view, null);
//        tabOne.setText("Special Offers");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tabs, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_view, null);
//        tabTwo.setText("Veg");
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tabs, 0, 0);
//        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        String tabTitles[] = getResources().getStringArray(R.array.tab_titles);
        adapter.addFragWithTitle(new TodaysSpecialFragment(), tabTitles[0]);
        adapter.addFragWithTitle(new SoupFragment(), tabTitles[1]);
        adapter.addFragWithTitle(new StartersFragment(), tabTitles[2]);
        adapter.addFragWithTitle(new MainCourseFragment(), tabTitles[3]);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

    }
}
