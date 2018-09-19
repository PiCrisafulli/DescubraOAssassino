package com.example.pi.descubraoassassino.domain;

import java.util.ArrayList;

public class Places {

    public ArrayList<Place> places;

    public Places (){
        places = new ArrayList<>();
    }
    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place>  places) {
        this.places = places;
    }
}
