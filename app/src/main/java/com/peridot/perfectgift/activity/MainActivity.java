package com.peridot.perfectgift.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import com.peridot.perfectgift.Entity.Category;
import com.peridot.perfectgift.Entity.Product;
import com.peridot.perfectgift.R;
import com.peridot.perfectgift.SearchFilter;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {
    RadioGroup genderRg;
    RadioGroup ageRG;

    Spinner categorySpinner;

    Button searchButton;

    EditText fromPrice;
    EditText toPrice;

    SearchFilter filter = new SearchFilter();

    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().initialData(realm -> {
            mockDataToRealm(realm);
        }).deleteRealmIfMigrationNeeded().name("realm.db").build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        loadGlobalVariables();
    }

    private void mockDataToRealm(Realm realm) {
        Category car = realm.createObject(Category.class, 1L);
        car.setName("Коли");
        Category technology = realm.createObject(Category.class, 2L);
        technology.setName("Технологии");
        Category fitness = realm.createObject(Category.class, 3L);
        fitness.setName("Фитнес");

        /**
         * Products
         */
        String defNum = "+359-878-123-345";
        String defEm = "info@peridot.com";
        Product car1 = realm.createObject(Product.class, 1L);
        car1.setName("BMW I8");
        car1.setShortDescription("НОВ ВНОС! Перфектно състояние!");
        car1.setLongDescription("НОВ ВНОС от Англия! Колата е в перфектно състояние от всяка гледна точка. Цената не подлежи на договаряне.");
        car1.setPrice(130000.00);
        car1.setContactNumber(defNum);
        car1.setContactEmail(defEm);
        car1.setGenderSpecific("Мъже");
        car1.setMinAge(18);
        car1.setImage("bmw_i8");
        car1.getCategories().add(car);

        Product car2 = realm.createObject(Product.class, 2L);
        car2.setName("Ford Focus");
        car2.setShortDescription("Колата е като нова отвътре, салон и ходова част без забележки, ревизиран климатик. Всичко по нея работи, алуминиеви джанти с зимни гуми мишелин.");
        car2.setLongDescription("Колата е като нова отвътре, салон и ходова част без забележки, ревизиран климатик. Всичко по нея работи, алуминиеви джанти с зимни гуми мишелин.");
        car2.setPrice(15000.00);
        car2.setContactNumber(defNum);
        car2.setContactEmail(defEm);
        car2.setMinAge(18);
        car2.setImage("ford_focus");
        car2.getCategories().add(car);

        Product car3 = realm.createObject(Product.class, 3L);
        car3.setName("Golf 4");
        car3.setShortDescription("Двигател 1.6i Изгодно предложение! Метанова уредба, Кожен салон, Регулиращ се вулан, Ел.стъкла.");
        car3.setLongDescription("Двигател 1.6i Изгодно предложение! Метанова уредба, Кожен салон, Регулиращ се вулан, Ел.стъкла.");
        car3.setPrice(5000.00);
        car3.setContactNumber(defNum);
        car3.setContactEmail(defEm);
        car3.setMinAge(18);
        car3.setGenderSpecific("Жени");
        car3.setImage("golf_4");
        car3.getCategories().add(car);

        Product car4 = realm.createObject(Product.class, 4L);
        car4.setName("Toy Car");
        car4.setShortDescription("Автомобила е внос от Германия от Немски дилър на Audi, пълна сервизна история, възможност за N1 - ползване на ДДС, възможност за лизинг срок до 5г. и аванс от 20%");
        car4.setLongDescription("Автомобила е внос от Германия от Немски дилър на Audi, пълна сервизна история, възможност за N1 - ползване на ДДС, възможност за лизинг срок до 5г. и аванс от 20%");
        car4.setPrice(100.00);
        car4.setContactNumber(defNum);
        car4.setContactEmail(defEm);
        car4.setMinAge(3);
        car4.setMaxAge(12);
        car4.setImage("toy_car");
        car4.getCategories().add(car);

        Product fitness1 = realm.createObject(Product.class, 5L);
        fitness1.setName("Myprotein L-Carnitine");
        fitness1.setShortDescription("Супер силен L-Carnitine на много изгодна цена!");
        fitness1.setLongDescription("L-Carnitine е известен, като средство за регулиране на теглото. Неговата функция в организма е да транспортира мазнините " +
                "до депата за изгаряне на мазнини, където те се преобразуват в енергия.");
        fitness1.setPrice(18.99);
        fitness1.setContactNumber(defNum);
        fitness1.setContactEmail(defEm);
        fitness1.setMinAge(18);
        fitness1.setGenderSpecific("Жени");
        fitness1.setImage("carnitine");
        fitness1.getCategories().add(fitness);

        Product fitness2 = realm.createObject(Product.class, 6L);
        fitness2.setName("Whey Protein Scitec");
        fitness2.setShortDescription("100% Whey Protein Professional Scitec Nutrition 920 грама");
        fitness2.setLongDescription("100% Whey Protein Professional на Scitec Nutrition е протеин, който ще ви помогне да увеличите " +
                "бързо мускулният растеж, а също така подобрява възстановяването и стимулира стопяването на излишните телесни мазнини.");
        fitness2.setPrice(49.99);
        fitness2.setContactNumber(defNum);
        fitness2.setContactEmail(defEm);
        fitness2.setMinAge(18);
        fitness2.setImage("protein");
        fitness2.getCategories().add(fitness);

        Product fitness3 = realm.createObject(Product.class, 7L);
        fitness3.setName("WIN Nutrition Gainer");
        fitness3.setShortDescription("WIN Nutrition 100% Hi-Mass Gainer Chocolate 2kg");
        fitness3.setLongDescription("WIN Nutrition Hi-Mass Gainer е изключително добър гейнър, с който ще прескочите генетичните ограничения и ще покачите мускулна маса трайно! \n" +
                "Ако искате да покачите мускулна маса, но метаболизмът ви е изключително бърз, то отговорът е много лесен - WIN Nutrition Hi-Mass Gainer. ");
        fitness3.setPrice(65.00);
        fitness3.setContactNumber(defNum);
        fitness3.setContactEmail(defEm);
        fitness3.setGenderSpecific("Мъже");
        fitness3.setMinAge(18);
        fitness3.setImage("gainer");
        fitness3.getCategories().add(fitness);

        Product fitness4 = realm.createObject(Product.class, 8L);
        fitness4.setName("Зоб");
        fitness4.setShortDescription("Тестостерон 5 спринцовки");
        fitness4.setLongDescription("Тестостеронът е стероиден хормон от групата на половите хормони. При бозайниците хормонът се секретира основно от тестисите " +
                "на мъжките и от яйчниците при женските, въпреки че малки количества се секретират и от надбъбречните жлези. " +
                "По принцип е главен мъжки полов хормон и анаболитен стероид.");
        fitness4.setPrice(25.00);
        fitness4.setContactNumber(defNum);
        fitness4.setContactEmail(defEm);
        fitness4.setGenderSpecific("Мъже");
        fitness4.setMinAge(18);
        fitness4.setImage("zob");
        fitness4.getCategories().add(fitness);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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
        RealmResults<Category> categoriesInDb = realm.where(Category.class).findAll();
        String[] categoriesStrArray = new String[categoriesInDb.size() + 1];
        categoriesStrArray[0] = "избери категория..";
        for(int i = 1; i< categoriesInDb.size()+1; i++) {
            categoriesStrArray[i] = categoriesInDb.get(i-1).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesStrArray) {
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
