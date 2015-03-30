package com.thepit.ipitslo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.model.BeltEntry;

import java.util.List;

/**
 * Created by DrIce on 3/29/15.
 */
public class BeltPageAdapter extends ArrayAdapter<BeltEntry> {

    private Context mContext;
    private List<BeltEntry> mBeltEntries;

    public BeltPageAdapter(Context context, int resource, List<BeltEntry> beltEntries) {
        super(context, resource, beltEntries);

        mContext = context;
        mBeltEntries = beltEntries;
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.base_list_item, null);
            holder.name = (TextView)convertView.findViewById(R.id.item_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        BeltEntry entry = mBeltEntries.get(position);

        holder.name.setText(entry.getName());

        return convertView;
    }
}
