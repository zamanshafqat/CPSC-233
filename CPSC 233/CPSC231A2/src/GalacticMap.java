import java.util.HashSet;
import java.util.Random;
/**
 * The GalacticMap class represents the grid-based map of the galactic space.
 * It stores information about the positions of spaceships and provides methods
 * for managing the entities within the map.
 *
 * <p>This class contains functionality to add fighters, retrieve a list of reported spaceships,
 * and check three game winning conditions for the galactic space.</p>
 *
 * @author Parisa Daeijavad
 */
public class GalacticMap {
    public Spaceship[][] grid; // 2D array representing the grid of the galactic map
    private int fighterNumber = 0; // Counter for the number of fighters in the map
    public HashSet<Spaceship> reportList = new HashSet<Spaceship>(); // Set to store reported fighter spaceships

    /**
     * Constructs a GalacticMap object with the specified size.
     *
     * @param size The size of the grid for the galactic map.
     */
    public GalacticMap(int size) {
        this.grid = new Spaceship[size][size];
    }

    /**
     * Adds a fighter spaceship to the report list.
     *
     * @param fighter The fighter spaceship to add to the report list.
     */
    public void AddReportedFighter(FighterShip fighter) {
        reportList.add(fighter);
    }

    /**
     * Adds one to the count of fighters in the GalacticMap.
     */
    public void addOneFighter() {
        fighterNumber++;
    }

    /**
     * Retrieves the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to retrieve the spaceship.
     * @param y The y-coordinate of the position to retrieve the spaceship.
     * @return The spaceship at the specified coordinates.
     */
    public Spaceship getSpaceshipAt(int x, int y) {
        return this.grid[x][y];
    }

    /**
     * Retrieves a random spaceship from the GalacticMap.
     *
     * @return A random spaceship from the GalacticMap.
     */
    public Spaceship getRandomSpaceship() {
        Random random = new Random();
        Spaceship randomSpaceship = null;

        // Keep generating random coordinates until a non-null grid cell is found
        while (randomSpaceship == null) {
            int randomX = random.nextInt(grid.length);
            int randomY = random.nextInt(grid[0].length);
            randomSpaceship = grid[randomX][randomY];
        }

        return randomSpaceship;
    }

    /**
     * Returns a string representation of the GalacticMap.
     *
     * ...............
     *
     */
    @Override
    public String toString() {
        // using 2d loop to create string of the array
        String gridString = "";
        for (Spaceship[] row : grid) {
            for (Spaceship spaceship : row) {
             if (spaceship == null){
                 gridString = gridString + "[         ]";
             }
             else {
                 String explorer =spaceship.getType().name();
                 gridString = gridString + "[" + " " + explorer.charAt(0)+"-"+spaceship.getID() + " "+ "]";
             }

         }
     gridString = gridString + "\n";
     }
    return gridString;
    }

    /**
     * Removes the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to remove the spaceship.
     * @param y The y-coordinate of the position to remove the spaceship.
     */
    public void removeSpaceshipAt(int x, int y) {
        this.grid[x][y] = null;
    }

    /**
     * Moves the specified spaceship to the new coordinates in the GalacticMap.
     *
     * ........
     *
     */
    public void moveSpaceshipTo(Spaceship spaceship, int newX, int newY) {
        // ....
        int oldX = spaceship.getX();
        int oldY = spaceship.getY();
        removeSpaceshipAt(oldX,oldY);
        // after removing just place at new location
        grid[newX][newY] = spaceship;

        System.out.println(toString());
    }
    /**
     * Checks if the specified coordinates represent a valid move within the GalacticMap grid.
     *
     * ............
     *
     */
    public boolean isValidMove(int newX, int newY) {
        // Check if the new position is within the grid boundaries
        // you have to minus one from .length
        boolean validMove = true;
        if (newY < 0 || newY > grid.length-1) {
            validMove = false;
            System.out.print("Moving Failed! out of bound x or y!");
        }
        if (newX < 0 || newX > grid[0].length-1) {
            validMove = false;
            System.out.print("Moving Failed! out of bound x or y!");
        }
        return validMove;
    }

    /**
     * Checks if the specified coordinates represent a collision with another spaceship.
     *
     * .......
     *
     */
    public boolean isCollision(int newX, int newY) {
        // Check if the new position is occupied by another spaceship
        boolean collision = false;
        if(isValidMove(newX, newY)) {
            if (grid[newX][newY] != null) {
                collision = true;
                System.out.println("Moving Failed! the position is filled with another spaceship!");
            }
        }
     return collision;
    }

    /**
     * Places the specified spaceship, that is read from the file, in the GalacticMap.
     *
     * .............
     *
     */
    public void placeSpaceship(Spaceship spaceship) {
        // Place the spaceship in its position
        int newX = spaceship.getX();
        int newY = spaceship.getY();
        // adding fighter to list
        if(spaceship.getType() == SpaceshipType.FIGHTER ){
            addOneFighter();
        }
        boolean collision = isCollision(newX,newY);
        boolean move = isValidMove(newX,newY);
        if(collision){
            throw new IllegalArgumentException("Wrong input file! the position is filled with another item!");
        }
        if(!move){
            throw new ArrayIndexOutOfBoundsException("Wrong input file! position is outside of the map!");
        }
        grid[newX][newY] = spaceship;
        // here at the location take from file the spaceship if place
    }

    /**
     * Checks if all cargoes have reached their destinations.
     *
     * ..............
     *
     */
    public boolean allCargoesReachedDestination() {
        //...
        // Check if all cargoes have reached their destination
        //...
        // this might look in-efficient but i believe its the best way to implement this....
        boolean found = false;
        for (Spaceship[] row : grid) {
            for (Spaceship spaceship : row) {
                if(spaceship != null) {
                    if (spaceship.getType() == SpaceshipType.CARGOSHIP) {
                        // now check if the cargo reached the destination
                        CargoShip cargoShip = (CargoShip) spaceship;
                        if (cargoShip.isReachedDestination()) {
                            found = true;
                        }
                        else{
                            found = false;
                        }
                    }
                }
           }
        }
        return found;
    }

    /**
     * Checks if all explorers and cargoes have been removed by fighters.
     *
     * ............
     *
     */
    // right now only checks if all cargos removed
    public boolean allExplorersAndCargoesRemoved(){
        boolean allRemoved = true;

        for (Spaceship[] row : grid) {
            for (Spaceship spaceship : row) {
                if(spaceship != null) {
                    if (spaceship.getType() == SpaceshipType.CARGOSHIP || spaceship.getType() == SpaceshipType.EXPLORER ) {
                        allRemoved = false;
                    }

                }
            }
        }
        return allRemoved;
    }

    /**
     * Checks if all fighters have been reported by explorers.
     *
     * ................
     *
     */
    // using double looping first to find explorer and then find all fighter and report
    public boolean allFightersReported() {

        // basically in the explorer interact function i check if the fighter has been reported and then
        // from the place spaceship method check how many fighters were there in the map;
        boolean reported = false;
            if (reportList.size() == fighterNumber) {
                reported = true;
            }


        return reported;
    }
}
