package com.peridot.perfectgift.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peridot.perfectgift.ItemResultsAdapter;
import com.peridot.perfectgift.R;
import com.peridot.perfectgift.SearchFilter;

public class SearchResultsActivity extends AppCompatActivity {
    ListView productsListView;
    SearchFilter filter;

    String[] items;
    String[] description;
    String[] prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        filter = new SearchFilter(getIntent());

        Resources res = getResources();
        productsListView = findViewById(R.id.products_list_view);
        items = res.getStringArray(R.array.items);
        description = res.getStringArray(R.array.descriptions);
        prices = res.getStringArray(R.array.prices);

        ItemResultsAdapter adapter = new ItemResultsAdapter(this, items, description, prices);
        productsListView.setAdapter(adapter);

        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailViewActivity = new Intent(getApplicationContext(), DetailViewActivity.class);
                showDetailViewActivity.putExtra("com.peridot.perfectgift.ITEM_INDEX", position);
                startActivity(showDetailViewActivity);
            }
        });
    }
}
