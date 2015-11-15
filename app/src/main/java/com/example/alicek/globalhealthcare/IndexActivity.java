package com.example.alicek.globalhealthcare;



import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by alicek on 2015-11-14.
 */
public class IndexActivity extends AppCompatActivity {

    int request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Intent intent = getIntent();
        request = intent.getIntExtra("request", 1);
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

        switch(request){
            case 100:{
                actionBar.selectTab(tab1);
                break;
            }
            case 101:{
                actionBar.selectTab(tab2);
                break;
            }
            case 102:{
                actionBar.selectTab(tab3);
                break;
            }
        }
    }

    public void OnBluetoothEnable(View v){
        InfoFragment.OnBluetoothEnable(v);
        while(true){
            if(InfoFragment.isBluetoothEnabled()){
                ActionBar actionBar = getSupportActionBar();
                ActionBar.Tab tab = actionBar.getTabAt(1);
                actionBar.selectTab(tab);

                tab = actionBar.getTabAt(2);
                actionBar.selectTab(tab);
                break;
            }
        }
    }

}