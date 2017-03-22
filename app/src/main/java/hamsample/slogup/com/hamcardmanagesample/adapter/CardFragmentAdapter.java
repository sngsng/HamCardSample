package hamsample.slogup.com.hamcardmanagesample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by sngjoong on 2017. 3. 22..
 */

public class CardFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public CardFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }
}
