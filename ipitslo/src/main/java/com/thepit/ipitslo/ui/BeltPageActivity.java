package com.thepit.ipitslo.ui;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.model.BeltEntry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Locale;

import roboguice.util.Ln;

public class BeltPageActivity extends BaseActivity implements BeltPageFragment.Callbacks{

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private String [] beltGroupNames;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belt_page);
        init();
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        /*
        if(savedInstanceState != null) {

        }
        */
        FragmentManager fm = getSupportFragmentManager();
        mPagerAdapter = new BeltStatePagerAdapter(fm);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * Set Title here
             * @param pos
             */
            @Override
            public void onPageSelected(int pos) {
                String [] beltgroups = getResources().getStringArray(R.array.belt_groups);
                setTitle(beltgroups[pos]);
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });
	}

    private void init() {
        beltGroupNames = getResources().getStringArray(R.array.belt_groups);
    }

    @Override
    public void onBeltEntrySelected(BeltEntry beltEntry) {
        Intent intent = new Intent(this, BeltDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Entry", beltEntry);
        Ln.d("name - " + beltEntry.getName() + " rank - " + beltEntry.getColor());
        intent.putExtra("Entry", beltEntry);
        startActivity(intent);
    }


    private class BeltStatePagerAdapter extends FragmentStatePagerAdapter {

        public BeltStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BeltPageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            if(beltGroupNames != null) {
                return beltGroupNames[position].toUpperCase(l);
            }
            return null;
        }
    }
}
