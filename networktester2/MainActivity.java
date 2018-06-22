package com.harmonyideas.nettester2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    // Declare Tab Variable
    ActionBar.Tab Tab1, Tab2, Tab3, Tab4, Tab5, Tab6;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore selected tab
        int saved = savedInstanceState.getInt("tab", 0);
        if (saved != getSupportActionBar().getSelectedNavigationIndex())
            getSupportActionBar().setSelectedNavigationItem(saved);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getSupportActionBar().getSelectedNavigationIndex());

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(true);

        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(true);

        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set Tab Icon and Titles
        String tab1label = getResources().getString(R.string.tab1label);
        String tab2label = getResources().getString(R.string.tab2label);
        String tab3label = getResources().getString(R.string.tab3label);
        String tab4label = getResources().getString(R.string.tab4label);
        String tab5label = getResources().getString(R.string.tab5label);
        String tab6label = getResources().getString(R.string.tab6label);

        Tab1 = actionBar.newTab().setText(tab1label);
        Tab2 = actionBar.newTab().setText(tab2label);
        Tab3 = actionBar.newTab().setText(tab3label);
        Tab4 = actionBar.newTab().setText(tab4label);
        Tab5 = actionBar.newTab().setText(tab5label);
        Tab6 = actionBar.newTab().setText(tab6label);

        TabListener<ftPingTest> t1 = new TabListener<ftPingTest>(this, tab1label, ftPingTest.class);
        // Set Tab Listeners
        Tab1.setTabListener(t1);
        actionBar.addTab(Tab1);

        TabListener<ftWhoIsTest> t2 = new TabListener<ftWhoIsTest>(this, tab2label, ftWhoIsTest.class);
        Tab2.setTabListener(t2);
        actionBar.addTab(Tab2);

        TabListener<ftDnsTest> t3 = new TabListener<ftDnsTest>(this, tab3label, ftDnsTest.class);
        Tab3.setTabListener(t3);
        actionBar.addTab(Tab3);

        TabListener<ftMtrTest> t4 = new TabListener<ftMtrTest>(this, tab4label, ftMtrTest.class);
        Tab4.setTabListener(t4);
        actionBar.addTab(Tab4);

        TabListener<ftGoogleTest> t5 = new TabListener<ftGoogleTest>(this, tab5label, ftGoogleTest.class);
        Tab5.setTabListener(t5);
        actionBar.addTab(Tab5);

        TabListener<ftSubnetCalcTest> t6 = new TabListener<ftSubnetCalcTest>(this, tab6label, ftSubnetCalcTest.class);
        Tab6.setTabListener(t6);
        actionBar.addTab(Tab6);
        // Add tabs to actionbar
    }
}
