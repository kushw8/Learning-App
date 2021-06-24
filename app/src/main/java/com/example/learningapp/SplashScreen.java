package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.learningapp.auth.LoginnActivity;

public class SplashScreen extends AppCompatActivity implements Animation.AnimationListener {

    ImageView imageView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        imageView = findViewById(R.id.splash_logo);
        animation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.splash_anime);
        animation.setAnimationListener(this);
        imageView.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginnActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);

    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}