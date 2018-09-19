package com.example.pi.descubraoassassino.domain;

import java.util.ArrayList;

public class Weapons {

    public ArrayList<Weapon> weapons;

    public Weapons(){
        weapons = new ArrayList<>();
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }
}
