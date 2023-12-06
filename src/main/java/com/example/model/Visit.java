/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;



import java.time.LocalDateTime; // Import the LocalDateTime class
import java.util.ArrayList;

/**
 *
 * @author Michał Buczak
 */
public class Visit {
    /**
     * Uniqe id of the Visit object
     */
    int id;
    /**
     * Time of the visit in dd-MM-yyyy HH:mm format
     */
    private LocalDateTime time;
    /**
     * Price of the service, default is 0
     */
    private float cost;
    /**
     * Flag to signal if visit was held
     */
    private Boolean held;
    /**
     * Array of prescribed medicines, takes Medicine type
     */
    private ArrayList<Medicine> medicines;
    
    public Visit(int _id, LocalDateTime _time, float _cost, Boolean held, ArrayList<Medicine> _medicine)
    {
        this.id = _id;
        this.time = _time;
        this.cost = _cost;
        this.held = held;
        this.medicines = _medicine;
    }

    /**
     * Changes held flag to true
     */
    public void visitWasHeld()
    {
        held = true;
    }
    
    public void setHeld(Boolean status)
    {
        held = status;
    }
    
    public int getId()
    {
        return id;
    }

    public float getCost() {
        return cost;
    }
    
    public String getCostString() {
        return String.valueOf(cost);
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
    public String getDate() {
        return time.toString();
    }
    public void setDate(String s)
    {
        time = LocalDateTime.parse(s);
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
