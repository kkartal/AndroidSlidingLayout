package com.coffeebreakcodes.slidinglayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coffeebreakcodes.equalizerlayout.EqualizerLayout;
import com.coffeebreakcodes.equalizerlayout.SlidingCase;
import com.coffeebreakcodes.equalizerlayout.StatusListener;

public class MainActivity extends AppCompatActivity {

    EqualizerLayout equalizerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equalizerLayout = (EqualizerLayout) findViewById(R.id.equalizerLayout);

        equalizerLayout.setDirection(SlidingCase.EXPAND_DOWN);
        equalizerLayout.setMinSize(100f);
        equalizerLayout.setMaxSize(300f);
        equalizerLayout.setExpandedAtStart(true);
        equalizerLayout.setStatusListener(new StatusListener() {
            @Override
            public void status(boolean isExpanded) {

            }
        });

        Button buttonTog = (Button) findViewById(R.id.buttonToggle);
        buttonTog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equalizerLayout.autoToggle();
            }
        });

        Button buttonEx = (Button) findViewById(R.id.buttonEx);
        buttonEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equalizerLayout.expand();
            }
        });

        Button buttonCol = (Button) findViewById(R.id.buttonCol);
        buttonCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equalizerLayout.collapse();
            }
        });
    }
}
