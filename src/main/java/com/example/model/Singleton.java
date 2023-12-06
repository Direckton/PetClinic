/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author direc
 */
public final class Singleton {
    private static Registration registration;
    
    public static Registration getInstance()
    {
        if(registration == null){
            registration = new Registration();
        }
        return registration;
    }
}
