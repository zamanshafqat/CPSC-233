package rw.util;

import rw.battle.*;
import rw.enums.Symbol;
import rw.enums.WeaponType;

import java.io.*;
import java.util.Scanner;

/**
 * Class to assist reading in battle file
 *
 * @author Jonathan Hudson
 * @version 1.0
 */
public final class Reader {


    public static Battle loadBattle(File file) {
        Battle battle = null;
        int col =0;
        int row =0;
        boolean rowfound = false;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 1 && !rowfound){
                    row =Integer.parseInt(parts[0]);
                    rowfound = true;
                    continue;
                }
                if (parts.length == 1 && rowfound){
                    col =Integer.parseInt(parts[0]);
                    // here i created the map with the row and columns
                    battle = new Battle(row,col);
                    continue;
                }
                if(parts.length == 3 && parts[2].equals("WALL")){
                    row = Integer.parseInt(parts[0]);
                    col = Integer.parseInt(parts[1]);
                    Entity wall = new Wall();
                    battle.addEntity(row,col,wall);
                   continue;
                }
                if(parts.length == 7 && parts[2].equals("PREDACON")){
                    row = Integer.parseInt(parts[0]);
                    col = Integer.parseInt(parts[1]);
                    String string = parts[3];
                    char symbol = string.charAt(0);
                    String name = parts[4];
                    int health = Integer.parseInt(parts[5]);
                    String weapon = parts[6];
                    WeaponType weapon1 = null;
                    if(weapon.equals("C")){
                         weapon1 = WeaponType.CLAWS;
                    }
                    if(weapon.equals("L")){
                        weapon1 = WeaponType.LASER;
                    }
                    if(weapon.equals("T")){
                        weapon1 = WeaponType.TEETH;
                    }
                    Robot PredaCon = new PredaCon(symbol,name,health,weapon1);
                    battle.addEntity(row,col,PredaCon);
                    continue;
                }
                if(parts.length == 8 && parts[2].equals("MAXIMAL")) {
                    row = Integer.parseInt(parts[0]);
                    col = Integer.parseInt(parts[1]);
                    String string = parts[3];
                    char symbol = string.charAt(0);
                    String name = parts[4];
                    int health = Integer.parseInt(parts[5]);
                    int attack = Integer.parseInt(parts[6]);
                    int armor = Integer.parseInt(parts[7]);
                    Robot Maximal = new Maximal(symbol, name, health,attack,armor);
                    battle.addEntity(row,col,Maximal);
                    continue;

                }
            }

        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            System.err.println("File not found: " + file);

        }

        return battle;
    }
}