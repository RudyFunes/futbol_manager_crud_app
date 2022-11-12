package com.example.futbol_manager_app.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static ArrayList<String> dbCredentials= new ArrayList<>();

    public  static ArrayList<String> getCredentials(){

//        File file = new File("C:\\Users\\rudym\\Desktop\\MairaDB\\MariaDBCredentials.txt");  // local Maria db
        File file = new File("C:\\Users\\rudym\\Desktop\\AWS\\MariaDB-Server\\Conetion Credentials\\MariaDB_User_Credentials.txt");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()){
                dbCredentials.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Something wrong while reading the file");
        }

        return dbCredentials;
    }

}
