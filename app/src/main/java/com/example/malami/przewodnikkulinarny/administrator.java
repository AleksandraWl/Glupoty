package com.example.malami.przewodnikkulinarny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class administrator extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

    }

    public void dodajAdministratora(View view) {
        Intent i = new Intent(administrator.this, DodawanieAdministratora.class);
        startActivity(i);
    }

    public void usunAdministratora(View view) {
        Intent i = new Intent (administrator.this, UsunAdministratora.class);
        startActivity(i);
    }


    public void dodawanieKategorii(View view) {
            Intent i = new Intent(administrator.this, DodawanieRestauracji.class);
            startActivity(i);
        }


}



