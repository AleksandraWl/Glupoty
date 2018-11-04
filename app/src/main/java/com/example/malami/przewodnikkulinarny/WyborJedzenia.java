package com.example.malami.przewodnikkulinarny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import okhttp3.internal.cache.DiskLruCache;


public class WyborJedzenia extends AppCompatActivity{


    Spinner spinner;
    //Button wyloguj;
    DatabaseReference db;

    final ArrayList<String> lista=new ArrayList<>();


    EditText NowyAdministrator;
    FirebaseAuth firebaseAuth;
    String genere;
    TextView adres;
    String dlugosc;
    String szerokosc;

    DatabaseReference mDatabase;
    DatabaseReference  lubnaRef;

    TextView d, s;

    MapsActivity mapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_jedzenia);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        lubnaRef = mDatabase.child("Restauracje");
        d=findViewById(R.id.dlugosc);
        s=findViewById(R.id.szerokosc);


        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = FirebaseDatabase.getInstance().getReference("Restauracje");
        adres= findViewById(R.id.Adres);


        spinner = (findViewById(R.id.spinner));

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fetchData()));

        genere = spinner.getSelectedItem().toString();


        }



    private ArrayList<String> fetchData()
    {
        lista.clear();
        lista.add("Restauracje");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    restauracje res = ds.getValue(restauracje.class);

                    lista.add(res.getNazwa());
                    //  Toast.makeText(this, ds+"", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return lista;

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.wyloguj:
                Intent i = new Intent(WyborJedzenia.this, Logowanie.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();

        }

        return super.onOptionsItemSelected(item);
    }


    public void zatwierdz(View view) {
        genere = spinner.getSelectedItem().toString();

        lubnaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //These are all of your children.
                Map<String, Object> lubna = (Map<String, Object>) dataSnapshot.getValue();

                for (String childKey : lubna.keySet()) {
                    if(childKey.equals(genere)) {
                        //childKey is your "-LQka.. and so on"
                        //Your current object holds all the variables in your picture.
                        Map<String, Object> currentLubnaObject = (Map<String, Object>) lubna.get(childKey);

                        String adresRes = (String) currentLubnaObject.get("adres");
                        adres.setText(adresRes);

                    }
                    //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void mapa(View view) {
        genere = spinner.getSelectedItem().toString();
        lubnaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //These are all of your children.
                Map<String, Object> lubna = (Map<String, Object>) dataSnapshot.getValue();

                for (String childKey : lubna.keySet()) {
                    if(childKey.equals(genere)) {
                        //childKey is your "-LQka.. and so on"
                        //Your current object holds all the variables in your picture.
                        Map<String, Object> currentLubnaObject = (Map<String, Object>) lubna.get(childKey);

                        szerokosc = (String) currentLubnaObject.get("szerokosc");
                        dlugosc = (String) currentLubnaObject.get("dlugosc");
                    }
                    //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                }

                Double f_szerokosc=Double.parseDouble(szerokosc);
                Double f_dlugosc=Double.parseDouble(dlugosc);
d.setText("Dlugosc: "+f_dlugosc);
s.setText("Szerokosc: "+ f_szerokosc);
//mapa.DodajPunkt(f_dlugosc,f_szerokosc,genere);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /*Intent i = new Intent(WyborJedzenia.this, MapsActivity.class);
       startActivity(i);*/
    }

//

}

