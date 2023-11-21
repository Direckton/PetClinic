/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.petclinicfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author direc
 */
public class CalcController {

    @FXML
    private Label result;
    @FXML
    private TextField arg1;
    @FXML
    private TextField arg2;
    @FXML
    private Button button;

     

    @FXML
    private void add(ActionEvent event) {
     double x = Double.parseDouble(arg1.getText());
     double y = Double.parseDouble(arg2.getText());
     result.setText("" + (x+y));
    }
    
    
    
}
