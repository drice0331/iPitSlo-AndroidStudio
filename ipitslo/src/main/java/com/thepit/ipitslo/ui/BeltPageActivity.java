package com.thepit.ipitslo.ui;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.model.BeltEntry;
import com.thepit.ipitslo.util.BaseFetchTask;
import com.thepit.ipitslo.util.CoreConstants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import roboguice.inject.InjectView;
import roboguice.util.Ln;

public class BeltPageActivity extends BaseActivity implements BaseFetchTask.FetchTaskListener<BeltEntry>, BeltPageFragment.Callbacks{

    private ViewPager mViewPager;

    private PagerAdapter mPagerAdapter;

    private Map<String, ArrayList<BeltEntry>> beltGroupMap;
    private ArrayList<BeltEntry> beltEntries;

    private Map<String, String> parserKeys;
    private Map<String, String> objectKeys;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belt_page);
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        beltGroupMap = new HashMap<String, ArrayList<BeltEntry>>();
        beltEntries = new ArrayList<BeltEntry>();
        if(savedInstanceState != null) {

        }

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

    @Override
    public void onBeltEntrySelected(BeltEntry beltEntry) {

    }

    @Override
    public ArrayList<BeltEntry> getBeltEntriesForPosition(int position) {

        String key;
        String url;

        String [] urls = getResources().getStringArray(R.array.belt_urls);
        url = urls[position];
        String [] beltgroups = getResources().getStringArray(R.array.belt_groups);
        key = beltgroups[position];
        Ln.d("making sure url is correct " + url);
        //If belt map doesn't contain list for given key, then retrieve it with FetchTask
        if(!beltGroupMap.containsKey(key)) {
            parserKeys = new HashMap<String, String>();
            parserKeys.put(CoreConstants.DATA_URL, url);
            parserKeys.put(CoreConstants.CLASS_NAME, CoreConstants.BELT_ENTRY_CLASS_NAME);
            parserKeys.put(CoreConstants.PARSER_KEY, CoreConstants.PARSER_ENTRY_NAME);

            objectKeys = new HashMap<String, String>();
            objectKeys.put("field1", CoreConstants.STUDENT_NAME);
            objectKeys.put("field2", CoreConstants.STUDENT_PROGRESS);
            objectKeys.put("field3", CoreConstants.STUDENT_BELT_COLOR);
            objectKeys.put("field4", CoreConstants.STUDENT_INFO_LINK);

            new BaseFetchTask<BeltEntry>(this).execute(parserKeys, objectKeys);

            //Check to see if beltEntries set before putting into beltgroupmap

            Ln.d("Belt Entries put into group map");
            beltGroupMap.put(key, beltEntries);
        }

        return beltGroupMap.get(key);
    }

    @Override
    public void onPostExecute(ArrayList<BeltEntry> itemList) {
        beltEntries = itemList;
        mPagerAdapter.notifyDataSetChanged();
        Ln.d("Belt Entries Post Execute");
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
    }
}
