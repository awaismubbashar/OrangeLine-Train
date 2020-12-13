package com.example.orangelinetrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private int page = 0;
    private int delay = 2500; //milliseconds
    private ViewPager viewPager;
    private SimpleFragmentPagerAdapter myAdapter;
    Runnable runnable = new Runnable() {
        public void run() {
            if (myAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };
    ArrayList<Integer> sliderImagesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        addImageInArrayList();
        handler = new Handler();
        viewPager = findViewById(R.id.viewPagerID);
        myAdapter = new SimpleFragmentPagerAdapter(this, sliderImagesArray);
        viewPager.setAdapter(myAdapter);

    }

    private void addImageInArrayList() {
        sliderImagesArray.add(R.drawable.orange_train);
        sliderImagesArray.add(R.drawable.stations);
        sliderImagesArray.add(R.drawable.ticket_machines);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                // Ensures the views overlap each other.
                view.setTranslationX(view.getWidth() * -position);

                // Alpha property is based on the view position.
                if(position <= -1.0F || position >= 1.0F) {
                    view.setAlpha(0.0F);
                } else if( position == 0.0F ) {
                    view.setAlpha(1.0F);
                } else { // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                    view.setAlpha(1.0F - Math.abs(position));
                }
            }
        });
        handler.postDelayed(runnable,delay);
    }
}