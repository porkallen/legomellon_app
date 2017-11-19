package com.app.hp_app.uesless;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by Nanica on 10/29/17.
 */

class SectionsPagerAdapter extends PagerAdapter {
    public SectionsPagerAdapter(FragmentManager supportFragmentManager) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
