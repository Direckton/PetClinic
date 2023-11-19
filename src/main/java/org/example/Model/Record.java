package org.example.Model;

import java.util.ArrayList;

public class Record {
    private Pet pet;
    private ArrayList<Visit> visits;

    public Record(Pet pet, ArrayList<Visit> visits)
    {
        this.pet = pet;
        this.visits = visits;
    }

    public Pet getPet() {
        return pet;
    }
    public ArrayList getVists()
    {
        return visits;
    }
}
