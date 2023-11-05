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
     Registration registration = new Registration();
     View view = new View();
     UserInput input = new UserInput();

     public Controller()
     {
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
             case 9:
                 view.printMessage("Exiting...");
                 return true;
             default:
         }
        return false;
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
