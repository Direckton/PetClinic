/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

//import org.example.View.View;

/**
 * Class used as a main logic block of the clinic
 * Implements operations on Pets and Visits
 * @author Michał Buczak
 */
public final class Registration {
    /**
     * Stores all records of pets and its visits
     */
    //private HashMap<Pet,ArrayList<Visit>> entry;
    private CopyOnWriteArrayList<Entry> data;
    /**
     * Object used to display data in console
     */
    //private View view;
    
    private static Registration reg;
    
    public static Registration getInstance(){
        if(reg==null){
            reg = new Registration();
        }
        return reg;
    }
    
    
    public Registration()
    {
        //entry = new HashMap<Pet,ArrayList<Visit>>();
        data = new CopyOnWriteArrayList<>();
//
//        FileLoader file = new FileLoader();
//        try{
//            data = file.readDbToRegistration();
//        }
//        catch(IOException e)
//        {
//            
//        }

//        data.add(new Entry(new Pet(1,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(2,"Dogo",3,Pet.Health.SICK),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(3,"Horse",12,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(4,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        CreateVisit(LocalDateTime.of(2024,2,1,13,0),1, true);
//        CreateVisit(LocalDateTime.of(2024,2,7,13,45),1);
//        CreateVisit(LocalDateTime.of(2024,1,1,13,0),2);
//        CreateVisit(LocalDateTime.of(2024,1,2,13,0),2);
//        CreateVisit(LocalDateTime.of(2024,1,3,13,5),2);
//        CreateVisit(LocalDateTime.of(2024,2,1,13,0),2);
//        CreateVisit(LocalDateTime.of(2024,2,1,13,0),3);
//        CreateVisit(LocalDateTime.of(2024,3,1,14,20),3);
//        CreateVisit(LocalDateTime.of(2024,4,1,15,0),3);
//        addMedicine(1,getVisits(1),new Medicine("Azaporc",20,2));
//        addMedicine(1,getVisits(1),new Medicine("Betamox L.A.",10,1));
//        addMedicine(2,getVisits(1),new Medicine("Betamox L.A.",10,1));
//        addMedicine(1,getVisits(2),new Medicine("Otisur",1,1));
//        addMedicine(2,getVisits(2),new Medicine("Hemosilate vet",15,2));
//        addMedicine(3,getVisits(2),new Medicine("Scanopril Flavour",1,3));
//        addMedicine(4,getVisits(2),new Medicine("Scanopril Flavour",1,3));
//        addMedicine(1,getVisits(3),new Medicine("Azaporc",20,2));
//        addMedicine(1,getVisits(3),new Medicine("Betamox L.A.",10,1));
//        addMedicine(1,getVisits(3),new Medicine("Betamox L.A.",10,1));
//        addMedicine(2,getVisits(3),new Medicine("Otisur",1,1));
//        addMedicine(3,getVisits(3),new Medicine("Hemosilate vet",15,2));       
//        data.add(new Entry(new Pet(5,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(6,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(7,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(8,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(9,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(10,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(11,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
//        data.add(new Entry(new Pet(12,"Cat",2,Pet.Health.HEALTHY),new ArrayList<Visit>()));
        //view = new View();
    }
    
    
    public CopyOnWriteArrayList<Entry> getData(){
        return data;
    }
    
