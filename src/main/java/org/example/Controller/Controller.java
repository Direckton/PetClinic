/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Controller;

import org.example.Model.*;
import org.example.View.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Controller dictating flow of the program
 * @author Michał Buczak
 */
public class Controller {
    /**
     * Registration object responsible for logic
     */
     private Registration registration;
    /**
     * View class responsible for showing information in console window
     */
     private View view;
    /**
     * UserInput class for getting input from user through console window
     */
     private UserInput input;

    /**
     * Constructor with initialized data, temporary solution until proper database is up
     * Contains infinite loop for running program
     * Exiting is achieved upon selecting correct switch from the control panel
     */
     public Controller()
     {
         //Initialization
         registration = new Registration();
         view = new View();
         input = new UserInput();

         registration.addNewPet(1,"Dog",3,Pet.Health.SICK);
         registration.addNewPet(2,"Cat",5,Pet.Health.HEALTHY);
         registration.addNewPet(3,"Turtle",2,Pet.Health.NA);
         registration.CreateVisit(LocalDateTime.of(2024,2,1,13,0),1);
         registration.CreateVisit(LocalDateTime.of(2024,2,7,13,45),1);
         registration.CreateVisit(LocalDateTime.of(2024,1,1,13,0),2);
         registration.CreateVisit(LocalDateTime.of(2024,1,2,13,0),2);
         registration.CreateVisit(LocalDateTime.of(2024,1,3,13,5),2);
         registration.CreateVisit(LocalDateTime.of(2024,2,1,13,0),2);
         registration.CreateVisit(LocalDateTime.of(2024,2,1,13,0),3);
         registration.CreateVisit(LocalDateTime.of(2024,3,1,14,20),3);
         registration.CreateVisit(LocalDateTime.of(2024,4,1,15,0),3);
         registration.addMedicine(1,registration.getVisits(1),new Medicine("Azaporc",20,2));
         registration.addMedicine(1,registration.getVisits(1),new Medicine("Betamox L.A.",10,1));
         registration.addMedicine(2,registration.getVisits(1),new Medicine("Betamox L.A.",10,1));
         registration.addMedicine(1,registration.getVisits(2),new Medicine("Otisur",1,1));
         registration.addMedicine(2,registration.getVisits(2),new Medicine("Hemosilate vet",15,2));
         registration.addMedicine(3,registration.getVisits(2),new Medicine("Scanopril Flavour",1,3));
         registration.addMedicine(4,registration.getVisits(2),new Medicine("Scanopril Flavour",1,3));
         registration.addMedicine(1,registration.getVisits(3),new Medicine("Azaporc",20,2));
         registration.addMedicine(1,registration.getVisits(3),new Medicine("Betamox L.A.",10,1));
         registration.addMedicine(1,registration.getVisits(3),new Medicine("Betamox L.A.",10,1));
         registration.addMedicine(2,registration.getVisits(3),new Medicine("Otisur",1,1));
         registration.addMedicine(3,registration.getVisits(3),new Medicine("Hemosilate vet",15,2));

         view.welcomeMessage();
         while(true)
         {
            view.MainMenu();
            if(option(input.getNumber())){
                break;
            }
         }

     }

    /**
     * Main menu selector panel, calls corresponding methods
     * @param select Integer selector
     * @return If true is returned, the main loop will be broken adn program will end
     */
     public Boolean option(int select)
     {
         switch(select){
             case 1:
                 //Add new pet
                 view.printMessage("Input unique ID");
                 int id = input.getNumber();
                 view.printMessage("Input animal name");
                 String name = input.getLine();
                 view.printMessage("Input animal age");
                 int age = input.getNumber();
                 registration.addNewPet(id,name,
                        age,input.getHealth());
                 break;
             case 2:
                 //Find pet by id
                 FindPet();
                 break;
             case 3:
                 //Create visit
                 view.printMessage("Input Pet id");
                 int petId = input.getNumber();
                 if(registration.checkValidId(petId))
                 {
                    registration.CreateVisit(input.getDate(), petId);
                 }
                 else {
                     view.printMessage("Id is incorrect.");
                 }
                 break;
             case 4:
                 //Register visit
                 view.printMessage("Input Pet id");
                 petId = input.getNumber();
                 if(registration.checkValidId(petId))
                 {
                     var visits = registration.getVisits(petId);
                     if(visits.isEmpty())
                     {
                         view.printMessage("No visits");
                         break;
                     }
                     for(var v : visits)
                     {
                         view.printVisit(v);
                     }
                     //Choose visit by its id
                     view.printMessage("Choose Id of the visit:");
                     int visitId = input.getNumber();
                     try{
                         registration.checkVisitId(visitId,visits);
                         visitOptions(visitId, visits);
                     }
                     catch (Exception e)
                     {
                         view.printException(e);
                     }

                 }
                 else {
                     view.printMessage("Invalid Id");
                 }
                 break;
             case 9:
                 view.printMessage("Exiting...");
                 return true;
             default:
         }
        return false;
     }

