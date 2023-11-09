/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;

/**
 *
 * @author direc
 */
public class Medicine {
    private String name;    //medicine name
    private float quantity; //in ml or g
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
