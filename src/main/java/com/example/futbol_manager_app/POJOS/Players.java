package com.example.futbol_manager_app.POJOS;

import java.math.BigDecimal;

public class Players {
    private  int id;
    private  String player_name;
    private  int player_number;
    private  int player_age;
    private  int team_id;
    private  String player_position;
    private String player_country;


    public Players(int id, String player_name, int player_number, int player_age, int team_id, String player_position, String player_country) {
        this.id = id;
        this.player_name = player_name;
        this.player_number = player_number;
        this.player_age = player_age;
        this.team_id = team_id;
        this.player_position = player_position;
        this.player_country = player_country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getPlayer_number() {
        return player_number;
    }

    public void setPlayer_number(int player_number) {
        this.player_number = player_number;
    }

    public int getPlayer_age() {
        return player_age;
    }

    public void setPlayer_age(int player_age) {
        this.player_age = player_age;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getPlayer_position() {
        return player_position;
    }

    public void setPlayer_position(String player_position) {
        this.player_position = player_position;
    }

    public String getPlayer_country() {
        return player_country;
    }

    public void setPlayer_country(String player_country) {
        this.player_country = player_country;
    }


    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", player_name='" + player_name + '\'' +
                ", player_number=" + player_number +
                ", player_age=" + player_age +
                ", team_id=" + team_id +
                ", player_position='" + player_position + '\'' +
                ", player_country='" + player_country + '\'' +
                '}';
    }
}