    public ArrayList<Pet> getPetData(){
       ArrayList<Pet> temp = new ArrayList<>();
        data.forEach((p) -> temp.add(p.getPet()));
        return temp;
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
            //entry.put(new Pet(id,animal,age,health),new ArrayList<>());
            data.add(new Entry(new Pet(id,animal,age,health),new ArrayList<>()));

        }
        catch(Exception e)
        {
            //view.printException(e);
        }

    }
    public void addNewPet(Pet pet){
        data.add(new Entry(pet,new ArrayList<>()));
    }
    
    public void addNewVisit(Visit visit, int petId)
    {
        for(var p :data)
        {
            if(p.getPetId() == petId)
            {
                p.getVists().add(visit);
            }
        }
    }
    public void deleteVisit(int visitId, int petId)
    {
        for(var p :data)
        {
            if(p.getPetId() == petId)
            {
                for( var v : p.getVists())
                {
                    if(v.getId() == visitId)
                    {
                        p.getVists().remove(v); 
                    }
                }
            }
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
/*        for(Pet p : entry.keySet())
        {
            if(p.getId() == id)
            {
                //id already exist
                return true;
            }
        }*/
        for(Entry r : data)
        {
            if(r.getPet().getId() == id)
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
        /*for (Pet p : entry.keySet())
        {
            if (p.getId() == id)
            {
                return p;
            }
        }*/
        for(Entry r : data)
        {
            if(r.getPet().getId() == id)
            {
                //id already exist
                return r.getPet();
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
//        entry.put(pet,visits);
        data.add(new Entry(pet,visits));
    }

    /**
     * Finds and edits Pet name
     * @param name new name
     * @param pet object to be modified
     */
    public void editPet(String name, Pet pet){
//        for(Pet p : entry.keySet()){
//            if(pet.getId() == p.getId())
//            {
//                p.setAnimal(name);
//            }
//        }
        for(Entry r : data){
            if(pet.getId() == r.getPet().getId())
            {
                r.getPet().setAnimal(name);
            }
        }
    }

    /**
     * Finds and edits Pets age
     * @param age new age
     * @param pet object to be modified
     */
    public void editPet(int age, Pet pet){
//        for(Pet p : entry.keySet()){
//            if(pet.getId() == p.getId())
//            {
//                p.setAge(age);
//            }
//        }
        for(Entry r : data){
            if(pet.getId() == r.getPet().getId())
            {
                r.getPet().setAge(age);
            }
        }
    }

    /**
     * Finds and edits Pets health status
     * @param health new status
     * @param pet object to be modified
     */
    public void editPet(Pet.Health health, Pet pet){
//        for(Pet p : entry.keySet()){
//            if(pet.getId() == p.getId())
//            {
//                p.setHealth(health);
//            }
//        }
        for(Entry r : data){
            if(pet.getId() == r.getPet().getId())
            {
                r.getPet().setHealth(health);
            }
        }
    }

    /**
     * Delete Pet and its Visits by pets id
     * @param id
     */
    public void deleteRecord(int id)
    {
//        for(Pet p : entry.keySet()){
//            if(id == p.getId())
//            {
//                entry.remove(p);
//            }
//        }
        for(Entry r : data){
            if(id == r.getPet().getId())
            {
                data.remove(r);
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
//        var e = entry.get(findPet(petId));
//
//        e.add(new Visit(visitId+1,date,0.0f, new ArrayList<Medicine>()));

        var visitId = 0;
        for (Entry r : data) {
            if(r.getPet().getId()==petId)
            {
                var e=r.getVists();
                if(e.size() > 0)
                {
                    visitId = e.size();
                }
                e.add(new Visit(visitId+1,date,0.0f,false, new ArrayList<Medicine>()));
                break;
            }
        }
    }
    public void CreateVisit(LocalDateTime date, int petId, Boolean held)
    {
//        var e = entry.get(findPet(petId));
//
//        e.add(new Visit(visitId+1,date,0.0f, new ArrayList<Medicine>()));

        var visitId = 0;
        for (Entry r : data) {
            if(r.getPet().getId()==petId)
            {
                var e=r.getVists();
                if(e.size() > 0)
                {
                    visitId = e.size();
                }
                e.add(new Visit(visitId+1,date,0.0f,held, new ArrayList<Medicine>()));
                break;
            }
        }
    }

    /**
     * Returns all the visits specified pet has been registered to
     * @param petId
     * @return Array of Visits
     */
    public ArrayList<Visit> getVisits(int petId)
    {
        //return entry.get(findPet(petId));

        for (Entry r : data) {
            if(r.getPet().getId() == petId)
            {
                return r.getVists();
            }
        }
        return null;
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
                    //view.printMessage("No prescribed medicines");
                    return;
                }
                for (var m : medicines)
                {
                    //view.printMedicine(m);
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
//
//    public void saveDB(){
//        FileLoader file = new FileLoader();
//        file.saveToStrings(data);
//    }

}
