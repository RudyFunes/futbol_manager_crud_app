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
    HashMap <Integer, String> allLeagues;
    HashMap <Integer, String> allTeams;
    HashMap <Integer, String> allPlayers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons.add(btn_delete_league); buttons.add(btn_insert_league);
        buttons.add(btn_update_league); buttons.add(btn_insert_team);
        buttons.add(btn_delete_team); buttons.add(btn_update_team);
        buttons.add(btn_insert_player); buttons.add(btn_delete_player);
        buttons.add(btn_update_player); buttons.add(btn_team);
        buttons.add(btn_player); buttons.add(btn_league);
        buttons.add(btn_clear); buttons.add(btn_logout);

        //initialize maps
        allLeagues = new HashMap<>();
        allTeams = new HashMap<>();
        allPlayers = new HashMap<>();
        //preLoad tables data
        try {
            league_table();
            team_table();
            player_table();
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
                playerInsertRecord();
                player_table();
                break;
            case 7 :
                playerDeleteReocrd();
                player_table();
                clear();
                break;
            case 8 :
                playerUpdateRecord();
                player_table();
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
            lbl_team_message1.setVisible(false);
            lbl_player_message1.setVisible(false);
            clear();
        }
        //set player visible
        if (num == 10 ){
            table_view_team.setVisible(false);
            table_view_leagues.setVisible(false);
            pane_league.setVisible(false);
            pane_team.setVisible(false);
            table_view_player.setVisible(true);
            pane_player.setVisible(true);
            lbl_league_message1.setVisible(false);
            lbl_team_message1.setVisible(false);
            clear();
        }
        //set team visible
        if (num ==9  ){
            table_view_team.setVisible(true);
            table_view_leagues.setVisible(false);
            pane_league.setVisible(false);
            pane_team.setVisible(true);
            table_view_player.setVisible(false);
            pane_player.setVisible(false);
            lbl_player_message1.setVisible(false);
            lbl_league_message1.setVisible(false);
            clear();
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
        //------- stored team name and team_id in a Hashmap Variable
        for (Teams teams : teamsList){
            allTeams.put(teams.getId(),teams.getTeam_name());
        }


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
            allLeagues.put(leagues.getId(),leagues.getLeague());
        }
    }
    private void player_table() throws SQLException {
        // ObservableList<Leagues> leaguesList = DBConnection.getLeagues();
        playersList = DBConnection.getPlayers();

        table_column_player_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_column_player_name.setCellValueFactory(new PropertyValueFactory<>("player_name"));
        table_column_player_number.setCellValueFactory(new PropertyValueFactory<>("player_number"));
        table_column_player_age.setCellValueFactory(new PropertyValueFactory<>("player_age"));
        table_column_player_team.setCellValueFactory(new PropertyValueFactory<>("player_team"));
        table_column_player_position.setCellValueFactory(new PropertyValueFactory<>("player_position"));
        table_column_player_nationality.setCellValueFactory(new PropertyValueFactory<>("player_country"));

        table_view_player.setItems(playersList);
        //------- stored team name and team_id in a Hashmap Variable
        for (Players players : playersList){
            allPlayers.put(players.getId(),players.getPlayer_name());
        }
    }
    /* the following methods Are teh CRUD for Leagues Table*/
    private void leagueInsertRecord() throws SQLException {
        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_league_id.getText());
        boolean yearIsDigit = Utility.checkIfHasOnlyDigits(txt_league_year.getText());
        lbl_league_message1.setVisible(false);

        String query =  "INSERT INTO league (country,league_name,year)" +
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
                lbl_league_message1.setVisible(true);
                lbl_league_message1.setText("All Field Are Required");
            }
        }
        //
        else {
            lbl_league_message1.setVisible(true);
            lbl_league_message1.setText("ID and year must be Digits");
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

                    String query = "UPDATE league SET country = '" + txt_league_country.getText() +"', league_name ='" + txt_league_league.getText()
                            + "', year = " + txt_league_year.getText() + " WHERE league_id = " + txt_league_id.getText() +";";

                    DBConnection.executeQuery(query);
                    lbl_league_message1.setVisible(true);
                    lbl_league_message1.setText("Successful updated row id: " + txt_league_id.getText());
                    in = true;
                    clear();
                    break;
                }

            }//end for loop
            if(!in){
                lbl_league_message1.setVisible(true);
                lbl_league_message1.setText("League ID not found");
            }
        }
        //
        else {
            lbl_league_message1.setVisible(true);
            lbl_league_message1.setText("ID must be valid row ID");
        }

    }
    private void leagueDeleteRecord() throws SQLException {
        boolean idIsNumber = Utility.checkIfHasOnlyDigits(txt_league_id.getText());
        boolean in = false;
        if (idIsNumber && !txt_league_id.getText().equals("")){
            int id = Integer.parseInt(txt_league_id.getText());

            for (Leagues l : leaguesList) {
                if (l.getId() == id) {
                    String query = "DELETE FROM league WHERE league_id = " + txt_league_id.getText();
                    DBConnection.executeQuery(query);
                    lbl_league_message1.setVisible(true);
                    lbl_league_message1.setText("Successful Deleted row id: " + txt_league_id.getText());
                    in = true;
                    break;
                }

            }//end for loop
            if (!in){
                lbl_league_message1.setVisible(true);
                lbl_league_message1.setText("ID not found in table");
            }
        }
        else  {
            lbl_league_message1.setVisible(true);
            lbl_league_message1.setText("ID must be valid Number");
        }
    }
    /* the following methods Are teh CRUD for Teams Table*/
    private void teamInsertRecord() throws SQLException {
        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_team_id.getText());
        boolean capacityIsDigit = Utility.checkIfHasOnlyDigits(txt_team_capacity.getText());
        boolean leagueFound = false;
        String query = "";
        lbl_team_message1.setVisible(false);


        //validate query
        if ( (idIsDigit && capacityIsDigit) && ( !txt_team_id.getText().equals("") && !txt_team_capacity.getText().equals("")) ){
            //check to see if league already exist in the database
            for (Map.Entry<Integer,String> entry : allLeagues.entrySet()){
                if (txt_team_league.getText().equals(entry.getValue())){
                    query =  "INSERT INTO team (team_name,team_location,stadium,capacity,league_id)" +
                            "VALUES ('" + txt_team_name.getText() + "','" + txt_team_location.getText() +  "','"  + txt_team_stadium.getText() + "'," +
                            txt_team_capacity.getText()   + "," + entry.getValue() +");";
                            leagueFound = true;
                    break;
                }
            }

            /* validation check for text boxes*/
                if (!txt_team_name.getText().equals("") && !txt_team_location.getText().equals("") && !txt_team_stadium.getText().equals("") &&
                        !txt_team_capacity.getText().equals("")  && !txt_team_league.getText().equals("") && leagueFound){

                    DBConnection.executeQuery(query);
                    lbl_team_message1.setVisible(true);
                    lbl_team_message1.setText("Row successfully added");
                    clear();
                }
                else {
                    lbl_team_message1.setVisible(true);
                    lbl_team_message1.setText("All Field Are Required");
                   if (!leagueFound){
                        lbl_team_message1.setText("Make sure league is in Database");
                        lbl_team_message1.setVisible(true);
                    }

                }
           }
        else {
            lbl_team_message1.setVisible(true);
            lbl_team_message1.setText("ID and Capacity must be Digits");
        }


    }// end of teamInsertMethod
    private void teamUpdateRecord() throws SQLException {
        boolean in = false;
        boolean leagueFound = false;
        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_team_id.getText());
        boolean capacityIsDigit = Utility.checkIfHasOnlyDigits(txt_team_capacity.getText());
        lbl_league_message1.setVisible(false);


        /* validation check for text boxes*/
        if ( (idIsDigit && !txt_team_id.getText().equals(""))  && (capacityIsDigit || txt_team_capacity.getText().equals("")) ){
            int id = Integer.parseInt(txt_team_id.getText());
            for (Teams t : teamsList) {
                if (t.getId() == id) {
                    in = true;
                    if (txt_team_name.getText().equals("")  ){
                        txt_team_name.setText(t.getTeam_name()) ;
                    }
                    if (txt_team_location.getText().equals("") ){
                        txt_team_location.setText(t.getTeam_location());
                    }
                    if (txt_team_stadium.getText().equals("")){
                        txt_team_stadium.setText(t.getStadium());
                    }
                    if(txt_team_capacity.getText().equals("")){
                        txt_team_capacity.setText(String.valueOf(t.getCapacity()));
                    }

                    //if not league selected to be updated keeps the same already in data
                    if (txt_team_league.getText().equals("")){
                        for(Map.Entry<Integer, String> entry : allLeagues.entrySet()){
                            if (t.getLeague_id() == entry.getKey()){
                                txt_team_league.setText(String.valueOf(entry.getKey()));
                                leagueFound = true;
                                break;
                            }
                        }
                    }
                    //check to see if league exist before entering the value
                    if (!txt_team_league.getText().equals("")){
                        for(Map.Entry<Integer,String> entry : allLeagues.entrySet()){
                            if (txt_team_league.getText().equals(entry.getValue())){
                                txt_team_league.setText(String.valueOf(entry.getKey()));
                                leagueFound = true;
                                break;
                            }
                        }
                    }
                    if (leagueFound ){
                        String query = "UPDATE team SET team_name = '" + txt_team_name.getText() +"', team_location ='" + txt_team_location.getText()
                                + "', stadium = '" + txt_team_stadium.getText()   + "', capacity = '" + txt_team_capacity.getText()   + "', league_id = '" + txt_team_league.getText()
                                + "' WHERE team_id = " + txt_team_id.getText() +";";

                        DBConnection.executeQuery(query);
                        lbl_team_message1.setVisible(true);
                        lbl_team_message1.setText("Successful updated team id: " + txt_team_id.getText());
                        clear();
                        break;
                    }
                    else {
                        lbl_team_message1.setVisible(true);
                        lbl_team_message1.setText("League not found add it");
                        clear();
                        break;
                    }
                }// end nested if

                if(!in){
                    lbl_team_message1.setVisible(true);
                    lbl_team_message1.setText("Team ID not found");
                }
            }
        }//end  main for loop
        else {
            lbl_team_message1.setVisible(true);
            lbl_team_message1.setText("Must enter valid row ID");
            clear();
        }

    }//end of method
    private void teamDeleteRecord() throws SQLException {
        boolean idIsNumber = Utility.checkIfHasOnlyDigits(txt_team_id.getText());
        boolean in = false;
        if (idIsNumber && !txt_team_id.getText().equals("")){
            int id = Integer.parseInt(txt_team_id.getText());
            for (Teams t : teamsList) {
                if (t.getId() == id) {
                    String query = "DELETE FROM team WHERE team_id = " + txt_team_id.getText();
                    DBConnection.executeQuery(query);
                    lbl_team_message1.setVisible(true);
                    lbl_team_message1.setText("Successful Deleted row id: " + txt_team_id.getText());
                    in = true;
                    break;
                }

            }//end for loop
            if (!in){
                lbl_team_message1.setVisible(true);
                lbl_team_message1.setText("ID not found in table");
            }
        }
        else  {
            lbl_team_message1.setVisible(true);
            lbl_team_message1.setText("Must be valid row ID");
        }
    }//end of teamDelete method
    /* the following methods Are teh CRUD for Players Table*/
    private void playerInsertRecord() throws SQLException {
        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_player_id.getText());
        boolean numberIsDigit = Utility.checkIfHasOnlyDigits(txt_player_number.getText());
        boolean ageIsDigit = Utility.checkIfHasOnlyDigits(txt_player_age.getText());
        boolean leagueFound = false;
        String query = "";
        lbl_player_message1.setVisible(false);


        //validate fields with digits
        if ( ((idIsDigit && numberIsDigit && ageIsDigit ) && (!txt_player_id.getText().equals("") && !txt_player_number.getText().equals("") &&
                !txt_player_age.getText().equals(""))) ){
            //check to see if team already exist in the database
            for (Map.Entry<Integer,String> entry : allTeams.entrySet()){
                if (txt_player_team.getText().equals(entry.getValue())){
                    query =  "INSERT INTO players (name,j_number,age,team_id,position,nationality)" +
                            "VALUES ('" + txt_player_name.getText() + "','" + txt_player_number.getText() +  "','"  + txt_player_age.getText() + "'," +
                            entry.getKey() + ",'" + txt_player_position.getText() + "','"   + txt_player_nationality.getText() + "');";
                    leagueFound = true;
                    break;
                }
            }
            /* validation check for text boxes not equal empty*/
            if (!txt_player_name.getText().equals("") &&!txt_player_team.getText().equals("")  && !txt_player_position.getText().equals("")  &&
                    !txt_player_nationality.getText().equals("") && leagueFound){
                System.out.println(query);
                DBConnection.executeQuery(query);
                lbl_player_message1.setVisible(true);
                lbl_player_message1.setText("Row successfully added");
                clear();
            }
            else {
                lbl_player_message1.setVisible(true);
                lbl_player_message1.setText("All Field Are Required");
                if (!leagueFound){
                    lbl_player_message1.setText("Make sure team is in Database");
                    lbl_player_message1.setVisible(true);
                }

            }
        }
        else {
            lbl_player_message1.setVisible(true);
            lbl_player_message1.setText("ID, Number and Age must be digits");
        }


    }
    private void playerUpdateRecord() throws SQLException {
        boolean in = false;
        boolean leagueFound = false;
        boolean idIsDigit = Utility.checkIfHasOnlyDigits(txt_player_id.getText());
        boolean numberIsDigit = Utility.checkIfHasOnlyDigits(txt_player_number.getText());
        boolean ageIsDigit = Utility.checkIfHasOnlyDigits(txt_player_age.getText());
        lbl_player_message1.setVisible(false);


        /* validation check for text boxes*/
        if ( (idIsDigit && !txt_player_id.getText().equals(""))  && (numberIsDigit || !txt_player_number.getText().equals("")) && (ageIsDigit || !txt_player_age.getText().equals("")) ){
            int id = Integer.parseInt(txt_player_id.getText());
            for (Players p : playersList) {
                if (p.getId() == id) {

                    if (txt_player_name.getText().equals("")  ){
                        txt_player_name.setText(p.getPlayer_name()) ;
                    }
                    if (txt_player_number.getText().equals("") ){
                        txt_player_number.setText(String.valueOf(p.getPlayer_number()));
                    }
                    if (txt_player_age.getText().equals("")){
                        txt_player_age.setText(String.valueOf(p.getPlayer_age()));
                    }

                    if(txt_player_position.getText().equals("")){
                        txt_player_position.setText(String.valueOf(p.getPlayer_position()));
                    }
                    if(txt_player_nationality.getText().equals("")){
                        txt_player_nationality.setText(p.getPlayer_country());
                    }

                    //if not player team selected to be updated keeps the same already in data
                    if (txt_player_team.getText().equals("")){
                        for(Map.Entry<Integer,String> entry : allTeams.entrySet()){
                            if (p.getTeam_id() == entry.getKey()){
                                txt_player_team.setText(entry.getValue());
                                leagueFound = true;
                                break;
                            }
                        }
                    }
                    //check to see if  player team exist before entering the value
                    if (!txt_player_team.getText().equals("")){
                        for(Map.Entry<Integer,String> entry : allTeams.entrySet()){
                            if (txt_player_team.getText().equals(entry.getValue())){
                                txt_player_team.setText(String.valueOf(entry.getKey()));
                                leagueFound = true;
                                break;
                            }
                        }
                    }
                    for(Map.Entry<Integer,String> entry : allTeams.entrySet()){
                        System.out.println(entry.getValue());
                    }

                    if (leagueFound ){
                        String query = "UPDATE players SET name = '" + txt_player_name.getText() +"', j_number ='" + txt_player_number.getText()
                                + "', age = '" + txt_player_age.getText()   + "', team_id = " + txt_player_team.getText()   + ", position = '" + txt_player_position.getText()
                                + "', nationality = '" + txt_player_nationality.getText() + "' WHERE player_id = " + txt_player_id.getText() +";";

                        DBConnection.executeQuery(query);
                        lbl_player_message1.setVisible(true);
                        lbl_player_message1.setText("Successful updated player id: " + txt_team_id.getText());
                        clear();
                        break;
                    }
                    else {
                        lbl_player_message1.setVisible(true);
                        lbl_player_message1.setText("Team not found add it");
                        break;
                    }
                }// end nested if

                if(!in){
                    lbl_player_message1.setVisible(true);
                    lbl_player_message1.setText("Player ID not found");
                }
            }
        }//end  main for loop
        else {
            lbl_player_message1.setVisible(true);
            lbl_player_message1.setText("Must enter valid row ID");
        }


    }
    private void playerDeleteReocrd() throws SQLException {
        boolean idIsNumber = Utility.checkIfHasOnlyDigits(txt_player_id.getText());
        boolean in = false;
        if (idIsNumber && !txt_player_id.getText().equals("")){
            int id = Integer.parseInt(txt_player_id.getText());
            for (Players p: playersList) {
                if (p.getId() == id) {
                    String query = "DELETE FROM players WHERE player_id = " + txt_player_id.getText();
                    DBConnection.executeQuery(query);
                    lbl_player_message1.setVisible(true);
                    lbl_player_message1.setText("Successful Deleted row id: " + txt_player_id.getText());
                    in = true;
                    break;
                }

            }//end for loop
            if (!in){
                lbl_player_message1.setVisible(true);
                lbl_player_message1.setText("ID not found in table");
            }
        }
        else  {
            lbl_player_message1.setVisible(true);
            lbl_player_message1.setText("Must enter valid row ID");
        }
    }
}
