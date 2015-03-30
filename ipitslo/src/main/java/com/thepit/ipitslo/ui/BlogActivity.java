package com.thepit.ipitslo.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.adapter.BlogAdapter;
import com.thepit.ipitslo.model.BlogEntry;
import com.thepit.ipitslo.util.BaseFetchTask;
import com.thepit.ipitslo.util.BaseFetchTask.FetchTaskListener;
import com.thepit.ipitslo.util.CoreConstants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BlogActivity extends BaseActivity implements FetchTaskListener<BlogEntry>{
	
	private ListView blogListView;
	private BlogAdapter adapter;
	private ArrayList<BlogEntry> mBlogEntries;
	private Map<String, String> parserKeys;
	private Map<String, String> objectKeys;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blog);
		blogListView = (ListView)findViewById(R.id.blog_list_view);

        mBlogEntries = new ArrayList<BlogEntry>();

		parserKeys = new HashMap<String, String>();
		parserKeys.put(CoreConstants.DATA_URL, CoreConstants.BLOG_URL);
		parserKeys.put(CoreConstants.CLASS_NAME, CoreConstants.BLOG_ENTRY_CLASS_NAME);
        parserKeys.put(CoreConstants.PARSER_KEY, CoreConstants.PARSER_ITEM_NAME);

		objectKeys = new HashMap<String, String>();
		objectKeys.put("field1", "title");
		objectKeys.put("field2", "link");
		
		new BaseFetchTask<BlogEntry>(this).execute(parserKeys, objectKeys);

		adapter = new BlogAdapter(this, android.R.layout.simple_list_item_1, mBlogEntries);
		blogListView.setAdapter(adapter);
		blogListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
		        Intent intent = new Intent(BlogActivity.this, BlogDetailActivity.class);
		        intent.putExtra("url", mBlogEntries.get(position).getLink());
		        startActivity(intent);				
			}
		});
	}

	@Override
	public void onPostExecute(ArrayList<BlogEntry>itemList) {
		mBlogEntries = itemList;
        adapter = new BlogAdapter(this, android.R.layout.simple_list_item_1, mBlogEntries);
        blogListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
	}
	}
