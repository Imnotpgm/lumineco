package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLClient extends SQLiteOpenHelper {


        //Table
        private static final String NOM_TABLE ="Ampoule";

        //Colonne
        private static final String ID = "id";
        private static final String NOM = "nom";
        private static final String CONSOMATION = "conso";
        private static final String MARQUE = "marque";
        private static final String TYPE = "type";

        // Vous devez gérer le numéro de version de votre BDD (a un impact sur la reconstruction de la BDD par exemple)
        public static final int DATABASE_VERSION = 5;

        // Nom du fichier contenant la BDD (sqlite = fichier)
        public static final String  DATABASE_FILE = "lumineco.db";

        // Requete de creation de la bdd (exemple simplifié)
        public static final String SQL_CREATE = "CREATE TABLE "+NOM_TABLE+" ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ NOM +" TEXT, "+ CONSOMATION +" TEXT, "+ MARQUE +" TEXT , "+ TYPE +" TEXT);";

        // Requete de suppression de la bdd (exemple simplifié)
        public static final String SQL_DELETE = "DROP TABLE IF EXISTS  Ampoule ;";


        // Constructeur permettant d'appeler le constructeur de SQLIteOpenHelper (cf. doc)
        public SQLClient(Context context){
            super (context, DATABASE_FILE, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // On créé la BDD si besoin
            db.execSQL(SQLClient.SQL_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Si la version de la BDD change.. Ici doit être mis le code pour traiter cette situation
            // Ici : traitement violent... On supprimme et on la créé à nouveau...
            // A adapter en fonction des besoins....
            db.execSQL(SQLClient.SQL_DELETE);
            this.onCreate(db);
        }

        //Methode d'ajout de data
        public boolean insertData(String nom,String conso,String marque,String type){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put(NOM,nom);
            contentValues.put(CONSOMATION,conso);
            contentValues.put(MARQUE,marque);
            contentValues.put(TYPE,type);
            long result = db.insert(NOM_TABLE,null,contentValues);
            return result != -1; //Si result = -1 Alors c'est la merde (les data sont pas envoie dans la bd)
        }


        //Methode de lecture
        public Cursor viewdata(){
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "Select * from " + NOM_TABLE ;
            Cursor cursor = db.rawQuery(query,null);
            return cursor;
        }


    }

