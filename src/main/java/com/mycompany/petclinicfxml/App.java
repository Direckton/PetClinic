package com.mycompany.petclinicfxml;

import com.mycompany.petclinicfxml.Model.Registration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Registration registration;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainMenu"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory( p -> { return getController(fxml);} ); //ADD SWITCH TO CREATE NEW CONTROLLERS
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        registration = new Registration();
        launch();
    }
    
    public static Object getController(String s)
    {
        switch (s){
            case "MainMenu":
            {
                return new MainMenuController();
            }
            case "FindPet":
            {
                return new FindPetController(registration);
            }
            default:
                return new MainMenuController();

        }
    }

}