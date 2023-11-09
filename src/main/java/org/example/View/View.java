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
 *
 * @author direc
 */
public class View {
    public View()
    {
        
    }
    public void welcomeMessage()
    {
        System.out.println("Welcome to pet clinic.");
    }
    
    public void MainMenu()
    {
        System.out.println("Main menu:\n"
                + "1 - Add new pet\n"
                + "2 - Find pet\n"
                + "3 - Create visit\n"
                + "4 - Search visit\n" +
                "9 - Exit");
    }
    public void printHealthOptions()
    {
        System.out.println("Health options:\n"
                + "1 - Healthy\n"
                + "2 - Sick\n"
                + "3 - N/A\n");
    }
    public void printException(Exception e)
    {
        System.out.println(e);
    }
    public void printNumericException(NumberFormatException e)
    {
        System.out.println(e);
    }
    public void printMessage(String s)
    {
        System.out.println(s);
    }

    public void printPet(Pet pet){
        System.out.println("Id:" + pet.getId() + "  Animal:" +
                pet.getAnimal() + "  Age:" + pet.getAge() +
                "  Health status:" + pet.getHealth());
    }

    public void petOptions()
    {
        System.out.println("What do you want to do with selected pet:\n"
                + "1 - Edit information\n"
                + "2 - Delete record\n"
                + "9 - Exit\n");
    }

    public void printVisit(Visit visit)
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        var date = visit.getTime();
        String formattedDate = date.format(myFormatObj);
        System.out.println("Id:" + visit.getId() + "  Date:" +
                formattedDate + "  Was held:" + visit.getHeld() +
                "  Cost:" + visit.getCost());
    }

    public void printMedicine(Medicine m)
    {
        System.out.println("Medicine name:" + m.getName() +
                " Quantity:" + m.getQuantity() +
                " Frequency:" + m.getFrequency());
    }
}
