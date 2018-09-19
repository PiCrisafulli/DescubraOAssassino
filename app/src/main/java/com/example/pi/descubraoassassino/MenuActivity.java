package com.example.pi.descubraoassassino;

import android.app.Application;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MenuActivity extends AppCompatActivity {
    Button playBtn;
    Button creditsBtn;
    Button exitBtn;

    MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Set Button's
        playBtn = findViewById(R.id.play_btn);
        creditsBtn = findViewById(R.id.credits_btn);
        exitBtn = findViewById(R.id.exit_btn);
        int maxVolume = 100;
        float log1=(float)(Math.log(maxVolume)/Math.log(maxVolume));
        if(audio == null) {
            audio = MediaPlayer.create(this, R.raw.menu);
            //audio.setVolume(1-log1,1-log1);
            audio.start();
        }

        //Set Event
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick(1);
            }
        });
        creditsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick(2);
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick(3);
            }
        });
    }

    private void btnClick(int choice){
        Intent intent;
        switch(choice){
            case 1:
                intent = new Intent(this, PlayModeActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                break;
            case 3:
                finish();
                System.exit(0);
                break;

        }
    }
}
