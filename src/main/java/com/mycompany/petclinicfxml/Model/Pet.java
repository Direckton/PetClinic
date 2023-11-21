/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.petclinicfxml.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;


/**
 * Class defining pet with getters and setters
 * @author Michał Buczak
 */

public class Pet {
    /**
     * Enumeration used to describe pet's state of health
     */
    public enum Health{
        HEALTHY,
        SICK,
        NA
    }

    /**
     * Unique id of the pet in database used to search it
     */
    private int id;
    /**
     * Name od the animal (i.e. Dog, Cat, Horse)
     */
    private String animal;
    /**
     * Age of the animal in years
     */
    private int age;
    /**
     * Current health state
     */
    private Health health;
    
    
    public Pet(int _id, String _animal, int _age, Health _health)
    {
        this.id = _id;
        this.animal = _animal;
        this.age= _age;
        this.health = _health;
    }

    /**
     * Pet object getter
     * @return Object of the pet
     */
    public Pet getPet()
    {
        return this;
    }

    public int getId()
    {
        return this.id;
    }
    public String getAnimal()
    {
        return this.animal;
    }
    public void setAnimal(String name)
    {
        this.animal = name;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public void setAge(String age)
    {
        this.age = Integer.parseInt(age);
    }
    public void setHealth(Health h)
    {
        this.health = h;
    }
    public String getAge()
    {
        return String.valueOf(this.age);
    }
    public Health getHealth()
    {
        return this.health;
    }
    public String getStringHealth()
    {
        return this.health.name();
    }
    
}
