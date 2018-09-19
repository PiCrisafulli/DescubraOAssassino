package com.example.pi.descubraoassassino.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.pi.descubraoassassino.NullHostNameVerifier;
import com.example.pi.descubraoassassino.PlayModeActivity;
import com.example.pi.descubraoassassino.PlayModeResultActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TheorySendTask extends AsyncTask<String, Void, JSONObject> {

    Context context;
    String value;

    public TheorySendTask(Context context) {
        this.context = context;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jsonResponse = null;
        try {
            URL url = new URL("https://handson.eniwine.com.br/api/descubraoassassino/");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("content-type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            urlConnection.setHostnameVerifier(new NullHostNameVerifier());

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params[0]);

            System.out.println(params[0]);

            writer.flush();
            writer.close();
            os.close();



            boolean isError = urlConnection.getResponseCode() != HttpsURLConnection.HTTP_OK;

            String response = "{}";

            InputStreamReader responseBodyReader = null;
            if (!isError) {

                InputStream responseBody = urlConnection.getInputStream();
                responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                value = String.valueOf(new JSONParser().parse(responseBodyReader));
            }
            jsonResponse = new JSONObject();
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
                    Intent intent = new Intent(context,PlayModeResultActivity.class);
                    intent.putExtra("result",value);
                    intent.putExtra("misteryid",((PlayModeActivity)context).getIntent().getExtras().getString("misteryid"));
                    context.startActivity(intent);
                    ((PlayModeActivity) context).finish();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

