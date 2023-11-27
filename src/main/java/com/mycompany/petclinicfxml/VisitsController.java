/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.petclinicfxml;

import com.mycompany.petclinicfxml.Model.Medicine;
import com.mycompany.petclinicfxml.Model.Pet;
import com.mycompany.petclinicfxml.Model.Registration;
import com.mycompany.petclinicfxml.Model.Visit;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author direc
 */
public class VisitsController{

    @FXML
    private TextField searchParam;
    @FXML
    private Button findBtn;
    @FXML
    private Button back;
    @FXML
    private TableView<Visit> table;
    @FXML
    private TableColumn<Visit, Integer> visitId;
    @FXML
    private TableColumn<Visit, String> time;
    @FXML
    private TableColumn<Visit, String> cost;
    @FXML
    private TableColumn<Visit, Boolean> held;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button medicineBtn;

    /**
     * Initializes the controller class.
     */
    private final ObservableList<Visit> data;    
    private final Registration registration;
    
    public VisitsController(Registration registration, int petId){
        this.registration = registration;
        data = FXCollections.observableArrayList(registration.getVisits(petId));
        data.addListener(new ListChangeListener<Visit>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Visit> change) {
                while (change.next()) {
                    if (change.wasPermutated()) {
                        for (int i = change.getFrom(); i < change.getTo(); ++i) {

                        }
                    } else if (change.wasUpdated()) {

                    } else {
                        for (var remitem : change.getRemoved()) {
                            registration.deleteVisit(remitem.getId(),petId);
                        }
                        for (var additem : change.getAddedSubList()) {
//                            registration.addNewPet(additem);
                            registration.addNewVisit(additem,petId);
                        }
                    }
                }
            }
        });
    }

    public void initialize() {
        // TODO
        visitId.setCellValueFactory(new PropertyValueFactory<Visit, Integer>("id"));
        time.setCellValueFactory(new PropertyValueFactory<Visit, String>("date"));
        cost.setCellValueFactory(new PropertyValueFactory<Visit, String>("costString"));
        held.setCellValueFactory(new PropertyValueFactory<Visit, Boolean>("held"));
    
        table.setItems(data);
        table.setEditable(true);
        
        time.setCellFactory(TextFieldTableCell.forTableColumn());	

        time.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Visit, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Visit, String> t) {
                ((Visit) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDate(t.getNewValue());
            }
        });
        
        cost.setCellFactory(TextFieldTableCell.forTableColumn());	

        cost.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Visit, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Visit, String> t) {
                ((Visit) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCost(Float.parseFloat(t.getNewValue()));
            }
        });
        
        held.setEditable(true);
        
        held.setCellFactory(CheckBoxTableCell.forTableColumn(held));

        
        held.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Visit, Boolean>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Visit, Boolean> event) {
                TableView<Visit> table = event.getTableView();
                Visit visit = table.getItems().get(event.getTablePosition().getRow());
                visit.setHeld(event.getNewValue());
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
        TableView<Visit> tableView = table;
        ObservableList<TableColumn<Visit, ?>> columns = tableView.getColumns();

        for (int i = 0; i < tableView.getItems().size(); i++) {
            Visit Visit = tableView.getItems().get(i);

            if (Visit.getId() == id) {
                // Highlight the row
                tableView.requestFocus();
                tableView.getSelectionModel().select(i);
                tableView.scrollTo(i);

            }
        }
    }

    @FXML
    private void goBackToPets(ActionEvent event) throws IOException {
        registration.saveDB();
        App.setRoot("FindPet");
    }

    @FXML
    private void Add(ActionEvent event) {
        Comparator<Visit> visitComparator = Comparator.comparing(Visit::getId);
        data.sort(visitComparator);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if(data.isEmpty())
        {
            data.add(new Visit(1,LocalDateTime.now(),0,false,new ArrayList<Medicine>()));
        }
        else
        {
            data.add(new Visit(data.get(data.size()-1).getId()+1,LocalDateTime.now(),0,false,new ArrayList<Medicine>()));
            
        }
    }

    @FXML
    private void Delete(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index != -1) data.remove(index);
    }

    @FXML
    private void openMedicines(ActionEvent event) {
    }
    
}
