/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;


import java.time.LocalDateTime; // Import the LocalDateTime class
import java.util.ArrayList;

/**
 *
 * @author direc
 */
public class Visit {
    int id;
    private LocalDateTime time;
    private float cost;
    private Boolean held;
    private ArrayList<Medicine> medicines;
    
    public Visit(int _id, LocalDateTime _time, float _cost, ArrayList<Medicine> _medicine)
    {
        this.id = _id;
        this.time = _time;
        this.cost = _cost;
        this.held = false;
        this.medicines = _medicine;
    }

    public void visitWasHeld()
    {
        held = true;
    }
    
    public int getId()
    {
        return id;
    }

    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    public Boolean getHeld() {
        return held;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void addMedicine(Medicine m)
    {
        medicines.add(m);
    }

    public ArrayList<Medicine> getMedicines()
    {
        return medicines;
    }
}
