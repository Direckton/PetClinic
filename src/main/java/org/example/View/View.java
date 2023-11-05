/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.View;


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
                + "3 - Register visit\n"
                + "4 - Print prescription\n");
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
}
