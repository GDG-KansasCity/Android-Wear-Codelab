package com.jaywhitsitt.wear;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class WearActivity extends Activity {

    private GridViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);
        mPager = (GridViewPager) findViewById(R.id.gridPager);
        mPager.setAdapter(new MyGridPagerAdapter(this, getFragmentManager()));
    }
}
