package com.example.malami.przewodnikkulinarny;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsunAdministratora extends AppCompatActivity {

    Spinner spinner;
    FirebaseHelper helper;
    DatabaseReference baza;
    String spinnerItemText;
    Button usun;

    String genere;
    DatabaseReference MDR;
    private ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usun_administratora);
        baza = FirebaseDatabase.getInstance().getReference("Kategorie");
        usun = (Button) findViewById(R.id.usun);

        spinner = (findViewById(R.id.spinner));

        //helper = new FirebaseHelper(db);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListaAdministratorow()));


    }

    public ArrayList<String> ListaAdministratorow()
    {
        final ArrayList<String> lista=new ArrayList<>();
        lista.clear();
        lista.add("Administratorzy");


        baza.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Administratorzy(dataSnapshot,lista);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Administratorzy(dataSnapshot,lista);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return lista;
    }

    private void Administratorzy(DataSnapshot snapshot,ArrayList<String> lista)
    {
        //lista.clear();
        lista.add("Administratorzy");
        for (DataSnapshot ds:snapshot.getChildren())
        {
            String administrator=ds.getValue(admin.class).getEmail();
            lista.add(administrator);
        }
    }

    /*private ArrayList<String> fetchData()
    {
        //lista.clear();
        lista.add("Administratorzy");
        baza.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    admin Admin = ds.getValue(admin.class);
                    lista.add(Admin.getEmail());
                    //  Toast.makeText(this, ds+"", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return lista;

    }*/

}
