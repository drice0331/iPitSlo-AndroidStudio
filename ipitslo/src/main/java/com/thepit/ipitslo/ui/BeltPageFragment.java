package com.thepit.ipitslo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.adapter.BeltPageAdapter;
import com.thepit.ipitslo.model.BeltEntry;
import com.thepit.ipitslo.model.BlogEntry;
import com.thepit.ipitslo.util.BaseFetchTask;
import com.thepit.ipitslo.util.CoreConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrIce on 3/29/15.
 */
public class BeltPageFragment extends ListFragment implements BaseFetchTask.FetchTaskListener<BeltEntry>{

    protected ArrayList<BeltEntry> mBeltEntries;

    protected int position;
    private Callbacks mCallbacks;

    protected BeltPageAdapter beltPageAdapter;

    public interface Callbacks {
        void onBeltEntrySelected(BeltEntry beltEntry);
        ArrayList<BeltEntry> getBeltEntriesForPosition(int position);
    }

    public static BeltPageFragment newInstance(int position) {
        Bundle args = new Bundle();

        args.putInt(CoreConstants.BELT_PAGER_POSITION, position); //TODO - maybe put ArrayList into here
        BeltPageFragment fragment = new BeltPageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(CoreConstants.BELT_PAGER_POSITION);

        String [] urls = getResources().getStringArray(R.array.belt_urls);
        String url = urls[position];
        String [] beltgroups = getResources().getStringArray(R.array.belt_groups);
        String key = beltgroups[position];

        mBeltEntries = new ArrayList<BeltEntry>();

        Map<String, String> parserKeys;
        Map<String, String> objectKeys;

        parserKeys = new HashMap<String, String>();
        parserKeys.put(CoreConstants.DATA_URL, url);
        parserKeys.put(CoreConstants.CLASS_NAME, CoreConstants.BELT_ENTRY_CLASS_NAME);
        parserKeys.put(CoreConstants.PARSER_KEY, CoreConstants.PARSER_ENTRY_NAME);

        objectKeys = new HashMap<String, String>();
        objectKeys.put("field1", CoreConstants.STUDENT_NAME);
        objectKeys.put("field2", CoreConstants.STUDENT_PROGRESS);
        objectKeys.put("field3", CoreConstants.STUDENT_BELT_COLOR);
        objectKeys.put("field4", CoreConstants.STUDENT_INFO_LINK);

        new BaseFetchTask<BeltEntry>(getActivity().getApplicationContext()).execute(parserKeys, objectKeys);

        mBeltEntries = mCallbacks.getBeltEntriesForPosition(position);
        beltPageAdapter = new BeltPageAdapter(getActivity(), android.R.layout.simple_list_item_1, mBeltEntries);
        setListAdapter(beltPageAdapter);

        setRetainInstance(true);//TODO - look up meaning of this again
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_belt_page, parent, false);
        ListView listView = (ListView)view.findViewById(android.R.id.list);
        if(beltPageAdapter != null) {
            listView.setAdapter(beltPageAdapter);
        } else {
            beltPageAdapter = new BeltPageAdapter(getActivity(), android.R.layout.simple_list_item_1, mBeltEntries);
            listView.setAdapter(beltPageAdapter);
        }
        //listView.setAdapter(getListAdapter());//TODO - figure out if this is redundant or not

        return view;
    }

    @Override
    public void onListItemClick(ListView list, View view, int position, long id) {
        BeltEntry beltEntry = ((BeltPageAdapter)getListAdapter()).getItem(position);

        //Intent intent = new Intent(this.getActivity(), BeltDetailActivity.class);
        //intent.putExtra(CoreConstants.BELT_DETAIL_ENTRY, crime.getId());
        //startActivity(intent)
        //mCallbacks.onCrimeSelected(crime);
        mCallbacks.onBeltEntrySelected(beltEntry);
    }

    @Override
    public void onPostExecute(ArrayList<BeltEntry>itemList) {
        mBeltEntries = itemList;
        beltPageAdapter = new BeltPageAdapter(getActivity(), android.R.layout.simple_list_item_1, mBeltEntries);

    }

}
