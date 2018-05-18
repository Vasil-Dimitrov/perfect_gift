package com.peridot.perfectgift.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peridot.perfectgift.Entity.Category;
import com.peridot.perfectgift.Entity.Product;
import com.peridot.perfectgift.adapter.ItemResultsAdapter;
import com.peridot.perfectgift.R;
import com.peridot.perfectgift.SearchFilter;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class SearchResultsActivity extends AppCompatActivity {
    ListView productsListView;
    SearchFilter filter;

    String[] items;
    String[] description;
    String[] prices;

    LinkedList<Product> productSortedList = new LinkedList<>();

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        filter = new SearchFilter(getIntent());

        realm = Realm.getDefaultInstance();
        RealmQuery<Product> productQuery = realm.where(Product.class).equalTo("categories.name", filter.getSelectedCategory());
        if(!filter.getSelectedGender().equals("Без значение")) {
            productQuery.equalTo("genderSpecific", filter.getSelectedGender());
        }
        if(filter.getFromPrice() != null && !filter.getFromPrice().equals(0.0)) {
            productQuery.greaterThanOrEqualTo("price", filter.getFromPrice());
        }
        if(filter.getToPrice() != null && !filter.getToPrice().equals(0.0)) {
            productQuery.lessThanOrEqualTo("price", filter.getToPrice());
        }
        String[] ageArray = filter.getSelectedAge().split("-");
        if(ageArray.length < 2) {
            productQuery.greaterThanOrEqualTo("minAge", 18);
        } else {
            productQuery.greaterThanOrEqualTo("minAge", Integer.parseInt(ageArray[0].trim()));
            productQuery.lessThanOrEqualTo("maxAge", Integer.parseInt(ageArray[1].trim()));
        }


        RealmResults<Product> products = productQuery.findAll();


        productsListView = findViewById(R.id.products_list_view);
        items = new String[products.size()];
        description = new String[products.size()];
        prices = new String[products.size()];
        for(int i=0; i<products.size(); i++) {
            productSortedList.add(products.get(i));
            items[i] = products.get(i).getName();
            description[i] = products.get(i).getShortDescription();
            prices[i] = products.get(i).getPrice().toString();
        }

        ItemResultsAdapter adapter = new ItemResultsAdapter(this, items, description, prices);
        productsListView.setAdapter(adapter);

        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailViewActivity = new Intent(getApplicationContext(), DetailViewActivity.class);
                showDetailViewActivity.putExtra("com.peridot.perfectgift.ITEM_NAME", productSortedList.get(position).getName());
                showDetailViewActivity.putExtra("com.peridot.perfectgift.ITEM_IMAGE", productSortedList.get(position).getImage());
                showDetailViewActivity.putExtra("com.peridot.perfectgift.ITEM_DESC", productSortedList.get(position).getLongDescription());
                showDetailViewActivity.putExtra("com.peridot.perfectgift.ITEM_NUMBER", productSortedList.get(position).getContactNumber());
                showDetailViewActivity.putExtra("com.peridot.perfectgift.ITEM_EMAIL", productSortedList.get(position).getContactEmail());
                showDetailViewActivity.putExtra("com.peridot.perfectgift.ITEM_PRICE", productSortedList.get(position).getPrice());
                startActivity(showDetailViewActivity);
            }
        });
    }
}
