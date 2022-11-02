package com.example.futbol_manager_app;

import com.example.futbol_manager_app.Utils.FileHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField textfield_username;
    @FXML
    private TextField textfield_password;
    @FXML
    private Button btn_login;
    @FXML
    private Label lbl_wrongpassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //use map to check for credentials
                HashMap<String,String> map = new HashMap<>();
                map =  ChangeScene.checkIfUserExist(event,textfield_username.getText(),textfield_password.getText());

                lbl_wrongpassword.setText("Wrong username or password");
                lbl_wrongpassword.setVisible(true);
                textfield_password.setText("");
                textfield_username.setText("");
            }
        });
        lbl_wrongpassword.setVisible(false);


    }
}