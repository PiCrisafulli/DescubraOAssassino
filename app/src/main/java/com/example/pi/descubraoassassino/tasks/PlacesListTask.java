package com.example.pi.descubraoassassino.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.pi.descubraoassassino.NullHostNameVerifier;
import com.example.pi.descubraoassassino.PlayModeActivity;
import com.example.pi.descubraoassassino.R;
import com.example.pi.descubraoassassino.domain.Place;
import com.example.pi.descubraoassassino.domain.Places;

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

import javax.net.ssl.HttpsURLConnection;

public class PlacesListTask extends AsyncTask<String, Void, JSONArray> {

    Context context;
    int urlResponse;
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    public PlacesListTask(Context context){
        this.context = context;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        JSONArray jsonResponse = null;
        try {
            URL url = new URL("https://handson.eniwine.com.br/api/descubraoassassino/locais");
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

            System.out.println(response);
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
                ArrayList<Place> places = new ArrayList<>();
                for(int i = 0 ; i < response.length(); i++){
                    try {
                        Place place = new Place((JSONObject) response.get(i));
                        System.out.println(place.getId() + " " + place.getNome());
                        places.add(place);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ((PlayModeActivity) context).getPlaces().setPlaces(places);
                createCBox(((PlayModeActivity) context).getPlacesLayout(),0,4);
                createCBox(((PlayModeActivity) context).getPlacesLayout_secondLine(),4,8);
                createCBox(((PlayModeActivity) context).getPlacesLayout_thirdLine(),8,((PlayModeActivity) context).getPlaces().getPlaces().size());
            }
        }
    }

    public void createCBox(LinearLayout layout, int start, int size){
        CheckBox checkBox;
        for(int i = start; i < size ; i++){
            checkBox = new CheckBox(context);
            checkBox.setId(i);
            checkBox.setText(((PlayModeActivity) context).getPlaces().getPlaces().get(i).getNome());
            checkBox.setTag(((PlayModeActivity) context).getPlaces().getPlaces().get(i).getNome());
            final CheckBox temp = checkBox;
            final int finalI = i;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    temp.setTextColor(R.color.red_border);
                    ((PlayModeActivity) context).getCrime().setLocal(temp.getText().toString());
                    ((PlayModeActivity) context).getCrime().setNumeroLocal(finalI);
                    CheckBox c = temp;
                    setAllTextColors(c);
                }
            });
            checkBoxes.add(checkBox);
            layout.addView(checkBox);
        }
    }
    public void setAllTextColors(CheckBox checkBox){
        for(CheckBox b : checkBoxes){
            if(!b.equals(checkBox)){
                b.setTextColor(context.getResources().getColorStateList(R.color.splash_background));
                b.setChecked(false);
            }
        }
    }
}
