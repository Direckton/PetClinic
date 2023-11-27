/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.petclinicfxml;

import javafx.util.StringConverter;
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
import java.util.Comparator;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.animation.PauseTransition;



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
    private TableColumn<Pet,String> petAge;
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
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button visitsBtn;
    
    public FindPetController(Registration registration) {
        this.registration = registration;
        data = FXCollections.observableArrayList(registration.getPetData());
        data.addListener(new ListChangeListener<Pet>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Pet> change) {
                while (change.next()) {
                    if (change.wasPermutated()) {
                        for (int i = change.getFrom(); i < change.getTo(); ++i) {

                        }
                    } else if (change.wasUpdated()) {

                    } else {
                        for (var remitem : change.getRemoved()) {
                            registration.deleteRecord(remitem.getId());
                        }
                        for (var additem : change.getAddedSubList()) {
                            registration.addNewPet(additem);
                            //registration.getPetData().add(additem);
                        }
                    }
                }
            }
        });
    }   
    
    public void initialize() {
        
        //petId = new TableColumn<>("Id");
        petId.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("id"));
        petName.setCellValueFactory(new PropertyValueFactory<Pet, String>("animal"));
        petAge.setCellValueFactory(new PropertyValueFactory<Pet, String>("age"));
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
        petAge.setCellFactory(TextFieldTableCell.forTableColumn());	

        petAge.setOnEditCommit(new EventHandler<CellEditEvent<Pet, String>>() {
            @Override
            public void handle(CellEditEvent<Pet, String> t) {
                ((Pet) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAge(t.getNewValue());
            }
        });
        
        petHealth.setEditable(true);
        
        petHealth.setCellFactory(ComboBoxTableCell.forTableColumn(Pet.Health.values()));

        
        petHealth.setOnEditCommit(new EventHandler<CellEditEvent<Pet, Pet.Health>>() {
            @Override
            public void handle(CellEditEvent<Pet, Pet.Health> event) {
                TableView<Pet> table = event.getTableView();
                Pet pet = table.getItems().get(event.getTablePosition().getRow());
                pet.setHealth(event.getNewValue());
            }
        });
            
    }
    
    @FXML
    private void search(ActionEvent event) {
        String searchValue = searchParam.getText().trim();

        if (!searchValue.isEmpty()) {
            // Convert the search input to an integer
            try {
                int searchId = Integer.parseInt(searchValue);
                highlightRowById(searchId);
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                // You can display an error message or take other appropriate actions
            }
        }
    }
   
    private void highlightRowById(int id) {
        TableView<Pet> tableView = table;
        ObservableList<TableColumn<Pet, ?>> columns = tableView.getColumns();

        for (int i = 0; i < tableView.getItems().size(); i++) {
            Pet pet = tableView.getItems().get(i);

            if (pet.getId() == id) {
                // Highlight the row
                tableView.requestFocus();
                tableView.getSelectionModel().select(i);
                tableView.scrollTo(i);

            }
        }
    }
    
    private int getHighlightedCell() {
        TableView<Pet> tableView = table;
        ObservableList<TableColumn<Pet, ?>> columns = tableView.getColumns();

        Pet pet = tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex());
        return pet.getId();
    }
    

    @FXML
    private void goBackToMenu(ActionEvent event) throws IOException {
        registration.saveDB();
        App.setRoot("MainMenu");
        
    }

    @FXML
    private void Add(ActionEvent event) {
        
        Comparator<Pet> petComparator = Comparator.comparing(Pet::getId);
        data.sort(petComparator);
        data.add(new Pet(data.get(data.size()-1).getId()+1,"",0,Pet.Health.NA));
    }
    
    @FXML
    private void Delete(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index != -1) data.remove(index);
    }

    @FXML
    private void openVisits(ActionEvent event) throws IOException {
        
        App.setPetId(getHighlightedCell());
        App.setRoot("Visits");

    }

}