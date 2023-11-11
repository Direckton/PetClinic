/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;

/**
 * Defines medicine
 * @author direc
 */
public class Medicine {
    /**
     * Name of the medicine
     */
    private String name;    //medicine name
    /**
     * Quantity in ml/g/tablets
     */
    private float quantity; //in ml or g
    /**
     * How frequent the medicine should be administrated
     */
    private int frequency;  //times a day

    public Medicine(String _name, float _quantity, int _frequency)
    {
        this.name = _name;
        this.quantity = _quantity;
        this.frequency = _frequency;
    }


    public String getName() {
        return name;
    }

    public float getQuantity() {
        return quantity;
    }

    public int getFrequency() {
        return frequency;
    }
}
