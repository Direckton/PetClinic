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
 * Class used as a main logic block of the clinic
 * Implements operations on Pets and Visits
 * @author Michał Buczak
 */
public class Registration {
    /**
     * Stores all records of pets and its visits
     */
    private HashMap<Pet,ArrayList<Visit>> entry;
    /**
     * Object used to display data in console
     */
    private View view;
    
    public Registration()
    {
        entry = new HashMap<Pet,ArrayList<Visit>>();
        view = new View();
    }

    /**
     * Performs checks and if everything is alright adds pet to collection
     * @param id
     * @param animal Animal name
     * @param age age in years
     * @param health Enum health status
     */
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

    /**
     * Checks if id is other than 0 and is unique
     * @param id
     * @return false if all checks were correct
     */
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

    /**
     * Checks if animal name is specified
     * @param s
     * @return  false if everything is correct
     */
    public Boolean checkAnimalName(String s){
        if(s.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Returns Pet object by Id parameter
     * @param id
     * @return Pet object / null if no pet was found
     */
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

    /**
     * Creates new record in Hashmap
     * @param pet Pet object
     * @param visits Array of Visit type
     */
    public void addNewRecord(Pet pet, ArrayList<Visit> visits)
    {
        entry.put(pet,visits);
    }

    /**
     * Finds and edits Pet name
     * @param name new name
     * @param pet object to be modified
     */
    public void editPet(String name, Pet pet){
        for(Pet p : entry.keySet()){
            if(pet.getId() == p.getId())
            {
                p.setAnimal(name);
            }
        }
    }

    /**
     * Finds and edits Pets age
     * @param age new age
     * @param pet object to be modified
     */
    public void editPet(int age, Pet pet){
        for(Pet p : entry.keySet()){
            if(pet.getId() == p.getId())
            {
                p.setAge(age);
            }
        }
    }

    /**
     * Finds and edits Pets health status
     * @param health new status
     * @param pet object to be modified
     */
    public void editPet(Pet.Health health, Pet pet){
        for(Pet p : entry.keySet()){
            if(pet.getId() == p.getId())
            {
                p.setHealth(health);
            }
        }
    }

    /**
     * Delete Pet and its Visits by pets id
     * @param id
     */
    public void deleteRecord(int id)
    {
        for(Pet p : entry.keySet()){
            if(id == p.getId())
            {
                entry.remove(p);
            }
        }
    }

    /**
     * Creates new Visit in array for specified pet by its id
     * @param date date ov the visit as LocalDateTime obj, must be in the future
     * @param petId
     */
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

    /**
     * Returns all the visits specified pet has been registered to
     * @param petId
     * @return Array of Visits
     */
    public ArrayList<Visit> getVisits(int petId)
    {
        return entry.get(findPet(petId));
    }

    /**
     * Sets flag to true
     * @param id Visit id
     * @param visits Visit array
     */
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

    /**
     * Sets quote for the service
     * @param id Visit id
     * @param visits Visits array
     * @param price new price
     */
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

    /**
     * Adds new Medicine Object to Medicines array in Visit
     * @param VisitId
     * @param visits Visits array
     * @param m Medicine object
     */
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

    /**
     * Prints specified visits prescription
     * @param VisitId
     * @param visits Visits array
     */
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

    /**
     * Checks if visit is exists, if not throws exception
     * @param visitId
     * @param visits Visits array
     * @throws Exception
     */
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
