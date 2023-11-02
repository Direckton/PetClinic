/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.example.Model;

/**
 *
 * @author direc
 */
public class Pet {
    private int id;
    private String animal;
    private int age;
    private String health;
    
    public Pet(int _id, String _animal, int _age, String _health)
    {
        this.id = _id;
        this.animal = _animal;
        this.age= _age;
        this.health = _health;
    }
    
    public Pet getPet()
    {
        return this;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    
}
