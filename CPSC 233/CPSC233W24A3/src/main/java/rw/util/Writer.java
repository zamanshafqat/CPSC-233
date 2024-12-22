package rw.util;
import rw.battle.*;
import java.io.FileWriter;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Writer {
    public static void createBattle(File file, int row, int col, int row1, int col1, int row2, int col2, char symbol, String name, int health, String Weapon, String symbol2, String name2, int health2, int Weapon2, int armor2) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            fileWriter.write(String.valueOf(row));
            fileWriter.newLine();
            fileWriter.write(String.valueOf(col));
            fileWriter.newLine();

            if (name != null) {

                /// saving the predacon
                fileWriter.write(row1 + "," + col1 + "," + "PREDACON" + "," + symbol + "," + name + "," + health + "," + Weapon);
            }


            fileWriter.newLine();

            if (name2 != null) {


                // saving the maximals
                fileWriter.write(row2 + "," + col2 + "," + "Maximal" + "," + symbol2 + "," + name2 + "," + health2 + "," + Weapon2 + "," + armor2);
            }


            fileWriter.close();
            ;
        } catch (IOException e) {
            System.err.println("Error occurred while appending to the file: " + e.getMessage());
        }
    }

}
