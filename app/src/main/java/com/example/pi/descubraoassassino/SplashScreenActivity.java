package com.example.pi.descubraoassassino;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    //Text View
    private TextView tv_find;
    private TextView tv_the;
    private TextView tv_criminal;

    //Layout
    private LinearLayout splash_screen;

    //Animation
    private Animation up_to_down;
    private Animation down_to_up;
    private Animation fade_in;
    private Animation fade_out;

    //Audio
    private MediaPlayer audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tv_find = findViewById(R.id.splash_screen_text1);
        tv_the = findViewById(R.id.splash_screen_text2);
        tv_criminal = findViewById(R.id.splash_screen_text3);

        //Make up to down animation
        up_to_down = AnimationUtils.loadAnimation(this, R.anim.up_to_down);
        tv_find.setAnimation(up_to_down);

        //Make down to up animation
        down_to_up = AnimationUtils.loadAnimation(this,R.anim.down_to_up);
        tv_criminal.setAnimation(down_to_up);

        if(audio == null) {
            audio = MediaPlayer.create(this, R.raw.splashscreen);
            //audio.setVolume(6.0f,6.0f);
            audio.start();
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                tv_the.setVisibility(View.VISIBLE);
                fade_in = new AlphaAnimation(0.0f, 1.0f);
                fade_in.setDuration(6000);
                tv_the.setAnimation(fade_in);


            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fade_out = new AlphaAnimation(1.0f, 0.0f);
                fade_out.setDuration(2500);
                tv_find.setAnimation(fade_out);
                tv_the.setAnimation(fade_out);
                tv_criminal.setAnimation(fade_out);
                startMenu();
            }
        }, 8000);


    }

    public void startMenu() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changeActivity();
            }
        }, 2000);

    }
    private void changeActivity(){
        audio.stop();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();

    }

}

