package com.example.pi.descubraoassassino.tasks;

import android.os.AsyncTask;

import com.example.pi.descubraoassassino.NullHostNameVerifier;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CriminalListTask extends AsyncTask<String, Void, JSONObject> {


    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonResponse = null;
        try {
            URL url = new URL("https://handson.eniwine.com.br/api/descubraoassassino/criminosos");
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
}
