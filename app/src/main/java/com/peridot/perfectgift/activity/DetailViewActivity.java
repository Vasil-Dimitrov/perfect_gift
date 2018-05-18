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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("com.peridot.perfectgift.ITEM_NAME");
        String imgageName = intent.getExtras().getString("com.peridot.perfectgift.ITEM_IMAGE");
        String desc = intent.getExtras().getString("com.peridot.perfectgift.ITEM_DESC");
        String contactNumber = intent.getExtras().getString("com.peridot.perfectgift.ITEM_NUMBER");
        String contactEmail = intent.getExtras().getString("com.peridot.perfectgift.ITEM_EMAIL");
        Double finalPrice = intent.getDoubleExtra("com.peridot.perfectgift.ITEM_PRICE", 0.00);

        int resID = getResources().getIdentifier(imgageName , "drawable", getPackageName());
        ImageView img = findViewById(R.id.imageView);
        scaleImg(img, resID);

        TextView title = findViewById(R.id.detailed_view_title);
        title.setText(name);

        TextView description = findViewById(R.id.detailed_view_description);
        description.setText(desc);

        TextView price = findViewById(R.id.detailed_view_price);
        price.setText(finalPrice + "лв.");

        TextView email = findViewById(R.id.detailed_view_email);
        email.setText("E-mail: " + contactEmail);

        TextView phone = findViewById(R.id.detailed_view_phone);
        phone.setText("Телефон: " + contactNumber);
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
