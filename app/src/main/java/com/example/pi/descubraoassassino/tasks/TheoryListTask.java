package com.example.pi.descubraoassassino.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.pi.descubraoassassino.NullHostNameVerifier;
import com.example.pi.descubraoassassino.PlayModeActivity;
import com.example.pi.descubraoassassino.domain.Crime;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class TheoryListTask extends AsyncTask <String, Void, JSONObject> {

    Context context;

    public TheoryListTask(Context context) {
        this.context = context;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonResponse = null;
        try {
            URL url = new URL("https://handson.eniwine.com.br/api/descubraoassassino");
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
                jsonResponse = new JSONObject(value);
            }

            jsonResponse.put("response", urlConnection.getResponseCode());

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
    protected void onPostExecute(JSONObject response) {
        super.onPostExecute(response);
        try {
            if (response.getInt("response") != 503) {
                if (response.getInt("response") == 200) {
                    ((PlayModeActivity) context).getCrime().setId(response.getString("misterioId"));
                    ((PlayModeActivity) context).getIntent().putExtra("misteryid",response.getString("misterioId"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
