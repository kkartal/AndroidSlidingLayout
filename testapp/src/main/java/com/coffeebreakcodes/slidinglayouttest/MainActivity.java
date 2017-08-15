package com.coffeebreakcodes.slidinglayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coffeebreakcodes.slidinglayout.SlidingGroupLayout;
import com.coffeebreakcodes.slidinglayout.SlidingLayout;
import com.coffeebreakcodes.slidinglayout.SlidingCase;
import com.coffeebreakcodes.slidinglayout.StatusListener;

public class MainActivity extends AppCompatActivity {

    SlidingLayout slidingLayout;
    SlidingLayout slidingLayout2;
    SlidingGroupLayout slidingGroupLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
        slidingLayout2 = (SlidingLayout) findViewById(R.id.slidingLayout2);
        slidingGroupLayout = (SlidingGroupLayout) findViewById(R.id.slidingGroupLayout);

        slidingLayout.setDirection(SlidingCase.EXPAND_DOWN);
        slidingLayout.setMinSize(200f);
        slidingLayout.setMaxSize(400f);
        slidingLayout.setExpandedAtStart(true);
        slidingLayout.hasSwipeListener(false);
        slidingLayout.setStatusListener(new StatusListener() {
            @Override
            public void status(boolean isExpanded) {
                //If you want to use status listener and independent_layouts="false"
                //at the same time, you must set
                //manuel dependency between sliding layouts
                slidingGroupLayout.expandOthers(slidingLayout, isExpanded);
            }
        });

        slidingLayout2.setDirection(SlidingCase.EXPAND_UP);
        slidingLayout2.setMinSize(200f);
        slidingLayout2.setMaxSize(400f);
        slidingLayout2.setExpandedAtStart(true);
        slidingLayout2.hasSwipeListener(false);
        slidingLayout2.setStatusListener(new StatusListener() {
            @Override
            public void status(boolean isExpanded) {
                //If you want to use status listener and independent_layouts="false"
                //at the same time, you must set
                //manuel dependency between sliding layouts
                slidingGroupLayout.expandOthers(slidingLayout2, isExpanded);
            }
        });

        Button buttonTog = (Button) findViewById(R.id.buttonToggle);
        buttonTog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingLayout.autoToggle();
            }
        });

        Button buttonEx = (Button) findViewById(R.id.buttonEx);
        buttonEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingLayout.expand();
            }
        });

        Button buttonCol = (Button) findViewById(R.id.buttonCol);
        buttonCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingLayout.collapse();
            }
        });
    }
}