    /**
     * Visit options menu
     * @param visitId
     * @param visits array of visits
     */
     public void visitOptions(int visitId, ArrayList<Visit> visits){
         view.printMessage("What do you want to do with te visit?\n" +
                 "1 - Register\n" +
                 "2 - Service quote\n" +
                 "3 - Prescribe medicine\n" +
                 "4 - Print prescription");
         switch (input.getNumber()){
             case 1:
                 //Register visit
                 registration.registerVisit(visitId, visits);
                 break;
             case 2:
                 //Service quote
                 view.printMessage("Input price:");
                 registration.setQuote(visitId,visits,input.getPrice());
                 break;
             case 3:
                 registration.addMedicine(visitId,visits,input.getMedicine());
                 break;
             case 4:
                 registration.printPrescription(visitId,visits);
                 break;
             default:
                 break;
         }
     }

    /**
     * Methode used to handle finding pet by id for performing actions on its data
     * ID cannot be changed
     */
     public void FindPet()
     {
         view.printMessage("Insert id of the pet:");
        try {
            Pet p = registration.findPet(input.getNumber());
            if(p== null)
            {
                throw new Exception("The id is incorrect, exiting.");
            }
            view.printPet(p);
            view.petOptions();
            petOptions(input.getNumber(),p);
        }
        catch (Exception e)
        {
            view.printException(e);
        }
     }

    /**
     * Methode for editing pets information
     * @param selector INT switch for choosing action
     * @param p Pet object to be edited
     */
     public void editPet(int selector, Pet p)
     {
        switch (selector)
        {
            case 1:
                view.printMessage("Input new name");
                registration.editPet(input.getLine(),p);
                break;
            case 2:
                view.printMessage("Input new age");
                registration.editPet(input.getNumber(),p);
                break;
            case 3:
                view.printMessage("Input health status");
                registration.editPet(input.getHealth(),p);
                break;
            default:
                return;
        }
     }

    /**
     * Selector for action to be performed on pet object
     * @param selector INT selector for choosing action
     * @param p Pet object
     */
     public void petOptions(int selector, Pet p)
     {
         switch (selector)
         {
             case 1: //Edit pet
                 view.printMessage("What information do you want to edit?\n" +
                         "1 - Animal name\n" +
                         "2 - Age\n" +
                         "3 - Health status");
                editPet(input.getNumber(), p);
                break;
             case 2:    //Delete pet and its record
                 view.printMessage("Are you sure you want to delete record?\n" +
                         "Y - yes   N - No");
                 String s = input.getLine().toUpperCase();
                 if(s.equalsIgnoreCase("y"))
                 {
                    registration.deleteRecord(p.getId());
                    return;
                 }
                 view.printMessage("The record was NOT deleted");
                 break;
             default:
                 return;
         }
     }
}

/**
 * Class for taking in input form the user through console
 */
class UserInput
{
    /**
     * Object for handling input from the console
     */
    private Scanner input;
    /**
     * String of data input
     */
    private String data;
    /**
     * View class for displaying in console
     */
    private View view;
    public UserInput()
    {
        view = new View();
        data = "";
        input = new Scanner(System.in);
    }

    /**
     * Gets pure string
     * @return String
     */
    public String getLine()
    {
        return input.nextLine();
    }

    /**
     * Converts string to int with necessary checks
     * @return Integer
     */
    public int getNumber()
    {
        int number = 0;
        try{
            number = Integer.parseInt(input.nextLine());
        }
        catch(NumberFormatException e)
        {
            view.printNumericException(e);
        }
        return number;
    }

    /**
     * Converts string to float with necessary checks
     * @return float
     */
    public float getPrice()
    {
        float number = 0.f;
        try{
            number = Float.parseFloat(input.nextLine());
        }
        catch(NumberFormatException e)
        {
            view.printNumericException(e);
        }
        return number;
    }

    /**
     *  Gets health status from the user as enum type
     * @return Pet.Health enum or null
     */
    public Pet.Health getHealth(){
        view.printHealthOptions();
        switch (getNumber()){
            case 1:
                return Pet.Health.HEALTHY;
            case 2:
                return Pet.Health.SICK;
            case 3:
                return Pet.Health.NA;
            default:
                view.printMessage("Wrong input, please check options above.");
                break;
        }
        return null;
    }

    /**
     * Gets date from the user
     * @return LocalDateTime in format dd-MM-yyyy HH:mm or null
     */
    public LocalDateTime getDate()
    {
        int year, month, day, hour, minute;
        Boolean correct = false;
        while (!correct)
        {
            view.printMessage("Input year:");
            year = getNumber();
            view.printMessage("Input month:");
            month = getNumber();
            view.printMessage("Input day:");
            day = getNumber();
            view.printMessage("Input hour:");
            hour = getNumber();
            view.printMessage("Input minute:");
            minute = getNumber();

            try
            {
                LocalDateTime date = LocalDateTime.of(year,month,day,hour,minute);
                if(date.isAfter(LocalDateTime.now()))
                {
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    view.printMessage(date.format(myFormatObj));
                    return date;
                }
                view.printMessage("The date is incorrect, please insert it again");

            }
            catch (DateTimeException e)
            {
                view.printException(e);
            }
        }
        return null;
    }

    /**
     * Gets data from user to create Medicine object
     * @return Medicine object
     */
    public Medicine getMedicine()
    {
        view.printMessage("Input medicine name:");
        String name = getLine();
        view.printMessage("Input medicine quantity(ml/g/tablets):");
        float quantity = getPrice();
        view.printMessage("Input how frequent medicine should be taken");
        int frequency = getNumber();

        return new Medicine(name,quantity,frequency);
    }
}
