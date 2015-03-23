package com.thepit.ipitslo.adapter;

import java.util.List;

import com.thepit.ipitslo.model.BlogEntry;
import com.thepit.ipitslo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BlogAdapter extends ArrayAdapter<BlogEntry> {

	private Context mContext;
	private List<BlogEntry> mBlogEntries;
	
	public BlogAdapter(Context context, int resource, List<BlogEntry> blogEntries) {
		super(context, resource, blogEntries);
		mContext = context;
		mBlogEntries = blogEntries;
	}
	
	public class ViewHolder {
		TextView title;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater infalInflater = (LayoutInflater) this.mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.blog_list_item, null);
			holder.title = (TextView)convertView.findViewById(R.id.blog_item_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		BlogEntry entry = mBlogEntries.get(position);
		
		holder.title.setText(entry.getTitle());
		
		return convertView;
	}

}
