package com.example.malami.przewodnikkulinarny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DodawanieKategorii extends AppCompatActivity {

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.wyloguj:
                Intent i = new Intent(DodawanieKategorii.this, Logowanie.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
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
