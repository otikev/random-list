package com.kokonetworks.myapplication;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kevin on 21/03/19 at 18:36
 */
public class CustomAdapter extends ArrayAdapter<Long> {
    /**
     * Constructor
     *
     * @param context  The current context.
     */
    public CustomAdapter(Context context, List<Long> items ) {
        super(context, R.layout.list_item,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Long item = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.itemText = convertView.findViewById(R.id.textItem);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemText.setText(String.valueOf(item));

        return convertView;
    }

    class ViewHolder{
        TextView itemText;
    }
}
