/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Controller;

import org.example.Model.*;
import org.example.View.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author direc
 */
public class Controller {
     Registration registration;
     View view;
     UserInput input;

     public Controller()
     {
         //Initialization
         registration = new Registration();
         view = new View();
         input = new UserInput();

         registration.addNewPet(1,"Dog",3,Pet.Health.SICK);
         registration.addNewPet(2,"Cat",5,Pet.Health.HEALTHY);
         registration.addNewPet(3,"Turtle",2,Pet.Health.NA);
         registration.CreateVisit(LocalDateTime.of(2024,1,1,13,0),2);
         registration.CreateVisit(LocalDateTime.of(2024,1,2,13,0),2);
         registration.CreateVisit(LocalDateTime.of(2024,1,3,13,0),2);
         registration.CreateVisit(LocalDateTime.of(2024,2,1,13,0),2);

         view.welcomeMessage();
         while(true)
         {
            view.MainMenu();
            if(option(select())){
                break;
            }
         }

     }
     public int select()
     {
         int choice = 0;
         String value ="";
         try{
            value = input.getLine();
            if(!value.isEmpty())
            {
                choice = Integer.parseInt(value);
            }
         }
         catch (NumberFormatException e)
         {
            view.printNumericException(e);
         }
        return choice;
     }
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
                     input.getNumber();

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

     public void visitOptions(Visit selector){
         view.printMessage("What do you want to do with te visit?\n" +
                 "1 - Animal name\n" +
                 "2 - Age\n" +
                 "3 - Health status");
     }
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

class UserInput
{
    private Scanner input;
    private String data;
    View view;
    public UserInput()
    {
        view = new View();
        data = "";
        input = new Scanner(System.in);
    }

    public String getLine()
    {
        return input.nextLine();
    }
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
                view.printMessage("Wrong input, please check options below.");
                break;
        }
        return null;
    }
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

            LocalDateTime date = LocalDateTime.of(year,month,day,hour,minute);

            if(date.isAfter(LocalDateTime.now()))
            {
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                view.printMessage(date.format(myFormatObj));
                return date;
            }
            view.printMessage("The date is incorrect, please insert it again");
        }
        return null;
    }
}
