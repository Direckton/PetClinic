/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.petclinicfxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author direc
 */
public class MainMenuController {


    @FXML
    private Label welcomeMessage;
    @FXML
    private Button addNewPet;
    @FXML
    private Button findPet;
    @FXML
    private Button createVisit;
    @FXML
    private Button searchVisit;
    @FXML
    private Button exit;

    public MainMenuController(){
        int i =0;
    }
    
    @FXML
    private void findPet(ActionEvent event)throws IOException {
        App.setRoot("FindPet");
    }    
    
}
