package com.peridot.perfectgift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.peridot.perfectgift.R;

public class ItemResultsAdapter extends BaseAdapter {
    LayoutInflater inflater;
    String[] items;
    String[] description;
    String[] prices;

    public ItemResultsAdapter(Context context, String[] items, String[] description, String[] prices) {
        this.items = items;
        this.description = description;
        this.prices = prices;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.products_listview_detail, null);
        TextView nameTextView = v.findViewById(R.id.name_text_view);
        TextView descriptionTextView = v.findViewById(R.id.description_text_view);
        TextView priceTextView = v.findViewById(R.id.price_text_view);

        nameTextView.setText(items[position]);
        descriptionTextView.setText(description[position]);
        priceTextView.setText(prices[position] + "лв.");

        return v;
    }
}
