package com.example.futbol_manager_app;

import com.example.futbol_manager_app.Utils.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangeScene {

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static PreparedStatement psInsert = null;
    private static PreparedStatement psCheckUserExist = null;
    private static ResultSet resultSet = null;

    public static void changeScene(ActionEvent event, String fxmlFile,String title, String username){

        Parent root = null;
        if ( username != null){
            try {
                FXMLLoader loader = new FXMLLoader( ChangeScene.class.getResource(fxmlFile));
                root = loader.load();
                TablesController tablesController = loader.getController();
            }catch ( Exception e){
                e.printStackTrace();
            }

        }
        else {
            try {

                root = FXMLLoader.load(ChangeScene.class.getResource(fxmlFile));

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.centerOnScreen();
        stage.show();


    }// end of change Scene method

    public static  HashMap<String,String> checkIfUserExist(ActionEvent event, String userName, String password){

        HashMap <String, String> userValidation = new HashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ArrayList<String> dbCredentials = FileHandler.getCredentials();
            connection = DriverManager.getConnection(dbCredentials.get(0),dbCredentials.get(1),dbCredentials.get(2));
            psCheckUserExist = connection.prepareStatement("SELECT username,password FROM users WHERE username = ?");
            psCheckUserExist.setString(1,userName);

            resultSet = psCheckUserExist.executeQuery();

            if (!resultSet.isBeforeFirst()){
                userValidation.put(userName,password);
                return  userValidation;
            }
            else {
                while (resultSet.next()){
                    String retrievePassword = resultSet.getString("password");
                    //if password match the one user enter change scene
                    if (retrievePassword.equals(password))
                        changeScene(event, "tables-view.fxml", "League Manager " + userName,userName);
                }
//                //if password did not match the one user entered
//                System.out.println("Password did not match ");
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Provided credential are incorrect");
//                alert.show();

            }


        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Something went wrong trying to connect to database. ");
        }
        finally {
            // all closed database connection to avoid leakage
            // best oder to close database connection is to  closed resultset first then prepared statements then connection
            // resultset closed
            if (resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //psInsert psCheckedUserExist closed
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //psInsert closed
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //psInsert closed
            if (connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } // end of finally block
        return userValidation;

    } //end checkIFUserExist Method



}
