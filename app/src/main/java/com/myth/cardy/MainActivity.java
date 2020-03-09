package com.myth.cardy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

import android.content.Intent;
import android.transition.Transition;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewTitle,textViewSlogan;
    Button buttonScan;
    ViewGroup transitionsContainer;
    ImageButton buttonSettings;
    ImageView backgroundBottom;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSettings = findViewById(R.id.buttonSettings);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewSlogan = findViewById(R.id.textViewSlogan);
        buttonScan = findViewById(R.id.buttonScan);
        transitionsContainer = findViewById(R.id.transitions_container);
        backgroundBottom = findViewById(R.id.bottombg);

        final Handler handlerFirst = new Handler();
        handlerFirst.postDelayed(new Runnable() {
            @Override
            public void run() {
                final ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.setPathMotion(new ArcMotion());
                changeBounds.setDuration(500);
                TransitionManager.beginDelayedTransition(transitionsContainer, changeBounds);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) textViewTitle.getLayoutParams();
                params.gravity = (Gravity.START | Gravity.TOP);
                textViewTitle.setLayoutParams(params);
                textViewTitle.animate().scaleX(0.8f).setDuration(500);
                textViewTitle.animate().scaleY(0.8f).setDuration(500);

            }
        }, 500);

        final TransitionSet set = new TransitionSet()
                .addTransition(new Fade())
                .setInterpolator(new FastOutLinearInInterpolator());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                textViewSlogan.setVisibility(View.VISIBLE);
                buttonScan.animate().alpha(1).setDuration(300);
                buttonSettings.animate().alpha(1).setDuration(300);
                buttonSettings.animate().rotation(360).setDuration(500);
                backgroundBottom.animate().translationY(0).setDuration(500);
            }
        }, 1000);


        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ScanActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
