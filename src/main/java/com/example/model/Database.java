/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author direc
 */
public class Database {
    
    private static Database db;
    
    public static Database getInstance(){
        if(db==null){
            db = new Database();
        }
        return db;
    }
    
    private Connection con;
    
    public Database()
    {
        connectToDB();
        createPetTables();
        createVisitTables();
        
    }
    
    public void connectToDB(){
        try{
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app");
        }catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            System.err.println("Couldn't connect to Database!");
        }
        
    }
    
    public void createPetTables() {

        // make a connection to DB
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE Pet "
                    + "(id INTEGER, animal VARCHAR(50), "
                    + "age INTEGER, health VARCHAR(50) CHECK (health in('NA','HEALTHY', 'SICK')) default 'NA',"
                    + "PRIMARY KEY(id))");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    public void createVisitTables() {

        // make a connection to DB
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE Visit "
                    + "(id INTEGER NOT NULL, date_ DATE, "
                    + "time_ TIME, cost FLOAT,"
                    + "held BOOLEAN,"
                    + "petId INTEGER,"
                    + "FOREIGN KEY (petId) REFERENCES Pet(id)"
                    + ")");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    public void query(String query)
    {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    public void insertData(String table, String values)
    {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO " + table
            + " VALUES (" + values + ")");
        }
        catch(SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    public void updatePet(Pet pet){
         try {
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            // Wysyłamy zapytanie do bazy danych
            ResultSet rs = statement.executeQuery("SELECT * FROM Pet WHERE id = " + pet.getId());

            //adding a new row
            rs.updateInt("id", pet.getId());
            rs.updateString("animal", pet.getAnimal());
            rs.updateInt("age", pet.getAge());
            rs.updateString("health", pet.getStringHealth());
            rs.updateRow();

            rs.close();
            System.out.println("Data updated");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    public ArrayList<Pet> getPetData()
    {
        ArrayList<Pet> pets = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM pet");
            // Przeglądamy otrzymane wyniki
            while (rs.next()) {
                int id = rs.getInt("id");
                String animal = rs.getString("animal");
                int age = rs.getInt("age");
                Pet.Health health =  Pet.Health.valueOf(rs.getString("health"));
                pets.add(new Pet(id,animal,age,health));
            }
            rs.close();
            return pets;
        }catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return null;
    }
    
    public ArrayList<Visit> getVisitData(Registration reg)
    {
        ArrayList<Visit> visits = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM visit");
            // Przeglądamy otrzymane wyniki
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date_");
                Time time = rs.getTime("time_");
                LocalDateTime dateTime =  LocalDateTime.of(date.toLocalDate(),time.toLocalTime());
                float cost = rs.getFloat("cost");
                boolean held = rs.getBoolean("held");
                int petId = rs.getInt("petId");
                Visit visit = new Visit(id,dateTime, cost, held, new ArrayList<Medicine>());
                reg.addNewVisit(visit, petId);
            }
            rs.close();
            return visits;
        }catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return null;
    }
    
}

