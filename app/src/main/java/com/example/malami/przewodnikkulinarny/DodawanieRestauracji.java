package com.example.malami.przewodnikkulinarny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DodawanieRestauracji extends AppCompatActivity {

    EditText NowaKategoria;
    EditText Informacje;
   // EditText Lokalizacja;
    DatabaseReference kategorie;
    String kategoria;
    String informacje;
    //String lokalizacja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie_kategorii);

        NowaKategoria=(findViewById(R.id.NowaKategoria));
        Informacje = (findViewById(R.id.Informacje));
       // Lokalizacja= (findViewById(R.id.Adres));
        kategorie = FirebaseDatabase.getInstance().getReference("Kategorie");



    }

    public void DodajKategorie(View view) {
        kategoria = NowaKategoria.getText().toString().trim();
        informacje= Informacje.getText().toString().trim();
      //  lokalizacja= Lokalizacja.getText().toString().trim();


        if(!TextUtils.isEmpty(kategoria))
        {
            kategorie kat = new kategorie(kategoria, informacje);
            String id= kategorie.push().getKey();
            kategorie.child(id).setValue(kat);

            Toast.makeText(this, "Ok, dodano", Toast.LENGTH_SHORT).show();
        }
        else {Toast.makeText(this, "Nie dodano", Toast.LENGTH_SHORT).show();}

    }
}
