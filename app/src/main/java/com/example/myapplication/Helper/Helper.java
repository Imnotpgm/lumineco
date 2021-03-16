package com.example.myapplication.Helper;

import android.nfc.Tag;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Helper {
    static String stream = null;
    private static String TAG = "getHTTPDataOK";

    public Helper() {
    }

    public String getHTTPData(String urlString){
        Log.v(TAG,"début getHTTPData");
        Log.v(TAG,"urlString : "+urlString);
        try {
            Log.v(TAG, "Entré dans le try");
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            int reponse = httpURLConnection.getResponseCode();
            Log.v(TAG,"Réponse HTTP : ");
            if(reponse == 200)
            {
                Log.v(TAG,"Entré dans le if");
                BufferedReader r = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = r.readLine())!=null)
                    Log.v(TAG, line);
                    sb.append(line);
                stream = sb.toString();
                httpURLConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  stream;
    }

}
