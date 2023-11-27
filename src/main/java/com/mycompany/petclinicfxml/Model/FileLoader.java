package com.mycompany.petclinicfxml.Model;




import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.util.Pair;

public class FileLoader {

    
    public FileLoader(){
    }

    /**
     * Creates file of specified name or opens it if it already exists.
     * @param path path of the file
     * @return file
     * @throws IOException
     */
    public File createFile(String path) throws IOException
    {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("Created new file " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists, opening:");
                file = openFile(path);
            }
            return file;
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * Opens and returns existing file
     * @param path path to the file
     * @return file or null if the file doesn't exist
     * @throws IOException
     */
    public File openFile(String path) throws IOException
    {
        try {
            File file = new File(path);

            if (file.isFile()) {
                return file;
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            throw new IOException(e);
        }

    }

    /**
     * Loads contents of the file to inventory
     * @param file opened file
     * @return inventory object
     */
    public ArrayList<Pet> readDbToPet(File file)
    {
        ArrayList<Pet> pets = new ArrayList<>();
        String[] pet = {""};
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine())
            {
                String data = fileReader.nextLine();
                //System.out.println(data);
                pet = data.split(",");
                int id = Integer.parseInt(pet[0]);
                String name = pet[1];
                int age = Integer.parseInt(pet[2]);
                String health = pet[3];
                pets.add(new Pet(id, name, age, Pet.Health.valueOf(health)));
            }
            return pets;
        }
        catch (IOException e)
        {
            return null;
        }
    }
    
    public HashMap<Integer,ArrayList<Visit>> readDbToVisit(File file) throws IOException{
        HashMap<Integer,ArrayList<Visit>> visits = new HashMap<>();
        HashMap<Pair<Integer,Integer>,ArrayList<Medicine>> medicines = new HashMap<>();
        medicines = readDbToMedicines(openFile("medicines.csv"));
        String[] visit = {""};
        try {
            Scanner fileReader = new Scanner(file);
            int petId=0;
            ArrayList<Visit> list= new ArrayList<>();
            while(fileReader.hasNextLine())
            {
                String data = fileReader.nextLine();
                //System.out.println(data);
                visit = data.split(",");
                if(petId == Integer.parseInt(visit[0]))
                {
                    petId = Integer.parseInt(visit[0]);
                    int visitId = Integer.parseInt(visit[1]);
                    //dd-MM-yyyy
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime date = LocalDateTime.parse(visit[2],formatter);
                    float cost = Float.parseFloat(visit[3]);
                    Boolean held = Boolean.parseBoolean(visit[4]);
                    var medicinesTmp = new ArrayList<Medicine>();
                    if(medicines.get(new Pair(petId, visitId))!=null)
                    {
                        medicinesTmp = medicines.get(new Pair(petId, visitId)); 
                    }
                    list.add(new Visit(visitId,date,cost,held,medicinesTmp));
                    
                }
                else{
                    visits.put(petId,new ArrayList<>(list));
                    list.clear();
                    petId = Integer.parseInt(visit[0]);
                    int visitId = Integer.parseInt(visit[1]);
                    //dd-MM-yyyy
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime date = LocalDateTime.parse(visit[2],formatter);
                    float cost = Float.parseFloat(visit[3]);
                    Boolean held = Boolean.parseBoolean(visit[4]);
                    list.add(new Visit(visitId,date,cost,held,new ArrayList<Medicine>(medicines.get(new Pair(petId, visitId)))));
                }

            }
            //add to map the last record
            visits.put(petId,new ArrayList<>(list));
            visits.remove(0);
        }
        catch (IOException e)
        {
            return null;
        }
        
        
        return visits;
    } 
    
