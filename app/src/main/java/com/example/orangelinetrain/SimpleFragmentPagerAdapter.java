package com.example.orangelinetrain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SimpleFragmentPagerAdapter  extends PagerAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    ArrayList<Integer> SliderImagesArray;
    public SimpleFragmentPagerAdapter(Context context, ArrayList<Integer> Images){
        mContext = context;
        SliderImagesArray = Images;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View ImageLayout = inflater.inflate(R.layout.simple_slider_activity, container, false);
        assert ImageLayout != null;
        ImageView imageView =  ImageLayout.findViewById(R.id.simpleSliderimageViewID);
        imageView.setImageResource(SliderImagesArray.get(position));
        container.addView(ImageLayout, 0);
        return ImageLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return SliderImagesArray.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}