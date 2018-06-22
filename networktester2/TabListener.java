package com.harmonyideas.nettester2;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.AppCompatActivity;


public class TabListener<T extends android.support.v4.app.Fragment> implements ActionBar.TabListener {

    private final AppCompatActivity mActivity;
    private final Class<T> mClass;
    private final String mTag;
    private Fragment fragment;


    public TabListener(AppCompatActivity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mClass = clz;
        mTag = tag;

        fragment = mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
        if (fragment != null && fragment.isDetached()) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            ft.detach(fragment);
            ft.commit();
        }
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

        if (fragment == null) {
            // If not, instantiate and add it to the activity
            fragment = Fragment.instantiate(mActivity, mClass.getName());
            ft.add(R.id.fragment_container, fragment, mTag);
        } else {

            // If it exists, simply attach it in order to show it
            ft.show(fragment);

        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        //ft.remove(fragment);
        //ft.detach(fragment);
        if (fragment != null) {
            // If not, instantiate and add it to the activity
            // Detach the fragment, because another one is being attached
            ft.hide(fragment);
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }
}
