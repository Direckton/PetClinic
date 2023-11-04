/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import org.example.Model.Pet;

/**
 *
 * @author direc
 */
public class Registration {
    private HashMap<Pet,ArrayList<Visit>> entry;
    
    public Registration()
    {
        entry = new HashMap<Pet,ArrayList<Visit>>();
    }
    
    public void addNewPet(int id, String animal, int age, Pet.Health health)
    {
        try
        {
            for(Pet p : entry.keySet())
            {
                if(p.getId() == id)
                {
                    //id already exist
                    throw new Exception("Id already exist, select diffrent one");
                }
            }
    
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        entry.put(new Pet(id,animal,age,health),null);
        
    }
    public void addNewRecord(Pet pet, ArrayList<Visit> visits)
    {
        entry.put(pet,visits);
    }
}
