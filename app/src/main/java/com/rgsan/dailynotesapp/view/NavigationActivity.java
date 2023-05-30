package com.rgsan.dailynotesapp.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.rgsan.dailynotesapp.R;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class NavigationActivity extends AppCompatActivity {
    ViewPager slideViewPager;
    LinearLayout dotIndicator;
    Button backButton, nextButton, skipButton;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            setDotIndicator(position);
            backButton.setVisibility(position > 0 ? View.VISIBLE : View.INVISIBLE);
            nextButton.setText(position == 2 ? "Finish" : "Next");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        getSupportActionBar().hide();

        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);
        skipButton = findViewById(R.id.skipButton);
        slideViewPager = findViewById(R.id.slideViewPager);
        dotIndicator = findViewById(R.id.dotIndicator);

        viewPagerAdapter = new ViewPagerAdapter(this);
        slideViewPager.setAdapter(viewPagerAdapter);

        setDotIndicator(0);
        slideViewPager.addOnPageChangeListener(viewPagerListener);

        backButton.setOnClickListener(v -> {
            if (getItem(0) > 0) {
                slideViewPager.setCurrentItem(getItem(-1), true);
            }
        });

        nextButton.setOnClickListener(v -> {
            if (getItem(0) < 2)
                slideViewPager.setCurrentItem(getItem(1), true);
            else {
                Intent i = new Intent(NavigationActivity.this, GetStarted.class);
                startActivity(i);
                finish();
            }
        });

        skipButton.setOnClickListener(v -> {
            Intent i = new Intent(NavigationActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setDotIndicator(int position) {
        dotIndicator.removeAllViews();
        dots = new TextView[3];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY));
            }
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(this, R.color.grey));
            dotIndicator.addView(dots[i]);
        }
        dots[position].setTextColor(ContextCompat.getColor(this, R.color.purple_500));
    }

    private int getItem(int i) {
        return slideViewPager.getCurrentItem() + i;
    }
}
