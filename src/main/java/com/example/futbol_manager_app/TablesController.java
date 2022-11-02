package com.example.futbol_manager_app;


import com.example.futbol_manager_app.DBConnection.DBConnection;
import com.example.futbol_manager_app.POJOS.Leagues;
import com.example.futbol_manager_app.POJOS.Players;
import com.example.futbol_manager_app.POJOS.Teams;
import com.example.futbol_manager_app.Utils.Utility;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TablesController implements Initializable {

    @FXML private Button btn_league; @FXML private Button btn_team; @FXML private Button btn_player;
    @FXML private Button btn_clear; @FXML private Button btn_logout;
    //-------------Table view for leagues -----------------------------//
    @FXML private TableView<Leagues> table_view_leagues;
    @FXML private TableColumn<Leagues,Integer>  table_column_league_id; @FXML private TableColumn<Leagues,String>  table_column_league_league;
    @FXML private TableColumn<Leagues,String>  table_column_league_country; @FXML private TableColumn<Leagues,Integer>  table_column_League_year;
    //-------------Table view for Teams-----------------------------//
    @FXML private TableView<Teams> table_view_team;
    @FXML private TableColumn<Teams,Integer>  table_column_team_id; @FXML private TableColumn<Teams,String>  table_column_team_name;
    @FXML private TableColumn<Teams,String>  table_column_team_location; @FXML private TableColumn<Teams,String>  table_column_team_stadium;
    @FXML private TableColumn<Teams,Integer>  table_column_team_capacity; @FXML private TableColumn<Teams,String>  table_column_team_league;
    //-------------Table view for Players -----------------------------//
    @FXML private TableView<Players> table_view_player;
    @FXML private TableColumn<Players,Integer>  table_column_player_id; @FXML private TableColumn<Players,String>  table_column_player_name;
    @FXML private TableColumn<Players,Integer>  table_column_player_number; @FXML private TableColumn<Players,Integer>  table_column_player_age;
    @FXML private TableColumn<Players,Integer>  table_column_player_team; @FXML private TableColumn<Players,String>  table_column_player_position;
    @FXML private TableColumn<Players,String>  table_column_player_nationality;
    //-------------Pane for leagues  and tags -----------------------------//
    @FXML private Pane pane_league; @FXML private TextField txt_league_id; @FXML private TextField txt_league_country;
    @FXML private TextField txt_league_league; @FXML private TextField txt_league_year;
    @FXML private Button btn_insert_league;  @FXML private Button btn_update_league; @FXML private Button btn_delete_league;
    @FXML private Label lbl_league_message1; @FXML private Label lbl_league_message2;
    //-------------Pane for teams  and tags -----------------------------//
    @FXML private Pane pane_team; @FXML private TextField txt_team_id; @FXML private TextField txt_team_name;
    @FXML private TextField txt_team_stadium; @FXML private TextField txt_team_location;
    @FXML private TextField txt_team_capacity; @FXML private TextField txt_team_league;
    @FXML private Button btn_insert_team;  @FXML private Button btn_update_team; @FXML private Button btn_delete_team;
    @FXML private Label lbl_team_message1; @FXML private Label lbl_team_message2;
    //-------------Pane for players  and tags -----------------------------//
    @FXML private Pane pane_player; @FXML private TextField txt_player_id; @FXML private TextField txt_player_name;
    @FXML private TextField txt_player_number; @FXML private TextField txt_player_age; @FXML private TextField txt_player_position;
    @FXML private TextField txt_player_team; @FXML private TextField txt_player_nationality;
    @FXML private Button btn_insert_player;  @FXML private Button btn_update_player; @FXML private Button btn_delete_player;
    @FXML private Label lbl_player_message1; @FXML private Label lbl_player_message2;
    //-------------End of variable decalration -----------------------------//

    private ArrayList <Button> buttons = new ArrayList<>();

    private ObservableList<Leagues> leaguesList;
    private ObservableList<Teams> teamsList;
    private ObservableList<Players> playersList;

    //------- stored all leagues names and leagues id  in a Hashmap Variable----
    HashMap <String,Integer> allLeagues;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons.add(btn_delete_league); buttons.add(btn_insert_league);
        buttons.add(btn_update_league); buttons.add(btn_insert_team);
        buttons.add(btn_delete_team); buttons.add(btn_update_team);
        buttons.add(btn_insert_player); buttons.add(btn_delete_player);
        buttons.add(btn_update_player); buttons.add(btn_team);
        buttons.add(btn_player); buttons.add(btn_league);
        buttons.add(btn_clear); buttons.add(btn_logout);

        allLeagues = new HashMap<>();
        try {
            league_table();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    txt_league_league.setPromptText("League");
        for (Button b : buttons){
            b.setOnAction(event -> {
             int buttonPushed = 20;
             buttonPushed = Utility.getRightButton(event,buttons);
                try {
                    handleButtonsActions(event, buttonPushed);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }


    }// end initializable
    /* Buttons selected action*/
    private void handleButtonsActions(ActionEvent event, int buttonPushed) throws SQLException {

        switch (buttonPushed){
            case 0 :
                leagueDeleteRecord();
                league_table();
                clear();
                break;
            case 1 :
                leagueInsertRecord();
                league_table();
                break;
            case 2 :
                leagueUpdateRecord();
                league_table();
                break;
            case 3 :
                teamInsertRecord();
                team_table();
                break;
            case 4 :
                teamDeleteRecord();
                team_table();
                clear();
                break;
            case 5 :
               teamUpdateRecord();
               team_table();
                break;
            case 6 :
                System.out.println("to be implemented");
                break;
            case 7 :
                System.out.println("to be implemented");
                break;
            case 8 :
                System.out.println("to be implemented");
                break;
            case 9 :
                tablesOff(9);
                team_table();
                break;
            case 10 :
                tablesOff(10);
                player_table();
                break;
            case 11 :
                tablesOff(11);
                league_table();
                break;
            case 12 :
                clear();
                break;
            case 13 :
                ChangeScene.changeScene(event,"main-view.fxml","Log In",null);
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Wrong button  Error");
                alert.show();
        }

    }//end of handleButtonsActions
    private void tablesOff(int num){
        //set league visible
        if (num == 11){
            table_view_team.setVisible(false);
            table_view_leagues.setVisible(true);
            pane_league.setVisible(true);
            pane_team.setVisible(false);
            table_view_player.setVisible(false);
            pane_player.setVisible(false);
        }
        //set player visible
        if (num == 10 ){
            table_view_team.setVisible(false);
            table_view_leagues.setVisible(false);
            pane_league.setVisible(false);
            pane_team.setVisible(false);
            table_view_player.setVisible(true);
            pane_player.setVisible(true);
        }
        //set team visible
        if (num ==9  ){
            table_view_team.setVisible(true);
            table_view_leagues.setVisible(false);
            pane_league.setVisible(false);
            pane_team.setVisible(true);
            table_view_player.setVisible(false);
            pane_player.setVisible(false);
        }
    }
    /* Clears up text from the Textview field and others*/
    private void clear(){
        txt_league_id.setText("");
        txt_league_country.setText("");
        txt_league_year.setText("");
        txt_league_league.setText("");
        //teams TextBoxes
        txt_team_id.setText("");
        txt_team_capacity.setText("");
        txt_team_league.setText("");
        txt_team_location.setText("");
        txt_team_name.setText("");
        txt_team_stadium.setText("");
        //Players TextBoxes
        txt_player_age.setText("");
        txt_player_id.setText("");
        txt_player_name.setText("");
        txt_player_nationality.setText("");
        txt_player_number.setText("");
        txt_player_position.setText("");
        txt_player_team.setText("");
    }
    // the following methods  gets the data and fill the table view with it.
    private void team_table() throws SQLException {
        // ObservableList<Leagues> leaguesList = DBConnection.getLeagues();

        teamsList = DBConnection.getTeams();
        //print to the console the leagues


        table_column_team_id.setCellValueFactory(new PropertyValueFactory<Teams, Integer>("id"));
        table_column_team_name.setCellValueFactory(new PropertyValueFactory<Teams, String>("team_name"));
        table_column_team_location.setCellValueFactory(new PropertyValueFactory<Teams, String>("team_location"));
        table_column_team_stadium.setCellValueFactory(new PropertyValueFactory<Teams, String>("stadium"));
        table_column_team_capacity.setCellValueFactory(new PropertyValueFactory<Teams, Integer>("capacity"));
        table_column_team_league.setCellValueFactory(new PropertyValueFactory<Teams, String>("league"));


        table_view_team.setItems(teamsList);
    }
    private void league_table() throws SQLException {
        // ObservableList<Leagues> leaguesList = DBConnection.getLeagues();
        leaguesList = DBConnection.getLeagues();

        table_column_league_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_column_league_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        table_column_league_league.setCellValueFactory(new PropertyValueFactory<>("league"));
        table_column_League_year.setCellValueFactory(new PropertyValueFactory<>("year"));

        table_view_leagues.setItems(leaguesList);

        //------- stored league name and league id in a Hashmap Variable
        for (Leagues leagues : leaguesList){
            allLeagues.put(leagues.getLeague(),leagues.getId());
        }
    }
    private void player_table() throws SQLException {
        // ObservableList<Leagues> leaguesList = DBConnection.getLeagues();
        playersList = DBConnection.getPlayers();

        table_column_player_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_column_player_name.setCellValueFactory(new PropertyValueFactory<>("player_name"));
        table_column_player_number.setCellValueFactory(new PropertyValueFactory<>("player_number"));
        table_column_player_age.setCellValueFactory(new PropertyValueFactory<>("player_age"));
        table_column_player_team.setCellValueFactory(new PropertyValueFactory<>("team_id"));
        table_column_player_position.setCellValueFactory(new PropertyValueFactory<>("player_position"));
        table_column_player_nationality.setCellValueFactory(new PropertyValueFactory<>("player_country"));

        table_view_player.setItems(playersList);
    }
    /* the following methods Are teh CRUD for Leagues Table*/
    private void leagueInsertRecord() throws SQLException {

        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_league_id.getText());
        boolean yearIsDigit = Utility.checkIfHasOnlyDigits(txt_league_year.getText());
        lbl_league_message1.setVisible(false);

        String query =  "INSERT INTO countrie_league (country,league_name,founded)" +
                "VALUES ('" + txt_league_country.getText() + "','" + txt_league_league.getText() +  "',"  + txt_league_year.getText() +");";

        /* validation check for text boxes*/
        if ((idIsDigit && yearIsDigit) && ( (txt_league_id.getText().length() >0)  && (txt_league_year.getText().length() >0) ) ){
            if (!txt_league_league.getText().equals("") && !txt_league_country.getText().equals("") ){
                DBConnection.executeQuery(query);
                lbl_league_message1.setVisible(true);
                lbl_league_message1.setText("Row successfully added");
                clear();
            }
            else {
                txt_league_league.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                txt_league_country.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                lbl_league_message1.setVisible(true);
                lbl_league_message1.setText("All Field Are Required");
            }
        }
        //
        else {
            lbl_league_message1.setVisible(true);
            lbl_league_message1.setText("ID and year must be Digits");
            txt_league_league.setStyle(null);
            txt_league_country.setStyle(null);
        }


    }
    private void leagueUpdateRecord() throws SQLException {
        boolean updated = false;
        boolean in = false;
        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_league_id.getText());
        boolean yearIsDigit = Utility.checkIfHasOnlyDigits(txt_league_year.getText());
        lbl_league_message1.setVisible(false);



        /* validation check for text boxes*/
        if (idIsDigit && !txt_league_id.getText().equals("")){
            int id = Integer.parseInt(txt_league_id.getText());
            for (Leagues l : leaguesList) {
                if (l.getId() == id) {
                    if (txt_league_league.getText().equals("")  ){
                        txt_league_league.setText(l.getLeague()) ;
                    }
                    if (txt_league_country.getText().equals("") ){
                        txt_league_country.setText(l.getCountry());
                    }
                    if (txt_league_year.getText().equals("")){
                        txt_league_year.setText(String.valueOf(l.getYear()));
                    }
                    String query = "UPDATE countrie_league SET country = '" + txt_league_country.getText() +"', league_name ='" + txt_league_league.getText()
                            + "', founded = " + txt_league_year.getText() + " WHERE league_id = " + txt_league_id.getText() +";";

                    DBConnection.executeQuery(query);
                    lbl_league_message1.setVisible(true);
                    lbl_league_message1.setText("Successful updated row id: " + txt_league_id.getText());
                    in = true;
                    updated = true;
                    clear();
                    break;
                }

            }//end for loop
            if(!in){
                lbl_league_message1.setVisible(true);
                lbl_league_message1.setText("ID not found in table");
                txt_league_id.setStyle(null);
            }
        }
        //
        else {
            lbl_league_message1.setVisible(true);
            lbl_league_message1.setText("ID must be valid row ID");
            txt_league_id.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            if (updated){
                lbl_league_message1.setStyle(null);
            }
        }

    }
    private void leagueDeleteRecord() throws SQLException {
        boolean deleted = false;
        boolean idIsNumber = Utility.checkIfHasOnlyDigits(txt_league_id.getText());
        boolean in = false;
        if (idIsNumber && !txt_league_id.getText().equals("")){
            int id = Integer.parseInt(txt_league_id.getText());

            for (Leagues l : leaguesList) {
                if (l.getId() == id) {
                    String query = "DELETE FROM countrie_league WHERE league_id = " + txt_league_id.getText();
                    DBConnection.executeQuery(query);
                    lbl_league_message1.setVisible(true);
                    lbl_league_message1.setText("Successful Deleted row id: " + txt_league_id.getText());
                    in = true;
                    deleted = true;
                    break;
                }

            }//end for loop
            if (!in){
                lbl_league_message1.setVisible(true);
                lbl_league_message1.setText("ID not found in table");
                txt_league_id.setStyle(null);
            }
        }
        else  {
            lbl_league_message1.setVisible(true);
            lbl_league_message1.setText("ID must be valid Number");
            txt_league_id.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            if (deleted){
                txt_league_id.setStyle(null);
            }
        }


    }
    /* the following methods Are teh CRUD for Teams Table*/
    private void teamInsertRecord() throws SQLException {
        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_team_id.getText());
        boolean capacityIsDigit = Utility.checkIfHasOnlyDigits(txt_team_capacity.getText());
        String query = "";
        lbl_team_message1.setVisible(false);
        //check to see if league already exist in the database
        for (Map.Entry<String,Integer> entry : allLeagues.entrySet()){
            if (txt_team_league.getText().equals(entry.getKey())){
                query =  "INSERT INTO team (team_name,team_location,stadium,capacity,league_id)" +
                        "VALUES ('" + txt_team_name.getText() + "','" + txt_team_location.getText() +  "','"  + txt_team_stadium.getText() + "'," +
                        txt_team_capacity.getText()   + "," + entry.getValue() +");";
                System.out.println(query);
                break;
            }
            System.out.println("Key " + entry.getKey() + " value " + entry.getValue());
        }
        System.out.println(query);
        //validate query
        if (!query.equals("")){
            /* validation check for text boxes*/
            if ((idIsDigit && capacityIsDigit) && ( (txt_team_id.getText().length() >0) && (txt_team_capacity.getText().length() > 0))){
                if (!txt_team_name.getText().equals("") && !txt_team_location.getText().equals("") && !txt_team_stadium.getText().equals("") &&
                        !txt_team_capacity.getText().equals("")  && !txt_team_league.getText().equals("")){

                    DBConnection.executeQuery(query);
                    lbl_team_message1.setVisible(true);
                    lbl_team_message1.setText("Row successfully added");
                    clear();
                }
                else {
                    txt_team_name.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    txt_team_stadium.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    txt_team_location.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    lbl_league_message1.setVisible(true);
                    lbl_league_message1.setText("All Field Are Required");
                }
            }
            //
            else {
                lbl_team_message1.setVisible(true);
                lbl_team_message1.setText("ID and Capacity must be Digits");
                txt_league_league.setStyle(null);
                txt_league_country.setStyle(null);
            }
        }
      else {
            lbl_team_message1.setText("Make sure league is in Database");
            lbl_team_message1.setVisible(true);
        }

    }// end of teamInsertMethod
    private void teamUpdateRecord(){}
    private void teamDeleteRecord(){}
    /* the following methods Are teh CRUD for Players Table*/
    private void playerInsertRecord(){}
    private void playerUpdateRecord(){}
    private void playerDeleteReocrd(){}
}
