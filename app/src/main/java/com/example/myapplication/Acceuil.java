package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.Common.Common;

import com.example.myapplication.Model.OpenWeatherMap;

import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Acceuil extends AppCompatActivity implements LocationListener {

    TextView txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTime, txtCelsius;
    ImageView imageView;

    private static String TAG = "callAPI";

    LocationManager locationManager;
    String provider;
    static Location location;
    static double lat ;//= location.getLatitude();
    static double lng ;//= location.getLongitude();
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();

    int MY_PERMISSION = 0;

    private static final String key = Common.API_KEY;
    private String longitude;
    private String latitude;
    private String units = "metric";
    private TextView textViewJSON; // TextView dans lequel on va insérer le JSON récupéré de l'API

    private static final String API_BASE_URL = Common.API_LINK;

    // Instance nécessaires au traitement (pour Retrofit)
    Retrofit retrofit;
    OpenWeatherMapInterface serviceAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Control
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtLastUpdate = (TextView) findViewById(R.id.txtLastUpdate);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtCelsius = (TextView) findViewById(R.id.txtCelsius);
        imageView = (ImageView) findViewById(R.id.ImageView);


        //Get Coordinate
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(Acceuil.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null) {
            Log.e("TAG", "No Location");
        }


        this.textViewJSON = (TextView) findViewById(R.id.txtJson);


        this.retrofit = new Retrofit.Builder()
                .baseUrl(Acceuil.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.serviceAPI = retrofit.create(OpenWeatherMapInterface.class);
        Log.v(TAG,"Latitude : "+ Acceuil.this.latitude);
        Log.v(TAG, "Longitude : "+Acceuil.this.longitude);


        Button boutonFiche = (Button) findViewById(R.id.meteoButton);
        boutonFiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // appel méthode getResult de l'interface OpenWeatherMapInterface
                Call<JsonElement> appel = serviceAPI.getResult(Acceuil.this.latitude, Acceuil.this.longitude, Acceuil.this.key, Acceuil.this.units);
                //Log.v(TAG, appel.toString());
                //Log.v(TAG, "Test");
                //Log.v(TAG,"Latitude : "+ Acceuil.this.latitude);
                //Log.v(TAG, "Longitude : "+Acceuil.this.longitude);
                appel.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        //Log.v(TAG, "Entré dans le onResponse");
                        //Log.v(TAG, String.valueOf(response.raw().request().url()));
                        if (response.isSuccessful()) {
                            //Log.v(TAG,"réponse successful");
                            // Récupère le contenu JSON de la réponse
                            JsonElement contenu = response.body();
                            Log.v(TAG, contenu.toString());
                            textViewJSON.setText(contenu.toString());
                            Gson gson = new Gson();
                            Type mType = new TypeToken<OpenWeatherMap>(){}.getType();
                            openWeatherMap = gson.fromJson(contenu,mType);
                            txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));
                            txtLastUpdate.setText(String.format("Last Updated: %s", Common.getDateNow()));
                            txtDescription.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
                            txtHumidity.setText(String.format("%d%%",openWeatherMap.getMain().getHumidity()));
                            txtTime.setText(String.format("%s/%s",Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunrise()),Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunset())));
                            txtCelsius.setText(String.format("%.2f °C",openWeatherMap.getMain().getTemp()));
                            Picasso.with(Acceuil.this).load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon())).into(imageView);
                        } else {
                            //Log.v(TAG, "réponse non successful");
                        }
                    }




                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Toast.makeText(Acceuil.this, getString(R.string.API_Error) + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        ActivityCompat.requestPermissions(Acceuil.this, new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, MY_PERMISSION);
        locationManager.removeUpdates(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Acceuil.this,new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE



            }, MY_PERMISSION);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Stats:
                Intent intent = new Intent(Acceuil.this, Stats.class);
                startActivity(intent);
                break;
            case R.id.Ajouter_ampoule:
                Intent intent2 = new Intent(Acceuil.this, Ajouter_ampoule.class);
                startActivity(intent2);
                break;
            case R.id.Profil_ampoule:
                Intent intent3 = new Intent(Acceuil.this, Profil_ampoule.class);
                startActivity(intent3);
                break;
            case R.id.Accueil:
                Toast.makeText(getApplicationContext(), R.string.ToastSameActivity, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        Acceuil.this.longitude = String.valueOf(lng);
        Acceuil.this.latitude = String.valueOf(lat);
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }


}


