package com.razachar.freestylegame.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.razachar.freestylegame.R;

public class PresentationActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SlideAdapter slideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        mSlideViewPager=(ViewPager)findViewById(R.id.slide_View_Pager);
        mDotLayout=(LinearLayout)findViewById(R.id.dots);

        slideAdapter=new SlideAdapter(this);

        mSlideViewPager.setAdapter(slideAdapter);


    }
}
