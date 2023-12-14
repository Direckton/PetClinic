/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.sql.*;


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
                    + "(id INTEGER, date_ DATE, "
                    + "time_ TIME, cost FLOAT,"
                    + "petId INTEGER,"
                    + "PRIMARY KEY(id),"
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
    
}
