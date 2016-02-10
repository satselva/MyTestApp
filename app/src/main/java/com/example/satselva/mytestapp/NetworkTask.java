package com.example.satselva.mytestapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static android.util.Base64.encode;

/**
 * Helper class to make HTTP request with the appropriate verb.
 * Adds basic auth token to the request header.
 *
 * Created by satselva on 2/10/2016.
 */
public class NetworkTask extends AsyncTask<String, Authenticator.RequestorType, String>{

    /** Base 64 encoded string for knappily-auth:Kna39ppily! */
    private static final String BASIC_AUTH_TOKEN = "Basic a25hcHBpbHktYXV0aDpLbmEzOXBwaWx5IQ==";

    public static enum RequestType {
        GET, POST, PUT, DELETE;
    }

    private final RequestType requestType;

    public NetworkTask(final RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod(requestType.name());
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", BASIC_AUTH_TOKEN);

            Map<String, String> postDataParams = new HashMap<>();
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(new String());
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            } else {
                response.append("");
            }
            Log.d("CATEGORY", String.format("Returned category with the following data %s", response.toString()));
            return response.toString();
        } catch (Exception e) {
            Log.e("CATEGORY", String.format("Exception occurred while reading response from [%s]", urls[0]), e);
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
