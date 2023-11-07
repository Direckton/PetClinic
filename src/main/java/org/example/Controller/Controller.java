/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Controller;

import org.example.Model.*;
import org.example.View.*;

import javax.swing.*;
import java.net.InterfaceAddress;
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
             case 9:
                 view.printMessage("Exiting...");
                 return true;
             default:
         }
        return false;
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
                 if(s.ig("Y"));
                 {
                    registration.deleteRecord(p.getId());
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
    View v;
    public UserInput()
    {
        v = new View();
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
            v.printNumericException(e);
        }
        return number;
    }
    public Pet.Health getHealth(){
        v.printHealthOptions();
        switch (getNumber()){
            case 1:
                return Pet.Health.HEALTHY;
            case 2:
                return Pet.Health.SICK;
            case 3:
                return Pet.Health.NA;
            default:
                v.printMessage("Wrong input, please check options below.");
                break;
        }
        return null;
    }
}
