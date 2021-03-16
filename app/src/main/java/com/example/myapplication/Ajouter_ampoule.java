package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Ajouter_ampoule extends AppCompatActivity {

    SQLClient BasedeDonnee;
    Button add_but;
    EditText add_nom;
    EditText add_conso;
    EditText add_marque;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        BasedeDonnee = new SQLClient(this);

        add_but = findViewById(R.id.add_but);
        add_nom = findViewById(R.id.add_nom);
        add_conso = findViewById(R.id.add_conso);
        add_marque = findViewById(R.id.add_marque);
        RadioGroup add_type = (RadioGroup) findViewById(R.id.add_type);
        int radioButton_id = add_type.getCheckedRadioButtonId();

        add_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = add_nom.getText().toString();
                String conso = add_conso.getText().toString();
                String marque = add_marque.getText().toString();
                int radioButton_id = add_type.getCheckedRadioButtonId();
                if (radioButton_id != -1) {
                    RadioButton radioButton_Choices = (RadioButton) findViewById(radioButton_id);
                    String type = radioButton_Choices.getText().toString();
                    if(!nom.equals("") && !conso.equals("") && !marque.equals("") && BasedeDonnee.insertData(nom,conso,marque,type) ){
                        Toast.makeText(Ajouter_ampoule.this, "Ampoule ajouter",Toast.LENGTH_SHORT).show();
                        add_nom.setText(" ");
                        add_conso.setText(" ");
                        add_marque.setText(" ");
                    }else{
                        Toast.makeText(Ajouter_ampoule.this, "Ampoule n'a pas etais ajouter verifier tout les champs",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Ajouter_ampoule.this, "SELECTIONE UN TYPE", Toast.LENGTH_LONG).show();
                }
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
                Toast.makeText(getApplicationContext(), "Va sur Stats", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Ajouter_ampoule.this, Stats.class);
                startActivity(intent);
                break;
            case R.id.Ajouter_ampoule:
                Toast.makeText(getApplicationContext(), "Vous etes sur cette page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Profil_ampoule:
                Toast.makeText(getApplicationContext(), "Va sur Profil", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(Ajouter_ampoule.this, Profil_ampoule.class);
                startActivity(intent3);
                break;
            case R.id.Accueil:
                Toast.makeText(getApplicationContext(), "Va sur Acceuil", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Ajouter_ampoule.this, Acceuil.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
