/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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
    public void CreateVisit(LocalDateTime date, int petId)
    {
        var e = entry.get(findPet(petId));
        var visitId = 0;
        if(e.size() > 0)
        {
            visitId = e.get(e.size()-1).getId();
        }
        e.add(new Visit(visitId+1,date,0.0f, new ArrayList<Medicine>()));
    }

    public ArrayList<Visit> getVisits(int petId)
    {
        return entry.get(findPet(petId));
    }
    public void registerVisit(int id, ArrayList<Visit> visits)
    {
        for(var v : visits)
        {
            if(v.getId() == id)
            {
                v.visitWasHeld();
                return;
            }
        }
    }
    public void setQuote(int id, ArrayList<Visit> visits, float price)
    {
        for(var v : visits)
        {
            if(v.getId() == id)
            {
                v.setCost(price);
                return;
            }
        }
    }
    public void addMedicine(int VisitId, ArrayList<Visit> visits, Medicine m)
    {
        for(var v : visits)
        {
            if(v.getId() == VisitId)
            {
                v.addMedicine(m);
                return;
            }
        }
    }
    public void printPrescription(int VisitId, ArrayList<Visit> visits)
    {
        for(var v : visits)
        {
            if(v.getId() == VisitId)
            {
                var medicines = v.getMedicines();
                if(medicines.isEmpty())
                {
                    view.printMessage("No prescribed medicines");
                    return;
                }
                for (var m : medicines)
                {
                    view.printMedicine(m);
                }
                return;
            }
        }
    }
    public void checkVisitId(int visitId, ArrayList<Visit> visits) throws Exception
    {
        for (var v : visits)
        {
            if(visitId == v.getId())
            {
                return;
            }
        }
        throw new Exception("Visit with id:" + visitId + " doesn't exist");
    }
}
