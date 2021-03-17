package com.example.myapplication;



import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Common.Common;
import com.example.myapplication.Model.OpenWeatherMap;

import static com.example.myapplication.Common.Common.unixTimeStampToDateTime;

public class Stats extends AppCompatActivity {
    SQLClient db;
    TextView statsresult;
    TextView nbAmpoule;
    private static String TAG = "aled";


    OpenWeatherMap openWeatherMap = new OpenWeatherMap();

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Button but_stats = (Button) findViewById(R.id.StatsCall);
        db = new SQLClient(this);
        openWeatherMap = Acceuil.getOpenWeatherMap();
        statsresult = (TextView) findViewById(R.id.StatsResult);
        nbAmpoule = (TextView) findViewById(R.id.NbAmpoule);

        Cursor cursor_nbAmpoule = db.viewdata();
        int nb_ampoule = 0;
        while (cursor_nbAmpoule.moveToNext()) {
            nb_ampoule++;
        }
        nbAmpoule.setText(getString(R.string.nbAmpoule1)+" "+String.valueOf(nb_ampoule)+" "+getString(R.string.nbAmpoule2));


        but_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor_stats = db.viewdata();
                int conso = 0;
                double tempOpti = 0;
                int result = 0;
                while (cursor_stats.moveToNext()) {
                    Log.v(TAG, cursor_stats.getString(2));
                    conso = Integer.parseInt(cursor_stats.getString(2));
                    Log.v(TAG, String.valueOf(openWeatherMap.getSys().getSunset()));
                    String[] sunset = unixTimeStampToDateTime(openWeatherMap.getSys().getSunset()).split("[:]");
                    tempOpti = 24 - Double.parseDouble(sunset[0]);
                    result+=conso * tempOpti;
                }
                String resultstats = String.valueOf(conso * tempOpti);
                statsresult.setText(resultstats+"  Watt/h");
            }
        });
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
                Toast.makeText(getApplicationContext(), R.string.ToastSameActivity, Toast.LENGTH_SHORT).show();
                break;
            case R.id.Ajouter_ampoule:
                Intent intent3 = new Intent(Stats.this, Ajouter_ampoule.class);
                startActivity(intent3);
                break;
            case R.id.Profil_ampoule:
                Intent intent1 = new Intent(Stats.this, Profil_ampoule.class);
                startActivity(intent1);
                break;
            case R.id.Accueil:
                Intent intent2 = new Intent(Stats.this, Acceuil.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}