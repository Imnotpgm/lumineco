package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Profil_ampoule extends AppCompatActivity {

    SQLClient db;

    ListView liste_ampoule;
    ListView consolist;
    ListView marquelist;
    ListView typelist;

    ArrayList<String> listItem;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        db = new SQLClient(this);
        listItem = new ArrayList<>();
        liste_ampoule = findViewById(R.id.liste_ampoule);

        viewdata();

        liste_ampoule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = liste_ampoule.getItemAtPosition(position).toString();
                Toast.makeText(Profil_ampoule.this,""+text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewdata() {
        Cursor cursor = db.viewdata();

        if(cursor.getCount() == 0){
            Toast.makeText(this,"Probleme ou pas de valeur dans la bd",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));// 1 = Nom
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
            liste_ampoule.setAdapter(adapter);
        }
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
