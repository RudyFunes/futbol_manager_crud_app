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
    private static ResultSet resultSet = null;

    //Maps to store league and teams
    private static HashMap<Integer,String> leaguesMap = new HashMap<>();
    private static HashMap<Integer, String> teamMap = new HashMap<>();



    public static Connection getDBConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ArrayList<String> dbCredentials = FileHandler.getCredentials();
//            System.out.println(dbCredentials.get(5));  // see what is inse the file
            connection = DriverManager.getConnection( dbCredentials.get(5), dbCredentials.get(6), dbCredentials.get(7));

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
        preparedStatement = conn.prepareStatement("SELECT * FROM league;");
        resultSet = preparedStatement.executeQuery();

        Leagues leagues;
        while (resultSet.next()){
            leagues = new Leagues(resultSet.getInt("league_id"),resultSet.getString("country"),resultSet.getString("league_name"),resultSet.getInt("year"));
            leaguesList.add(leagues);
        }
        closeDBConnection();
        //populates teh league map with the data from the database
        for (Leagues league : leaguesList){
            leaguesMap.put(league.getId(),league.getLeague());
        }
        return leaguesList;
    }
    // get right league method
    public static String getRightLeague(int leagueID){

        //if the league id is found then return the name of the league
        for(Map.Entry<Integer,String> entry : leaguesMap.entrySet() ){
            if (entry.getKey() == leagueID){
                return entry.getValue();
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
            teamMap.put(team.getId(),team.getTeam_name());
        }
        return teamsList;
    }
    // get right team method
    public static String getRightTeam(int leagueID){
        //if the league id is found then return the name of the league
        for(Map.Entry<Integer,String> entry : teamMap.entrySet() ){
            if (entry.getKey() == leagueID){
                return entry.getValue();
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
