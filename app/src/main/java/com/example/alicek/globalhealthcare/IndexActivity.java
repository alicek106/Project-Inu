package com.example.alicek.globalhealthcare;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alicek on 2015-11-14.
 */
public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setupTabs();
    }
    private void setupTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("Alarm")
                .setTabListener(new SupportFragmentTabListener<AlarmFragment>(R.id.container, this,
                        "first", AlarmFragment.class));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Find")
                .setTabListener(new SupportFragmentTabListener<FindFragment>(R.id.container, this,
                        "second", FindFragment.class));
        actionBar.addTab(tab2);

        ActionBar.Tab tab3 = actionBar
                .newTab()
                .setText("Info")
                .setTabListener(new SupportFragmentTabListener<InfoFragment>(R.id.container, this,
                        "third", InfoFragment.class));
        actionBar.addTab(tab3);
    }

}