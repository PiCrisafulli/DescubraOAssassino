package com.example.pi.descubraoassassino.tasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.pi.descubraoassassino.NullHostNameVerifier;
import com.example.pi.descubraoassassino.PlayModeActivity;
import com.example.pi.descubraoassassino.R;
import com.example.pi.descubraoassassino.domain.Weapon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class WeaponsListTask extends AsyncTask<String, Void, JSONArray> {

    Context context;
    int urlResponse;
    ArrayList<Button> allWeapons = new ArrayList<>();
    AlertDialog.Builder builder;

    public WeaponsListTask(Context context){
        this.context = context;
    }


    @Override
    protected JSONArray doInBackground(String... strings) {
        JSONArray jsonResponse = null;
        try {
            URL url = new URL("https://handson.eniwine.com.br/api/descubraoassassino/armas");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setHostnameVerifier(new NullHostNameVerifier());

            boolean isError = urlConnection.getResponseCode() != HttpsURLConnection.HTTP_OK;

            String response = "{}";

            InputStreamReader responseBodyReader = null;
            if (!isError) {

                InputStream responseBody = urlConnection.getInputStream();
                responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                String value = String.valueOf(new JSONParser().parse(responseBodyReader));
                jsonResponse = new JSONArray(value);
            }
            System.out.println(jsonResponse);

            urlResponse = urlConnection.getResponseCode();

            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

    @Override
    protected void onPostExecute(JSONArray response) {
        super.onPostExecute(response);
        if (urlResponse != 503) {
            if (urlResponse == 200) {
                ArrayList<Weapon> weapons = new ArrayList<>();
                for(int i = 0 ; i < response.length(); i++){
                    try {
                        Weapon weapon = new Weapon((JSONObject) response.get(i));
                        System.out.println(weapon.getId() + " " + weapon.getNome());
                        weapons.add(weapon);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ((PlayModeActivity) context).getWeapons().setWeapons(weapons);
                createButtons(((PlayModeActivity) context).getWeaponsLayout(),0,4);
                createButtons(((PlayModeActivity) context).getWeaponsLayout_secondLine(),4,8);
                createButtons(((PlayModeActivity) context).getWeaponsLayout_thirdLine(),8,
                        ((PlayModeActivity) context).getWeapons().getWeapons().size());

            }
        }
    }

    public void createButtons(LinearLayout layout,int start,int size){
        Button button;
        for(int i = start; i < size ; i++){
            button = new Button(context);
            button.setId(i);
            button.setText(((PlayModeActivity) context).getWeapons().getWeapons().get(i).getNome());
            button.setTextSize(11);
            button.setWidth(100);
            button.setTag(((PlayModeActivity) context).getWeapons().getWeapons().get(i).getNome());
            button.setPadding(1,1,1,1);
            button.setBackgroundResource(R.color.menu_background_button);
            layout.addView(button);
            final Button finalButton = button;
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalButton.setBackgroundResource(R.color.red_border);
                    ((PlayModeActivity) context).getCrime().setArma(finalButton.getText().toString());
                    ((PlayModeActivity) context).getCrime().setNumeroArma(finalI);
                    Button b = finalButton;
                    setAllBackgrounds(b);

                }
            });
            allWeapons.add(button);
        }
    }
    public void setAllBackgrounds(Button button){
        for(Button b : allWeapons){
            if(!b.equals(button)){
                b.setBackgroundResource(R.color.menu_background_button);
            }
        }
    }

}
