package com.example.pi.descubraoassassino.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class Weapon {

    public int id;
    public String Nome;

    public Weapon(JSONObject object){
        try {
            this.setId(Integer.parseInt(object.has("Id") ? object.getString("Id") : null));
            this.setNome((object.has("Nome") ? object.getString("Nome") : null));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
