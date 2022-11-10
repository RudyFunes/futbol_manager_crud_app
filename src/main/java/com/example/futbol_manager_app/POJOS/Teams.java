package com.example.futbol_manager_app.POJOS;

import java.math.BigDecimal;

public class Teams {

    private  int id;
    private  String team_name;
    private  String team_location;
    private  String stadium;
    private BigDecimal capacity;
    private  int league_id;

    private String league;

    public Teams(int id, String team_name, String team_location, String stadium, BigDecimal capacity, int league_id, String league) {
        this.id = id;
        this.team_name = team_name;
        this.team_location = team_location;
        this.stadium = stadium;
        this.capacity = capacity;
        this.league_id = league_id;
        this.league = league;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_location() {
        return team_location;
    }

    public void setTeam_location(String team_location) {
        this.team_location = team_location;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public BigDecimal getCapacity() {

        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {

        this.capacity = capacity;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "Teams{" +
                "id=" + id +
                ", team_name='" + team_name + '\'' +
                ", team_location='" + team_location + '\'' +
                ", stadium='" + stadium + '\'' +
                ", capacity=" + capacity +
                ", league_id=" + league_id +
                ", league='" + league + '\'' +
                '}';
    }
}
