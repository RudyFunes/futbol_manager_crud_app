module com.example.futbol_manager_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.futbol_manager_app to javafx.fxml;
    exports com.example.futbol_manager_app;
    exports com.example.futbol_manager_app.DBConnection;
    exports  com.example.futbol_manager_app.POJOS;
    opens com.example.futbol_manager_app.DBConnection to javafx.fxml;
}