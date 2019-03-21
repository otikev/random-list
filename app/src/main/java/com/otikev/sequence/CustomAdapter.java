package com.otikev.sequence;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kokonetworks.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kevin on 21/03/19 at 18:36
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<Long> mDataset;

    public CustomAdapter(List<Long> mDataset) {
        this.mDataset=mDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Long item = mDataset.get(position);
        holder.itemText.setText(String.valueOf(item));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setDataset(List<Long> mDataset) {
        this.mDataset = mDataset;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;

        public ViewHolder(@NonNull TextView itemView) {
            super(itemView);
            itemText = itemView;
        }
    }
}
