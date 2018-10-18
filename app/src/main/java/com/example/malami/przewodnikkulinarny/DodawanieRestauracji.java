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

    EditText Nazwa;
    EditText Adres;
    EditText Dlugosc;
    EditText Szerokosc;

    DatabaseReference restauracje;
    String nazwa;
    String adres;
    String dlugosc;
    String szerokosc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie_restauracji);

        Nazwa=findViewById(R.id.Nazwa);
        Adres=findViewById(R.id.adres);
        Dlugosc=findViewById(R.id.Dlugosc);
        Szerokosc=findViewById(R.id.Szerokosc);

        restauracje=FirebaseDatabase.getInstance().getReference();

    }

    public void dodajRestauracje(View view) {
        nazwa=Nazwa.getText().toString().trim();
        adres=Adres.getText().toString().trim();
        dlugosc=Dlugosc.getText().toString().trim();
        szerokosc=Szerokosc.getText().toString().trim();

        if(!TextUtils.isEmpty(nazwa)&&!TextUtils.isEmpty(adres)&&!TextUtils.isEmpty(dlugosc)&&!TextUtils.isEmpty(szerokosc))
        {
            restauracje res = new restauracje(nazwa,dlugosc,szerokosc,adres);
            String id= restauracje.push().getKey();
            restauracje.child(id).setValue(res);

            Toast.makeText(this, "Dodano", Toast.LENGTH_SHORT).show();
        }
        else {Toast.makeText(this, "Nie dodano", Toast.LENGTH_SHORT).show();}

    }
}
