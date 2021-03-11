package com.example.myapplication;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Profil_ampoule extends AppCompatActivity {
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
                Intent intent = new Intent(Profil_ampoule.this, Stats.class);
                startActivity(intent);
                break;
            case R.id.Ajouter_ampoule:
                Toast.makeText(getApplicationContext(), "Va sur ajouter ampoule", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(Profil_ampoule.this, Ajouter_ampoule.class);
                startActivity(intent3);
                break;
            case R.id.Profil_ampoule:
                Toast.makeText(getApplicationContext(), "Vous etes sur cette page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Accueil:
                Toast.makeText(getApplicationContext(), "Va sur Acceuil", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Profil_ampoule.this, Acceuil.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
