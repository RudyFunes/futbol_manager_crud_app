package com.example.futbol_manager_app.DBConnection;


import com.example.futbol_manager_app.POJOS.Leagues;
import com.example.futbol_manager_app.POJOS.Players;
import com.example.futbol_manager_app.POJOS.Teams;
import com.example.futbol_manager_app.Utils.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static PreparedStatement psInsert = null;
    private static PreparedStatement psCheckUserExist = null;
    private static ResultSet resultSet = null;

    //Maps to store league and teams
    private static HashMap<String,Integer> leaguesMap = new HashMap<>();
    private static HashMap<String,Integer> teamMap = new HashMap<>();



    public static Connection getDBConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ArrayList<String> dbCredentials = FileHandler.getCredentials();
            connection = DriverManager.getConnection(dbCredentials.get(0), dbCredentials.get(1), dbCredentials.get(2));
            return connection;
            }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Error connecting to database");
            return null;
        }
    }//end og get connection method

    // --------------------------------------------------------------------------------------//

    //get the leagues from my database
    public static ObservableList<Leagues> getLeagues() throws SQLException {

        ObservableList<Leagues> leaguesList = FXCollections.observableArrayList();

        Connection conn = getDBConnection();
        preparedStatement = conn.prepareStatement("SELECT * FROM countrie_league;");
        resultSet = preparedStatement.executeQuery();

        Leagues leagues;
        while (resultSet.next()){
            leagues = new Leagues(resultSet.getInt("league_id"),resultSet.getString("country"),resultSet.getString("league_name"),resultSet.getInt("founded"));
            leaguesList.add(leagues);
        }
        closeDBConnection();
        //populates teh league map with the data from the database
        for (Leagues league : leaguesList){
            leaguesMap.put(league.getLeague(),league.getId());
        }
        return leaguesList;
    }
    // get right league method
    public static String getRightLeague(int leagueID){

        //if the league id is found then return the name of the league
        for(Map.Entry<String,Integer> entry : leaguesMap.entrySet() ){
            if (entry.getValue() == leagueID){
                return entry.getKey();
            }
        }
        return "";
    }
    // --------------------------------------------------------------------------------------//
    public  static  ObservableList<Teams> getTeams() throws SQLException {

        ObservableList<Teams> teamsList = FXCollections.observableArrayList();

        Connection conn = getDBConnection();
        preparedStatement = conn.prepareStatement("SELECT * FROM team;");
        resultSet = preparedStatement.executeQuery();

        Teams teams;
        while (resultSet.next()){

            String league =  getRightLeague(resultSet.getInt("league_id"));

            teams = new Teams(resultSet.getInt("team_id"),resultSet.getString("team_name"),resultSet.getString("team_location"),resultSet.getString("stadium"),resultSet.getBigDecimal("capacity"),resultSet.getInt("league_id"),league);
            teamsList.add(teams);
        }
        closeDBConnection();
        //populates teh team map with the data from the database
        for (Teams team : teamsList){
            teamMap.put(team.getTeam_name(),team.getId());
        }
        return teamsList;
    }
    // get right team method
    public static String getRightTeam(int leagueID){
        //if the league id is found then return the name of the league
        for(Map.Entry<String,Integer> entry : teamMap.entrySet() ){
            if (entry.getValue() == leagueID){
                return entry.getKey();
            }
        }
        return "";
    }
    // --------------------------------------------------------------------------------------//
    public  static  ObservableList<Players> getPlayers() throws SQLException {
        ObservableList<Players> playersList = FXCollections.observableArrayList();

        Connection conn = getDBConnection();
        preparedStatement = conn.prepareStatement("SELECT * FROM players;");
        resultSet = preparedStatement.executeQuery();

        Players players;
        while (resultSet.next()){
         String playerTeam =  getRightTeam(resultSet.getInt("team_id"));

            players = new Players(resultSet.getInt("player_id"),resultSet.getString("name"),resultSet.getInt("j_number"),resultSet.getInt("age"),resultSet.getInt("team_id"),resultSet.getString("position"),resultSet.getString("nationality"),playerTeam);
            playersList.add(players);
        }
        closeDBConnection();

        return playersList;
    }


//-------------------------------------------------------------------------------------------
    public  static void executeQuery(String query) throws SQLException {

        Connection conn = getDBConnection();

        Statement st;
        st = conn.createStatement();
        st.execute(query);

        closeDBConnection();

    }
// --------------------------------------------------------------------------------------//
public static void closeDBConnection(){
    if (resultSet != null){
        try {
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //psInsert prepareStatement closed
    if (preparedStatement != null){
        try {
            preparedStatement.close();
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

}



}//end of DBconnection class
