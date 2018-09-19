package com.example.pi.descubraoassassino;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.pi.descubraoassassino.domain.Crime;
import com.example.pi.descubraoassassino.domain.Places;
import com.example.pi.descubraoassassino.domain.Weapons;
import com.example.pi.descubraoassassino.tasks.PlacesListTask;
import com.example.pi.descubraoassassino.tasks.TheoryListTask;
import com.example.pi.descubraoassassino.tasks.TheorySendTask;
import com.example.pi.descubraoassassino.tasks.WeaponsListTask;

public class PlayModeActivity extends AppCompatActivity {

    private Button ssbob_btn;
    private Button greeng_btn;
    private Button dvader_btn;
    private Button khan_btn;
    private Button joker_btn;
    private Button skeleton_btn;
    private Button temp;

    public Crime crime;
    public Places places;
    public Weapons weapons;

    LinearLayout placesLayout;
    LinearLayout placesLayout_secondLine;
    LinearLayout placesLayout_thirdLine;
    LinearLayout weaponsLayout;
    LinearLayout weaponsLayout_secondLine;
    LinearLayout weaponsLayout_thirdLine;

    Button send_button;

    Button []allButtons = new Button[6];
    Drawable d;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mode);
        crime = new Crime();
        if(getIntent().hasExtra("misteryid")){
            crime.id = getIntent().getExtras().getString("misteryid");
        } else {
            TheoryListTask theoryListTask = new TheoryListTask(this);
            theoryListTask.execute();
        }
        System.out.println(crime.id);

        send_button = findViewById(R.id.send_btn);

        ssbob_btn = findViewById(R.id.ssbob_avatar);
        greeng_btn = findViewById(R.id.greeng_avatar);
        dvader_btn = findViewById(R.id.vader_avatar);
        khan_btn = findViewById(R.id.khan_avatar);
        joker_btn = findViewById(R.id.joker_avatar);
        skeleton_btn = findViewById(R.id.skeleton_avatar);

        skeleton_btn.getForeground().setColorFilter(R.color.red_border, PorterDuff.Mode.MULTIPLY);
        ssbob_btn.getForeground().setColorFilter(R.color.red_border, PorterDuff.Mode.MULTIPLY);
        dvader_btn.getForeground().setColorFilter(R.color.red_border, PorterDuff.Mode.MULTIPLY);
        khan_btn.getForeground().setColorFilter(R.color.red_border, PorterDuff.Mode.MULTIPLY);
        joker_btn.getForeground().setColorFilter(R.color.red_border, PorterDuff.Mode.MULTIPLY);
        greeng_btn.getForeground().setColorFilter(R.color.red_border, PorterDuff.Mode.MULTIPLY);

        allButtons[0]=skeleton_btn;
        allButtons[1]=ssbob_btn;
        allButtons[2]=dvader_btn;
        allButtons[3]=khan_btn;
        allButtons[4]=joker_btn;
        allButtons[5]=greeng_btn;

        d = skeleton_btn.getForeground();
        temp = skeleton_btn;

        placesLayout = findViewById(R.id.places);
        placesLayout_secondLine = findViewById(R.id.places_second_line);
        placesLayout_thirdLine = findViewById(R.id.places_third_line);

        weaponsLayout = findViewById(R.id.weapons);
        weaponsLayout_secondLine = findViewById(R.id.weapons_second_line);
        weaponsLayout_thirdLine = findViewById(R.id.weapons_third_line);

        places = new Places();
        weapons = new Weapons();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Espere carregar todos os campos")
                .setTitle("Carregando");

        builder.setPositiveButton(R.string.ok_btn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(crime.numeroArma != 0 ||crime.numeroLocal!= 0 ||crime.numeroSuspeito != 0){
                    sendTheory();
                }
            }
        });



        skeleton_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                getCrime().setSuspeito(skeleton_btn.getText().toString());
                getCrime().setNumeroSuspeito(1);
                skeleton_btn.getForeground().clearColorFilter();
                setallforegrounds(skeleton_btn);


            }
        });

        khan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCrime().setSuspeito(khan_btn.getText().toString());
                getCrime().setNumeroSuspeito(2);
                khan_btn.getForeground().clearColorFilter();
                setallforegrounds(khan_btn);
            }
        });

        dvader_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCrime().setSuspeito(dvader_btn.getText().toString());
                getCrime().setNumeroSuspeito(3);
                dvader_btn.getForeground().clearColorFilter();
                setallforegrounds(dvader_btn);
            }
        });

        ssbob_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCrime().setSuspeito(ssbob_btn.getText().toString());
                getCrime().setNumeroSuspeito(4);
                ssbob_btn.getForeground().clearColorFilter();
                setallforegrounds(ssbob_btn);
            }
        });

        joker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCrime().setSuspeito(joker_btn.getText().toString());
                getCrime().setNumeroSuspeito(5);
                joker_btn.getForeground().clearColorFilter();
                setallforegrounds(joker_btn);
            }
        });

        greeng_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCrime().setSuspeito(greeng_btn.getText().toString());
                getCrime().setNumeroSuspeito(6);
                greeng_btn.getForeground().clearColorFilter();
                setallforegrounds(greeng_btn);
            }
        });

        PlacesListTask placesListTask = new PlacesListTask(this);
        placesListTask.execute();

        WeaponsListTask weaponsListTask = new WeaponsListTask(this);
        weaponsListTask.execute();



    }

    public void sendTheory(){
        TheorySendTask theorySendTask = new TheorySendTask(this);
        theorySendTask.execute(crime.toJsonString());
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    public void setallforegrounds(Button button){
        for(Button b : allButtons){
            if(!b.equals(button)){
                b.getForeground().setColorFilter(R.color.red_border, PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    public void setCriminal(int number){
        crime.setSuspeito(String.valueOf(number));
    }
    public void setPlace(int number){
        crime.setLocal(String.valueOf(number));
    }
    public void setWeapon(int number){
        crime.setArma(String.valueOf(number));
    }

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }

    public Weapons getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapons weapons) {
        this.weapons = weapons;
    }

    public LinearLayout getPlacesLayout() {
        return placesLayout;
    }

    public void setPlacesLayout(LinearLayout placesLayout) {
        this.placesLayout = placesLayout;
    }

    public LinearLayout getPlacesLayout_secondLine() {
        return placesLayout_secondLine;
    }

    public void setPlacesLayout_secondLine(LinearLayout placesLayout_secondLine) {
        this.placesLayout_secondLine = placesLayout_secondLine;
    }

    public LinearLayout getWeaponsLayout() {
        return weaponsLayout;
    }

    public void setWeaponsLayout(LinearLayout weaponsLayout) {
        this.weaponsLayout = weaponsLayout;
    }

    public LinearLayout getWeaponsLayout_secondLine() {
        return weaponsLayout_secondLine;
    }

    public void setWeaponsLayout_secondLine(LinearLayout weaponsLayout_secondLine) {
        this.weaponsLayout_secondLine = weaponsLayout_secondLine;
    }

    public LinearLayout getPlacesLayout_thirdLine() {
        return placesLayout_thirdLine;
    }

    public void setPlacesLayout_thirdLine(LinearLayout placesLayout_thirdLine) {
        this.placesLayout_thirdLine = placesLayout_thirdLine;
    }

    public LinearLayout getWeaponsLayout_thirdLine() {
        return weaponsLayout_thirdLine;
    }

    public void setWeaponsLayout_thirdLine(LinearLayout weaponsLayout_thirdLine) {
        this.weaponsLayout_thirdLine = weaponsLayout_thirdLine;
    }
}
