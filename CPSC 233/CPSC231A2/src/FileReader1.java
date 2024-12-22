
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLOutput;


/**
 * The FileReader class provides static methods for reading data from a file and constructing a GalacticMap object.
 * It reads a text file containing information about spaceships and their attributes, and initializes a GalacticMap
 * based on the data read from the file.
 *
 * @author Parisa Daeijavad
 *
 */

public class FileReader1 {

    /**
     * Reads data from a specified file and constructs a GalacticMap object based on the information read.
     *
     * @param fileName the name of the file to read from
     * @return a GalacticMap object initialized with data read from the file
     * @throws RuntimeException if the file specified by fileName is not found or if an error occurs while reading the file
     */

    public static GalacticMap readFromFile(String fileName) {
        // Your code goes here ....
        GalacticMap galacticMap = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            line = bufferedReader.readLine();
            // creating the grid of the size;
            int size = Integer.parseInt(line);
            validateMapSize(line);
            galacticMap  = new GalacticMap(size);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Invalid data format: Missing spaceship attributes.");
                }
                String type = parts[0];
                String id = parts[1];
                int x = Integer.parseInt(parts[2]);
                int y = Integer.parseInt(parts[3]);

                switch (type) {
                    case "FIGHTER":
                        validateNumericAttribute(parts[4]);
                        int damage = Integer.parseInt(parts[4]);
                        FighterShip fighter = new FighterShip(id, x, y, damage);
                        galacticMap.placeSpaceship(fighter);
                        continue;
                    case "EXPLORER":
                        validateNumericAttribute(parts[4]);
                        int scanRange = Integer.parseInt(parts[4]);
                        ExplorerShip explorer = new ExplorerShip(id, x, y, scanRange);
                        galacticMap.placeSpaceship(explorer);
                        continue;
                    case "CARGOSHIP":
                        if (parts.length < 8) {
                            throw new IllegalArgumentException("Invalid data format: Missing cargo ship attributes.");
                        }
                        validateNumericAttribute(parts[4]);
                        validateNumericAttribute(parts[5]);
                        validateNumericAttribute(parts[6]);
                        validateNumericAttribute(parts[7]);
                        int cargoCapacity = Integer.parseInt(parts[4]);
                        int currentCargo = Integer.parseInt(parts[5]);
                        int targetX = Integer.parseInt(parts[6]);
                        int targetY = Integer.parseInt(parts[7]);
                        CargoShip cargo = new CargoShip(id, x, y, cargoCapacity, currentCargo, targetX, targetY);
                        galacticMap.placeSpaceship(cargo);
                        continue;
                    default:
                        throw new IllegalArgumentException("Invalid spaceship type: " + type);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("File not found:" + fileName);

        }
        return galacticMap;
        // hint: you need to call placeSpaceship method...

    }

    private static void validateNumericAttribute(String attribute) {
        try {
            Integer.parseInt(attribute);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid data format: Unable to parse numeric value.");
        }
    }

    private static void validateMapSize(String size) {
        try{
            Integer.parseInt(size);

        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid file format: Missing map size.");
        }
    }
}