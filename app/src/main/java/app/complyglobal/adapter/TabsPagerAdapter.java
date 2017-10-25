package app.complyglobal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import app.complyglobal.fragment.DashboardFragment;

/**
 * Created by SUDARSHAN REDDY on 11-10-2017.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("TabAdapter","getItem called");
        switch (position) {
            case 0:
                return new DashboardFragment();
            case 1:
                return new DashboardFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.i("TabAdapter","getPageTitle called");
        switch (position) {
            case 0:
                return "Entity";
            case 1:
                return "Group";
        }
        return null;
    }
}
