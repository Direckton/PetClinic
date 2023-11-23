/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petclinicfxml.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author direc
 */
public class FileLoader {
    List<List<String>> records; 
    Scanner scanner;
    String COMMA_DELIMITER = ",";

    
    public FileLoader(){
        records = new ArrayList<>();
        
    }
    
    public void readCSV() throws FileNotFoundException{
        
        try (Scanner scanner = new Scanner(new File("book.csv"))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
    
    }
    private List<String> getRecordFromLine(String line) {
    List<String> values = new ArrayList<String>();
    try (Scanner rowScanner = new Scanner(line)) {
        rowScanner.useDelimiter(COMMA_DELIMITER);
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next());
        }
    }
    return values;
    }
    public ArrayList<Pet> convertToPet()
    {
        ArrayList<Pet> petList = new ArrayList<>();
        if(!records.isEmpty())
        {
            for(var row : records)
            {
                    petList.add(new Pet(Integer.parseInt(row.get(0)),row.get(1),
                    Integer.parseInt(row.get(2)),Pet.Health.valueOf(row.get(3))));
            }
        }
        return petList;
    }
}