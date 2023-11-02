/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Controller;

import org.example.Model.*;
import org.example.View.*;

/**
 *
 * @author direc
 */
public class Controller {
     Registration registration = new Registration();
     View view = new View();

     public Controller()
     {
         view.MainMenu();
        registration.addNewPet(1, "pies", 3, "chory");
        registration.addNewPet(1, "pies", 3, "chory");

     }
}
