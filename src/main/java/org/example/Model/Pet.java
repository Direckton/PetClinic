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
    public enum Health{
        HEALTHY,
        SICK,
        NA
    }
    private int id;
    private String animal;
    private int age;
    private Health health;
    
    public Pet(int _id, String _animal, int _age, Health _health)
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
    public void setHealth(Health h)
    {
        this.health = h;
    }
    public int getAge()
    {
        return this.age;
    }
    public Health getHealth()
    {
        return this.health;
    }

    
}
