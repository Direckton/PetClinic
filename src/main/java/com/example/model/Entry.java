package com.example.model;

import java.util.ArrayList;

public class Entry {
    private Pet pet;
    private ArrayList<Visit> visits;

    public Entry(Pet pet, ArrayList<Visit> visits)
    {
        this.pet = pet;
        this.visits = visits;
    }

    public Pet getPet() {
        return pet;
    }
    public int getPetId() {
        return pet.getId();
    }
    public ArrayList<Visit> getVists()
    {
        return visits;
    }
}
