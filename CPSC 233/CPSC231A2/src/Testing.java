import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class Testing {
    @Test
    // created a test galactic map making sure the file is read properly
    // --------------test file-----------------
    //5
    //FIGHTER f1342 1 1 2
    //EXPLORER e8263 3 3 2
    //CARGO SHIP c1334 3 4 10 7 0 1
    //FIGHTER e8273 1 3 1
    //EXPLORER e7263 4 3 2

    public void FileReaderTestGridSize(){
        // testing if the size of the grid is same
        GalacticMap galacticMap = FileReader1.readFromFile("config.txt");
        FighterShip fighter = new FighterShip("f1342", 1, 1, 2);
        FighterShip fighter2 = new FighterShip("e8273", 1, 3, 1);
        ExplorerShip explorer1 = new ExplorerShip("e8263", 3, 3, 2);
        ExplorerShip explorer2 = new ExplorerShip("e7263", 4, 3, 2);
        CargoShip cargo = new CargoShip("c1334",3,4,10,7,0,1);
        Spaceship[][] actual = galacticMap.grid;


        Spaceship[][] expected = {{null,null,null,null,null},
                {null,fighter,null,fighter2,null},
                {null,null,null,null,null} ,
                {null,null,null,explorer1,null},
                {null,null,null,explorer2,cargo}};

        assertEquals(expected.length,actual.length);

    }
    //testFile
    //5
    //FIGHTER f1342 1 1 2
    //EXPLORER e8263 3 3 2
    //CARGOSHIP c1334 3 4 10 7 0 1
    //FIGHTER e8273 1 3 1
    //EXPLORER e7263 4 3 2
    @Test
    public void fileReaderTestFighterTest(){
        // testing for the Fighter1 type id and cordinates are same;
        GalacticMap galacticMap = FileReader1.readFromFile("config.txt");
        Spaceship fighter = new FighterShip("f1342", 1, 1, 2);
        // checking if all spaceship are same..for the first fighter
        Spaceship expectedFighter = galacticMap.grid[1][1];
        SpaceshipType type = expectedFighter.getType();
        String id = expectedFighter.getID();
        int x = expectedFighter.getX();
        int y = expectedFighter.getY();
        assertEquals(type,SpaceshipType.FIGHTER);
        assertEquals(id,"f1342");
        assertEquals(x,1);
        assertEquals(y,1);

    }
    //testFile
    //    //5
    //    //FIGHTER f1342 1 1 2
    //    //EXPLORER e8263 3 3 2
    //    //CARGOSHIP c1334 3 4 10 7 0 1
    //    //FIGHTER e8273 1 3 1
    //    //EXPLORER e7263 4 3 2
    @Test
    public void fileReaderTestExplorer(){
        // testing for the explorer 1 on the grid
        GalacticMap galacticMap = FileReader1.readFromFile("config.txt");
        ExplorerShip explorer1 = new ExplorerShip("e8263", 3, 3, 2);
        Spaceship expectedExplorer = galacticMap.grid[3][3];
        SpaceshipType type = expectedExplorer.getType();
        String id = expectedExplorer.getID();
        int x = expectedExplorer.getX();
        int y = expectedExplorer.getY();
        assertEquals(type,SpaceshipType.EXPLORER);
        assertEquals(id,"e8263");
        assertEquals(x,3);
        assertEquals(y,3);

    }
    //---------------------------------------------------------------------------------------
    //GalacticMap.toString
    @Test
    // explorer is added to grid and checking if printed
    public void GalacticMapToStringTest1(){
        GalacticMap galacticMap = new GalacticMap(5);
        ExplorerShip explorer1 = new ExplorerShip("e8263", 3, 3, 2);
        galacticMap.grid[3][3] = explorer1;
        String output = galacticMap.toString();
        // now check if the file if read properly
        assertTrue(output.contains("[         ][         ][         ][         ][         ]"));
        assertTrue(output.contains("[         ][         ][         ][         ][         ]"));
        assertTrue(output.contains("[         ][         ][         ][         ][         ]"));
        assertTrue(output.contains("[         ][         ][         ][ E-e8263 ][         ]"));
        assertTrue(output.contains("[         ][         ][         ][         ][         ]"));


    }
    @Test
    // fighter is added to grid and checking if printed
    public void GalacticMapToStringTest2(){
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighter = new FighterShip("f1234", 1, 0, 2);
        galacticMap.grid[1][0] = fighter;
        ExplorerShip explorer1 = new ExplorerShip("e8263", 3, 3, 2);
        galacticMap.grid[3][3] = explorer1;
        String output = galacticMap.toString();
        // now check if the file if read properly
        assertTrue(output.contains("[         ][         ][         ][         ][         ]"));
        assertTrue(output.contains("[ F-f1234 ][         ][         ][         ][         ]"));
        assertTrue(output.contains("[         ][         ][         ][         ][         ]"));
        assertTrue(output.contains("[         ][         ][         ][ E-e8263 ][         ]"));
        assertTrue(output.contains("[         ][         ][         ][         ][         ]"));


    }
    @Test
    public void galacticMapToStringTest3(){
       // check all of the empty brackets are not greater or less than 9 spaces
        GalacticMap galacticMap = new GalacticMap(5);
        String output = galacticMap.toString();
        // here the spaces are 8
        assertFalse(output.contains("[        ]"));
        // here the spaces are 10
        assertFalse(output.contains("[          ]"));
    }
    @Test
    // using move function and checking if fighter change position;
    public void FighterShipMove(){
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighter = new FighterShip("f1342", 1, 1, 2);
        galacticMap.grid[1][1] = fighter;
        fighter.move(galacticMap);
        // checking if last position is set to null;
        assertEquals(galacticMap.grid[1][1],null);
    }
    @Test
    public  void FighterShipMove2() {
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighter = new FighterShip("f1342", 1, 1, 2);
        galacticMap.grid[1][1] = fighter;
        fighter.move(galacticMap);
        int y = fighter.getY();
        int x = fighter.getX();
        // to check if the fighter is placed at new position by using not equal
        assertNotEquals(null, galacticMap.grid[x][y]);
    }
    @Test
    // using interat method on the Fighter interct file and checking the method response
    public void FighterShipInterct(){
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighter = new FighterShip("f1342", 1, 1, 2);
        FighterShip fighter2 = new FighterShip("e8273", 2, 2, 2);
        galacticMap.grid[1][1] = fighter;
        galacticMap.grid[2][2] = fighter2;
        //
        // using fighter to interact to fighter
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        fighter.interact(galacticMap,galacticMap.getSpaceshipAt(2,2));

        System.setOut(originalOut);
        String output = outputStream.toString().trim();
        assertTrue(output.contains(".........interacting...........with.... FIGHTER e8273"));
        assertTrue(output.contains("fighters do not fight with fighters!"));
    }
    @Test
    // damge is set to 10
    public void FighterShInteractCargo(){
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighter = new FighterShip("f1342", 1, 1, 10);
        CargoShip cargo = new CargoShip("c1334",3,4,10,7,0,1);
        fighter.interact(galacticMap,cargo);
        // now check if the cargo is removed.
        assertEquals(galacticMap.grid[3][4],null);
    }
    @Test
    // explorerTestTXT
    //5
    //FIGHTER f1342 1 1 10
    //EXPLORER e8263 4 4 2
    //CARGOSHIP c1334 3 3 10 7 0 1
    //FIGHTER e8273 1 4 1
    //EXPLORER e7263 1 3 2
    public void explorerShipMoveTest(){
        // test to check if explorer ship not removed from it last position if out of bound move
        GalacticMap galacticMap = new GalacticMap(5);
        ExplorerShip explorer = new ExplorerShip("e7263", 4, 4, 2);
        galacticMap.grid[4][4] = explorer;
        explorer.move(galacticMap);
        // checking if last spaceship not moved;
        assertEquals(galacticMap.grid[4][4],explorer);
    }
    @Test
    public void explorerShipMoveTest2(){
        // test to check if removed from last position
        GalacticMap galacticMap = new GalacticMap(5);
        ExplorerShip explorer = new ExplorerShip("e7263", 2, 2, 2);
        galacticMap.grid[2][2] = explorer;
        explorer.move(galacticMap);
        // checking if last position is set to null;
        assertEquals(galacticMap.grid[2][2],null);
    }
    @Test

    // check what the program does when interacting with itself
    public void explorerShipInteractTest1(){
        GalacticMap galacticMap = new GalacticMap(5);
        ExplorerShip explorer = new ExplorerShip("e7263", 4, 3, 2);
        galacticMap.grid[4][3] = explorer;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        explorer.interact(galacticMap,explorer);

        System.setOut(originalOut);
        String output = outputStream.toString().trim();

        assertTrue(output.contains("The spaceship cannot interact with itself."));

    }
    @Test
    //testFile

    // to check what the program does when the other spaceship is not in range
    public void explorerShipInteractTest2(){
        GalacticMap galacticMap = new GalacticMap(5);
        ExplorerShip explorer = new ExplorerShip("e7263", 3, 3, 1);
        ExplorerShip other = new ExplorerShip("e8263", 1, 1, 2);
        galacticMap.grid[3][3] = explorer;
        galacticMap.grid[3][4] = other;
        // 3,3 is not in range with explorer in 3,4
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        explorer.interact(galacticMap,other);

        System.setOut(originalOut);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Spaceship: " + other.getName() +
                " is not in the scan-range"));

    }
    @Test
    public void cargoShipInteractTest1(){
        // checking if cargo interacts with explorer
        GalacticMap galacticMap = new GalacticMap(5);
        CargoShip cargo1 = new CargoShip("c1234",2,3,1000,500,1,1);
        galacticMap.grid[2][3]=cargo1;
        ExplorerShip explorer = new ExplorerShip("e7263", 3, 3, 1);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        galacticMap.grid[3][3] = explorer;
        cargo1.interact(galacticMap,explorer);

        System.setOut(originalOut);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("CargoShip cannot interact with EXPLORER"));

    }
    @Test
    public void cargoShipInteractTest2(){
        // checking if cargo interacts with itself
        GalacticMap galacticMap = new GalacticMap(5);
        CargoShip cargo1 = new CargoShip("c1234",2,3,1000,500,1,1);
        galacticMap.grid[2][3]=cargo1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        cargo1.interact(galacticMap,cargo1);

        System.setOut(originalOut);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("CargoShip cannot interact with itself"));

    }
    @Test
    // using default spaceship class to move explorer!!!!
    public void spaceShipMoveTest(){
        GalacticMap galacticMap = new GalacticMap(5);
        Spaceship spaceship = new ExplorerShip("1234",1,2,2);
        galacticMap.grid[1][2] = spaceship;
        spaceship.move(galacticMap);
        assertEquals(null,galacticMap.grid[1][2]);

    }


}
