package com.example.alicek.globalhealthcare;

import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alicek on 2015-11-14.
 */
public class AlarmFragment extends Fragment {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private ListView lvNavList;
    private String[] navItems = {"배변알람", "위험알람", "이상알람"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        View view = inflater.inflate(R.layout.fragment_alarm, container,false);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerFragment);
        drawerView = (View) view.findViewById(R.id.lv_activity_main);

        Button buttonOpenDrawer = (Button) view.findViewById(R.id.test2);
        buttonOpenDrawer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        lvNavList = (ListView)view.findViewById(R.id.lv_activity_main);

        lvNavList.setAdapter(
                new ArrayAdapter<String>(getContext(), R.layout.custon_testview, navItems));

        return view;
    }

}
