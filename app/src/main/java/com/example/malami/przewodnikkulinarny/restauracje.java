package com.example.malami.przewodnikkulinarny;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

public class restauracje {
    String nazwa;
    String dlugosc;
    String szerokosc;
    String adres;
    public Map<String, Boolean> mapa = new HashMap<>();

    restauracje()
    {

    }
    restauracje(String nazwa, String dlugosc, String szerokosc, String adres)
    {
        this.nazwa = nazwa;
        this.dlugosc =dlugosc;
        this.szerokosc=szerokosc;
        this.adres=adres;
    }

    public String getSzerokosc() {
        return szerokosc;
    }

    public String getAdres() {
        return adres;
    }

    public String getDlugosc() {
        return dlugosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nazwa", nazwa);
        result.put("dlugosc", dlugosc);
        result.put("adres", adres);
        result.put("szerokosc", szerokosc);

        result.put("mapa", mapa);

        return result;
    }

}
