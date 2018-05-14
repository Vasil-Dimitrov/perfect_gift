package com.peridot.perfectgift.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.peridot.perfectgift.R;
import com.peridot.perfectgift.SearchFilter;

public class MainActivity extends AppCompatActivity {
    RadioGroup genderRg;
    RadioGroup ageRG;

    Spinner categorySpinner;

    Button searchButton;

    EditText fromPrice;
    EditText toPrice;

    SearchFilter filter = new SearchFilter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadGlobalVariables();
    }

    /**
     * Loads all the global variables at the startup of the activity,
     * so that they can be used to extract the selected values for the search filter afterwards.
     */
    private void loadGlobalVariables() {
        ageRG = findViewById(R.id.age_restriction_radio_group);
        genderRg = findViewById(R.id.gender_restriction_radio_group);

        categorySpinner = findViewById(R.id.category_spinner);
        categorySpinner.setAdapter(getCategorySpinnerAdapter());
        categorySpinner.setOnItemSelectedListener(getCategorySpinnerListener());

        searchButton = findViewById(R.id.search_btn);
        searchButton.setOnClickListener(getSearchButtonListener());

        fromPrice = findViewById(R.id.from_price_edit_text);
        toPrice = findViewById(R.id.to_price_edit_text);
    }


    private View.OnClickListener getSearchButtonListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                filter.setFromPrice(fromPrice.getText().toString());
                filter.setToPrice(toPrice.getText().toString());

                String errors = filter.validate();
                TextView errorMessages = findViewById(R.id.error_messages);
                errorMessages.setText(errors);
                if (errors.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);

                    intent.putExtra("com.peridot.perfectgift.selectedGender", filter.getSelectedGender());
                    intent.putExtra("com.peridot.perfectgift.selectedAge", filter.getSelectedAge());
                    intent.putExtra("com.peridot.perfectgift.selectedCategory", filter.getSelectedCategory());
                    intent.putExtra("com.peridot.perfectgift.fromPrice", filter.getFromPrice());
                    intent.putExtra("com.peridot.perfectgift.toPrice", filter.getToPrice());

                    startActivity(intent);
                }
            }
        };
    }

    /**
     * Creates the adapter for the categorySpinner, sets it up and fills up all the needed data in it
     *
     * @return
     */
    private ArrayAdapter<String> getCategorySpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.categories)) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    /**
     * Create a listener for category_spinner that adds the selected value to the filter.
     * If the default one is selected the filter's value gets set to null.
     *
     * @return
     */
    private AdapterView.OnItemSelectedListener getCategorySpinnerListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(categorySpinner.getSelectedItemPosition() != 0) {
                    String selectedCategory = categorySpinner.getSelectedItem().toString();
                    filter.setSelectedCategory(selectedCategory);
                } else {
                    filter.setSelectedCategory(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        };
    }

    public void ageRestrictionClick(View view) {
        filter.setSelectedAge(radioBtnClick(ageRG));
    }

    public void genderRestrictionClick(View view) {
        filter.setSelectedGender(radioBtnClick(genderRg));
    }

    private String radioBtnClick(RadioGroup rg) {
        int selectedButtonId = rg.getCheckedRadioButtonId();
        RadioButton tempButton = findViewById(selectedButtonId);
        String textValue = tempButton.getText().toString();
        Toast.makeText(getBaseContext(), textValue, Toast.LENGTH_SHORT).show();
        return textValue;
    }
}
