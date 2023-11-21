/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.petclinicfxml;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.mycompany.petclinicfxml.Model.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.mycompany.petclinicfxml.Model.*;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author direc
 */
public class FindPetController{


    @FXML
    private TableColumn<Pet,Integer> petId;
    @FXML
    private TableColumn<Pet,String> petName;
    @FXML
    private TableColumn<Pet,Integer> petAge;
    @FXML
    private TableColumn<Pet,Pet.Health> petHealth;
    @FXML
    private TextField searchParam;
    @FXML
    private Button findBtn;
    @FXML
    private Button back;
    @FXML
    private TableView table;
    
    
       
    private final ObservableList<Pet> data;    
    private final Registration registration;
    
    public FindPetController(Registration registration) {
        this.registration = registration;
        data = FXCollections.observableArrayList(registration.getPetData());
        data.addListener(new ListChangeListener<Pet>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Pet> change) {
                while (change.next()) {
                    if (change.wasPermutated()) {
                        for (int i = change.getFrom(); i < change.getTo(); ++i) {
                            System.out.println("zamiana");
                        }
                    } else if (change.wasUpdated()) {
                        System.out.println("uaktualnienie");
                    } else {
                        for (var remitem : change.getRemoved()) {
                            registration.getPetData().remove(remitem);
                        }
                        for (var additem : change.getAddedSubList()) {
                            registration.getPetData().add(additem);
                        }
                    }
                }
            }
        });
    }   
    
    @FXML
    public void initialize() {
        
        //petId = new TableColumn<>("Id");
        petId.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("id"));
        petName.setCellValueFactory(new PropertyValueFactory<Pet, String>("animal"));
        petAge.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("age"));
        petHealth.setCellValueFactory(new PropertyValueFactory<Pet, Pet.Health>("health"));
        
        table.setItems(data);
        
        
        table.setEditable(true);
        petName.setCellFactory(TextFieldTableCell.forTableColumn());	

        petName.setOnEditCommit(new EventHandler<CellEditEvent<Pet, String>>() {
            @Override
            public void handle(CellEditEvent<Pet, String> t) {
                ((Pet) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAnimal(t.getNewValue());
            }
        });
    }
    
    @FXML
    private void search(ActionEvent event) {
            
    }

    @FXML
    private void goBackToMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
        
    }

}
