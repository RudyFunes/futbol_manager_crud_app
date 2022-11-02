package com.example.futbol_manager_app.POJOS;

public class Leagues {

    private  int id;
    private  String country;
    private  String league;
    private int year;


    public Leagues(int id, String country, String league, int year) {
        this.id = id;
        this.country = country;
        this.league = league;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Leagues{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", year=" + year +
                '}';
    }
}
