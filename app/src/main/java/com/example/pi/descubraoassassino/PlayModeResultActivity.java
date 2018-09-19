package com.example.pi.descubraoassassino;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pi.descubraoassassino.tasks.TheorySendTask;

public class PlayModeResultActivity extends AppCompatActivity  {

    TextView result;
    Button yes_btn;
    Button no_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_mode_result);
        result = findViewById(R.id.result);
        yes_btn = findViewById(R.id.yes_btn);
        no_btn = findViewById(R.id.no_btn);

        String resultString;
        switch(Integer.valueOf(getIntent().getExtras().get("result").toString())){
            case 0:
                result.setText(R.string.case_Correct);
                break;
            case 1:
                result.setText(R.string.case_One);
                break;
            case 2:
                result.setText(R.string.case_Two);
                break;
            case 3:
                result.setText(R.string.case_Three);
                break;
        }
        final Context context = this;
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context,PlayModeActivity.class);
               intent.putExtra("misteryid",getIntent().getExtras().getString("misteryid"));
               startActivity(intent);
               finish();
            }
        });

        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public TextView getResult() {
        return result;
    }

    public void setResult(TextView result) {
        this.result = result;
    }
}
