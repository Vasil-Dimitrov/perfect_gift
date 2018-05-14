package com.peridot.perfectgift.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.peridot.perfectgift.R;

public class DetailViewActivity extends AppCompatActivity {
    String[] titles;
    String[] descriptions;
    String[] prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        int index = intent.getIntExtra("com.peridot.perfectgift.ITEM_INDEX", -1);

        if(index > -1) {
            int pic = getImage(index);
            ImageView img = (ImageView) findViewById(R.id.imageView);
            scaleImg(img, pic);
        }

        titles = getResources().getStringArray(R.array.items);
        descriptions = getResources().getStringArray(R.array.descriptions);
        prices = getResources().getStringArray(R.array.prices);

        TextView title = findViewById(R.id.detailed_view_title);
        title.setText(titles[index]);

        TextView description = findViewById(R.id.detailed_view_description);
        description.setText(descriptions[index]);

        TextView price = findViewById(R.id.detailed_view_price);
        price.setText(prices[index] + "лв.");

        TextView email = findViewById(R.id.detailed_view_email);
        email.setText("E-mail: " + R.string.default_email);

        TextView phone = findViewById(R.id.detailed_view_phone);
        phone.setText("Телефон: " + R.string.default_number);
    }

    private int getImage(int index) {
        switch(index) {
            case 0: return R.drawable.bmw_i8;
            case 1: return R.drawable.ford_focus;
            case 2: return R.drawable.golf_4;
            default: return -1;
        }
    }

    private void scaleImg(ImageView img, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        // looking at the resource without having to draw (save a lot of memory resource)
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth) {
            int ratio = Math.round( (float) imgWidth / (float) screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaleImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaleImg);
    }
}
