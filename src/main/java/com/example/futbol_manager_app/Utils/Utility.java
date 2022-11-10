package com.example.futbol_manager_app.Utils;

import com.example.futbol_manager_app.POJOS.Teams;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;


public class Utility  extends TextField {

    public static boolean checkIfHasOnlyDigits(String str){
        for (int i = 0; i < str.length(); i++) {

            // Check if character is
            // not a digit between 0-9
            // then return false
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        // If we reach here, that means
        // all characters were digits.
        return true;
    }

    /*  Checks witch buttons was pushed and returns it an int  which is the position of the button in the array list. */
    public static int getRightButton(Event event, ArrayList<Button> buttons){
        if(event.getSource() == buttons.get(0)){
            return 0;
        }
        if(event.getSource() == buttons.get(1)){
            return 1;
        }
        if(event.getSource() == buttons.get(2)){
            return 2;
        }
        if(event.getSource() == buttons.get(3)){
            return 3;
        }
        if(event.getSource() == buttons.get(4)){
            return 4;
        }
        if(event.getSource() == buttons.get(5)){
            return 5;
        }
        if(event.getSource() == buttons.get(6)){
            return 6;
        } if(event.getSource() == buttons.get(7)){
            return 7;
        }
        if(event.getSource() == buttons.get(8)){
            return 8;
        }
        if(event.getSource() == buttons.get(9)){
            return 9;
        }
        if(event.getSource() == buttons.get(10)){
            return 10;
        } if(event.getSource() == buttons.get(11)){
            return 11;
        } if(event.getSource() == buttons.get(12)){
            return 12;
        }
        if(event.getSource() == buttons.get(13)){
            return 13;
        }

        return 20;
    }

    public static int checkIfTeamLeagueExist(String league, ObservableList<Teams> teams){

//        HashMap<String,Integer> league


        return 0;
    }


}
