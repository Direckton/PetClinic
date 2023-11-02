/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;


import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author direc
 */
public class Visit {
    private LocalDateTime time;
    private float cost;
    private ArrayList<Medicine> medicines;
    
    public Visit(LocalDateTime _time, float _cost, ArrayList<Medicine> _medicine)
    {
        this.time = _time;
        this.cost = _cost;
        this.medicines = _medicine;
    }
    
    
}
