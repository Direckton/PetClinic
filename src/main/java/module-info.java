module com.mycompany.petclinicfxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
       

    opens com.mycompany.petclinicfxml to javafx.fxml;
    opens com.mycompany.petclinicfxml.Model to java.fxml;
    
    exports com.mycompany.petclinicfxml;
    exports com.mycompany.petclinicfxml.Model;

}
