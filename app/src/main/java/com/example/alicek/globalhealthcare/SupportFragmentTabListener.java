package com.example.alicek.globalhealthcare;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
/**
 * Created by alicek on 2015-11-14.
 */


public class SupportFragmentTabListener<T extends Fragment> implements TabListener {
    private Fragment mFragment;
    private final FragmentActivity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    private final int mfragmentContainerId;
    private final Bundle mfragmentArgs;
    int mState = 0;
    private final int INFO_FRAGMENT = 1;
    private final int FIND_FRAGMENT = 2;
    private final int ALARM_FRAGMENT = 3;

    public SupportFragmentTabListener(FragmentActivity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = android.R.id.content;
        mfragmentArgs = new Bundle();
    }

    public SupportFragmentTabListener(int fragmentContainerId, FragmentActivity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = fragmentContainerId;
        mfragmentArgs = new Bundle();
    }

    // This version supports specifying the container to replace with fragment content and fragment args
    // new SupportFragmentTabListener<SomeFragment>(R.id.flContent, this, "first", SomeFragment.class, myFragmentArgs))
    public SupportFragmentTabListener(int fragmentContainerId, FragmentActivity activity,
                                      String tag, Class<T> clz, Bundle args) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mfragmentContainerId = fragmentContainerId;
        mfragmentArgs = args;
    }

	/* The following are each of the ActionBar.TabListener callbacks */

    public void onTabSelected(Tab tab, FragmentTransaction sft) {

        // Check if the fragment is already initialized
        if (mFragment == null) {
            // If not, instantiate and add it to the activity
            mFragment = Fragment.instantiate(mActivity, mClass.getName(), mfragmentArgs);
            sft.add(mfragmentContainerId, mFragment, mTag);
        } else {
            sft.attach(mFragment);
        }

        if(tab.getText().equals("Info")){
            System.out.println("info탭 클릭됨.");
            //((InfoFragment)mFragment).bind();
            mState = INFO_FRAGMENT;
        }

        else if(tab.getText().equals("Find")){
            System.out.println("Find탭 클릭됨.");
            mState = FIND_FRAGMENT;
        }

        else if(tab.getText().equals("Alarm")){
            System.out.println("Alarm탭 클릭됨.");
            mState = ALARM_FRAGMENT;
        }
    }

    public void onTabUnselected(Tab tab, FragmentTransaction sft) {
        if(tab.getText().equals("Info")){
            ((InfoFragment)mFragment).unbind();
            System.out.println("info탭 언클릭됨.");
        }

        else if(tab.getText().equals("Find")){
            System.out.println("Find탭 언클릭됨.");
        }

        else if(tab.getText().equals("Alarm")){
            System.out.println("Alarm탭 언클릭됨.");
        }

        if (mFragment != null) {
            // Detach the fragment, because another one is being attached
            sft.detach(mFragment);
        }
    }

    public void onTabReselected(Tab tab, FragmentTransaction sft) {

        // User selected the already selected tab. Usually do nothing.
    }
}