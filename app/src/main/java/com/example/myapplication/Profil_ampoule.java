package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.security.identity.PersonalizationData;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Profil_ampoule extends AppCompatActivity {

    SQLClient db;

    ListView liste_ampoule;


    ArrayList<Ampoule> listItem;
    CustomList_ampouleAdapter adapter;



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
                AlertDialog.Builder builder = new AlertDialog.Builder(Profil_ampoule.this);
                builder.setMessage("Tu veux suppr");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Ampoule amp = listItem.get(position);
                        if(db.deleteData(amp.getAmpouleNom(),amp.getAmpouleConso(),amp.getAmpouleMarque(),amp.getAmpouleType()) >= 1) {
                            Toast.makeText(Profil_ampoule.this, "La valeur a etait del", Toast.LENGTH_SHORT).show();
                            listItem.remove(amp);
                            adapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(Profil_ampoule.this, "ALED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("NO",null);
                builder.show();
            }
        });
    }

    private void viewdata() {
        Cursor cursor = db.viewdata();

        if(cursor.getCount() == 0){
            Toast.makeText(this,"Probleme ou pas de valeur dans la bd",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                String nom = cursor.getString(1);// 1 = Nom
                String conso = cursor.getString(2);// 2 = consomation
                String marque = cursor.getString(3);// 3 = marque
                String type = cursor.getString(4);// 4 = type
                Ampoule val = new Ampoule(nom, conso, marque, type);
                this.listItem.add(val);
            }
            adapter = new CustomList_ampouleAdapter(this,listItem);
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
