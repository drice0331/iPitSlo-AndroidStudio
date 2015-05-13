package com.thepit.ipitslo.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.adapter.BeltPageAdapter;
import com.thepit.ipitslo.model.BeltEntry;
import com.thepit.ipitslo.model.BlogEntry;
import com.thepit.ipitslo.util.BaseFetchTask;
import com.thepit.ipitslo.util.BaseParser;
import com.thepit.ipitslo.util.CoreConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrIce on 3/29/15.
 */
public class BeltPageFragment extends ListFragment {

    protected ArrayList<BeltEntry> mBeltEntries;
    protected ListView listView;
    protected int position;
    private Callbacks mCallbacks;
    protected BeltPageAdapter beltPageAdapter;

    public interface Callbacks {
        void onBeltEntrySelected(BeltEntry beltEntry);
    }

    public static BeltPageFragment newInstance(int position) {
        BeltPageFragment fragment = new BeltPageFragment();

        Bundle args = new Bundle();
        args.putInt(CoreConstants.BELT_PAGER_POSITION, position); //TODO - maybe put ArrayList into here
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

        setRetainInstance(true);//TODO - look up meaning of this again

        position = getArguments().getInt(CoreConstants.BELT_PAGER_POSITION);

        ////////////////////////////////////////////////////
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
        mBeltEntries = new ArrayList<BeltEntry>();

        new BeltFetchTask().execute(parserKeys, objectKeys);
        ////////////////////////////////////////////////////

        //beltPageAdapter = new BeltPageAdapter(getActivity(), android.R.layout.simple_list_item_1, mBeltEntries);
        //setListAdapter(beltPageAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_belt_page, parent, false);
        listView = (ListView)view.findViewById(android.R.id.list);
        /*
        if(beltPageAdapter != null) {
            listView.setAdapter(beltPageAdapter);
        } else {
            beltPageAdapter = new BeltPageAdapter(getActivity(), android.R.layout.simple_list_item_1, mBeltEntries);
            listView.setAdapter(beltPageAdapter);
        }
        */
        //listView.setAdapter(getListAdapter());//TODO - figure out if this is redundant or not

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("BaseFragment", "page - " + position + " onresume ");
        if(getListAdapter() != null) {
            ((BeltPageAdapter) getListAdapter()).notifyDataSetChanged();
            Log.d("BaseFragment", " notifydata set changed ");
        }
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

    /**
     * updates listadapter with latest itemList by retrieving it from singleton
     */
    public void updateAdapter() {
        if (listView == null) {
            Log.d("TAG", "list view null");
            return;
        }

        //if listtype is all or null, then get entire content list (defaulting to this if null)
        if(mBeltEntries == null) {
            setListAdapter(null);
        } else {
            beltPageAdapter = new BeltPageAdapter(this.getActivity(), R.layout.belt_list_item, mBeltEntries);
            setListAdapter(beltPageAdapter);
            beltPageAdapter.notifyDataSetChanged();
        }
    }

    private class BeltFetchTask extends AsyncTask<Map<String, String>, Void, ArrayList<BeltEntry>> {

        @Override
        protected ArrayList<BeltEntry> doInBackground(Map<String, String>... params) {
                Map<String, String> dataRetrieveKey = params[0];
                Map<String, String> objKey = params[1];
                ArrayList<BeltEntry> list = new BaseParser<BeltEntry>().fetchItems(dataRetrieveKey, objKey);
                return list;
        }

        @Override
        protected void onPostExecute(ArrayList<BeltEntry> itemList) {
            super.onPostExecute(itemList);
            mBeltEntries = itemList;
//            beltPageAdapter = new BeltPageAdapter(getActivity(), android.R.layout.simple_list_item_1, mBeltEntries);
  //          setListAdapter(beltPageAdapter);
            updateAdapter();
        }
    }



}
