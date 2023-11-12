/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.View;


import org.example.Model.Medicine;
import org.example.Model.Pet;
import org.example.Model.Visit;

import java.time.format.DateTimeFormatter;

/**
 * Class for displaying information in the console window
 * @author Michał Buczak
 */
public class View {
    public View() {}

    /**
     * Welcome statement run at the beginning of the program
     */
    public void welcomeMessage()
    {
        System.out.println("Welcome to pet clinic.");
    }

    /**
     * Main Menu options list
     */
    public void MainMenu()
    {
        System.out.println("Main menu:\n"
                + "1 - Add new pet\n"
                + "2 - Find pet\n"
                + "3 - Create visit\n"
                + "4 - Search visit\n" +
                "9 - Exit");
    }

    /**
     * Possible health options enumeration
     */
    public void printHealthOptions()
    {
        System.out.println("Health options:\n"
                + "1 - Healthy\n"
                + "2 - Sick\n"
                + "3 - N/A\n");
    }

    /**
     * Prints generic and user defined exceptions
     * @param e Exception type
     */
    public void printException(Exception e)
    {
        System.out.println(e);
    }

    /**
     * Prints NumberFormatException for wrong conversion from string to numeric formats
     * @param e NumberFormatException
     */
    public void printNumericException(NumberFormatException e)
    {
        System.out.println(e);
    }

    /**
     * Prints passed String
     * @param s
     */
    public void printMessage(String s)
    {
        System.out.println(s);
    }

    /**
     * Prints formatted Pet object
     * @param pet Pet object
     */
    public void printPet(Pet pet){
        System.out.println("Id:" + pet.getId() + "  Animal:" +
                pet.getAnimal() + "  Age:" + pet.getAge() +
                "  Health status:" + pet.getHealth());
    }

    /**
     * Prints list of options for found pet (look for it in the Controller)
     */
    public void petOptions()
    {
        System.out.println("What do you want to do with selected pet:\n"
                + "1 - Edit information\n"
                + "2 - Delete record\n"
                + "9 - Exit\n");
    }

    /**
     * Prints formatted Visit object
     * @param visit Visit object
     */
    public void printVisit(Visit visit)
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        var date = visit.getTime();
        String formattedDate = date.format(myFormatObj);
        System.out.println("Id:" + visit.getId() + "  Date:" +
                formattedDate + "  Was held:" + visit.getHeld() +
                "  Cost:" + visit.getCost());
    }

    /**
     * Prints formatted Medicine object
     * @param m Medicine Object
     */
    public void printMedicine(Medicine m)
    {
        System.out.println("Medicine name:" + m.getName() +
                " Quantity:" + m.getQuantity() +
                " Frequency:" + m.getFrequency());
    }
}
