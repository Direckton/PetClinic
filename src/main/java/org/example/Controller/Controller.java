/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Controller;

import org.example.Model.*;
import org.example.View.*;

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
        view.MainMenu();
        registration.addNewPet(1, "dog", 3, Pet.Health.SICK);
     }


}

class UserInput
{
    private Scanner input;
    private String data;
    public UserInput()
    {
        data = "";
        input = new Scanner(System.in);
    }

    public String getLine()
    {
        return input.nextLine();
    }
}
