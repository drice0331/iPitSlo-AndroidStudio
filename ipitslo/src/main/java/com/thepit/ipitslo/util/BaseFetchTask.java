package com.thepit.ipitslo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;

public class BaseFetchTask<T extends Object> extends AsyncTask<Map<String, String>, Void, ArrayList<T>> {

	FetchTaskListener<T>listener;
	Context context;
	
	public BaseFetchTask(Context listener) {
		this.context = listener;
		this.listener = (FetchTaskListener<T>) listener;
	}
	
	@Override
	protected ArrayList<T> doInBackground(Map<String,String>... params) {
		Map<String, String> dataRetrieveKey = params[0];
		Map<String, String> objKey = params[1];
		ArrayList<T> list = new BaseParser<T>().fetchItems(dataRetrieveKey, objKey);
		return list;
	}
	
	@Override
    protected void onPostExecute(ArrayList<T> itemList) {
		listener.onPostExecute(itemList);
		super.onPostExecute(itemList);
    }
	
	public interface FetchTaskListener<T extends Object> {
		void onPostExecute(ArrayList<T> itemList);
	}


}
