/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;


import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author direc
 */
public class Registration {
    private HashMap<Pet,ArrayList<Visit>> entry;
    
    private ArrayList<Pet> petList;
    
    public Registration()
    {
        entry = new HashMap<Pet,ArrayList<Visit>>();
        petList = new ArrayList<Pet>();
    }
    
    public void addNewPet(int id, String animal, int age, String health)
    {
        try
        {
            for(Pet p : petList)
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
        petList.add(new Pet(id,animal,age, health));
        
    }
}
