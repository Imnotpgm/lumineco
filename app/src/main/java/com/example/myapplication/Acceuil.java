package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public class Acceuil extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Toast.makeText(getApplicationContext(), "Va sur Stats", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Acceuil.this, Stats.class);
                startActivity(intent);
                break;
            case R.id.Ajouter_ampoule:
                Toast.makeText(getApplicationContext(), "Va sur Ajouter ampoule", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Acceuil.this, Ajouter_ampoule.class);
                startActivity(intent2);
                break;
            case R.id.Profil_ampoule:
                Toast.makeText(getApplicationContext(), "Va sur profil", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(Acceuil.this, Profil_ampoule.class);
                startActivity(intent3);
                break;
            case R.id.Accueil:
                Toast.makeText(getApplicationContext(), "Vous etes sur cette page", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


