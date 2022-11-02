package com.example.futbol_manager_app.Utils;

public class GetRightLeague {

    public static String getRightLeague(int leagueID){
      switch (leagueID){
          case  6:
              return "League One";
          case  7:
              return "Premier League";
          case  8:
              return "Serie A";
          case  9:
              return "Bundesliga";
          case  10:
              return "La liga";

      }

        return "";
    }
}
