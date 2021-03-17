package com.example.myapplication;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Stats extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

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
                Toast.makeText(getApplicationContext(), "Vous etes sur cette page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Ajouter_ampoule:
                Toast.makeText(getApplicationContext(), "Va sur ajouter", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(Stats.this, Ajouter_ampoule.class);
                startActivity(intent3);
                break;
            case R.id.Profil_ampoule:
                Toast.makeText(getApplicationContext(), "Va sur profil", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Stats.this, Profil_ampoule.class);
                startActivity(intent1);
            case R.id.Accueil:
                Toast.makeText(getApplicationContext(), "Va sur Accueil", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Stats.this, Acceuil.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }


}