    public HashMap<Pair<Integer,Integer>,ArrayList<Medicine>> readDbToMedicines(File file){
        HashMap<Pair<Integer,Integer>,ArrayList<Medicine>> medicines = new HashMap<>();
         String[] visit = {""};
        try {
            Scanner fileReader = new Scanner(file);
            int visitId=0;
            int petId = 0;
            Pair p = new Pair(petId,visitId);
            ArrayList<Medicine> list= new ArrayList<>();
            while(fileReader.hasNextLine())
            {
                String data = fileReader.nextLine();
                //System.out.println(data);
                visit = data.split(",");
                if(p.equals(new Pair(Integer.parseInt(visit[0]),Integer.parseInt(visit[1]))))
                {
                    petId = Integer.parseInt(visit[0]);
                    visitId = Integer.parseInt(visit[1]);
                    String name = visit[2];
                    float quantity = Float.parseFloat(visit[3]);
                    int frequency = Integer.parseInt(visit[4]);
                    list.add(new Medicine(name, quantity, frequency));
                    
                }
                else{
                    medicines.put(new Pair(petId,visitId),new ArrayList<>(list));
                    list.clear();
                    petId = Integer.parseInt(visit[0]);
                    visitId = Integer.parseInt(visit[1]);
                    String name = visit[2];
                    float quantity = Float.parseFloat(visit[3]);
                    int frequency = Integer.parseInt(visit[4]);
                    list.add(new Medicine(name, quantity, frequency));
                }

            }
            //add to map the last record
            medicines.put(new Pair(petId,visitId),new ArrayList<>(list));
            medicines.remove(new Pair(0,0));
        }
        catch (IOException e)
        {
            return null;
        }
        
        
        return medicines;
    }
    
    public ArrayList<Entry> readDbToRegistration() throws IOException
    {
        ArrayList<Entry> data = new ArrayList<>();
        ArrayList<Pet> pets = readDbToPet(openFile("pets.csv"));
        HashMap<Integer,ArrayList<Visit>> visits = readDbToVisit(openFile("visits.csv"));
        for(var p : pets)
        {
            ArrayList<Visit> visitsTmp = new ArrayList<>();
            if(visits.get(p.getId())!=null)
            {
                visitsTmp = visits.get(p.getId()); 
            }

            data.add(new Entry(p,visitsTmp));
        }
        return data;
    }
    
    public void saveToStrings(ArrayList<Entry> data){
        //Pet - ID, name, age, health
        ArrayList<ArrayList<String>> pets = new ArrayList<>();
        ArrayList<ArrayList<String>> visits = new ArrayList<>();
        ArrayList<ArrayList<String>> medicines = new ArrayList<>();
        for(var p : data)
        {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(String.valueOf(p.getPet().getId()));
            tmp.add(p.getPet().getAnimal());
            tmp.add(String.valueOf(p.getPet().getAge()));
            tmp.add(p.getPet().getStringHealth());
            pets.add(tmp);
            
            for(var v : p.getVists())
            {
                ArrayList<String> tmpV = new ArrayList<>();
                tmpV.add(String.valueOf(p.getPetId()));
                tmpV.add(String.valueOf(v.getId()));
                tmpV.add(v.getTime().toString());
                tmpV.add(String.valueOf(v.getCost()));
                tmpV.add(String.valueOf(v.getHeld()));
                visits.add(tmpV);
                for(var m : v.getMedicines())
                {
                    ArrayList<String> tmpM = new ArrayList<>();
                    tmpM.add(String.valueOf(p.getPetId()));
                    tmpM.add(String.valueOf(v.getId()));
                    tmpM.add(m.getName());
                    tmpM.add(String.valueOf(m.getQuantity()));
                    tmpM.add(String.valueOf(m.getFrequency()));
                    medicines.add(tmpM);
                }
            }
        }
        try
        {
            saveToFile(pets,openFile("pets.csv"));
            
        }
        catch (IOException e)
        {
            
        }

    }
    
    public void saveToFile(ArrayList<ArrayList<String>> data, File file)
    {
        String output ="";
        for(var i : data)
        {
            //System.out.println(i.size());
            int commas = 1;
            for(var j : i)
            {
                output += j;
                commas++;
                if(i.size() >= commas)
                {
                    output += ",";
                }
                if(i.size() < commas)
                {
                    output += "\n";
                }
            
            }
        }
        try {
        FileWriter writer = new FileWriter(file);
        writer.write(output);

        writer.close();
        System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) 
        {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
}
