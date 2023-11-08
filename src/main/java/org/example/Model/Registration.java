/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;


import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.example.Model.Pet;
import org.example.View.View;

/**
 *
 * @author direc
 */
public class Registration {
    private HashMap<Pet,ArrayList<Visit>> entry;
    private View view;
    
    public Registration()
    {
        entry = new HashMap<Pet,ArrayList<Visit>>();
        view = new View();
    }
    
    public void addNewPet(int id, String animal, int age, Pet.Health health)
    {
        try
        {
            //Check id
            if(checkValidId(id)){
                throw new Exception("Id already exist or is invalid, try again");
            }
            //Check name
            if(checkAnimalName(animal)){
                throw new Exception("Animal must be specified");
            }
            entry.put(new Pet(id,animal,age,health),new ArrayList<>());

        }
        catch(Exception e)
        {
            view.printException(e);
        }

    }
    public Boolean checkValidId(int id)
    {
        if(id == 0)
        {
            //invalid input
            return true;
        }
        for(Pet p : entry.keySet())
        {
            if(p.getId() == id)
            {
                //id already exist
                return true;
            }
        }
        //all good
        return false;
    }
    public Boolean checkAnimalName(String s){
        if(s.isEmpty()){
            return true;
        }
        return false;
    }
    public Pet findPet(int id)
    {
        for (Pet p : entry.keySet())
        {
            if (p.getId() == id)
            {
                return p;
            }
        }
        return null;
    }
    public void addNewRecord(Pet pet, ArrayList<Visit> visits)
    {
        entry.put(pet,visits);
    }
    public void editPet(String name, Pet pet){
        for(Pet p : entry.keySet()){
            if(pet.getId() == p.getId())
            {
                p.setAnimal(name);
            }
        }
    }
    public void editPet(int age, Pet pet){
        for(Pet p : entry.keySet()){
            if(pet.getId() == p.getId())
            {
                p.setAge(age);
            }
        }
    }
    public void editPet(Pet.Health health, Pet pet){
        for(Pet p : entry.keySet()){
            if(pet.getId() == p.getId())
            {
                p.setHealth(health);
            }
        }
    }
    public void deleteRecord(int id)
    {
        for(Pet p : entry.keySet()){
            if(id == p.getId())
            {
                entry.remove(p);
            }
        }
    }
    public void registerVisit(LocalDateTime date)
    {
        LocalDateTime myDate = LocalDateTime.now();
        var e = entry.get(findPet(2));
        e.add(new Visit(myDate,30.0f, new ArrayList<Medicine>()));
    }
}
