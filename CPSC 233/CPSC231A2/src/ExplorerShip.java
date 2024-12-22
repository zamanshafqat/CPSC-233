/**
 * The ExplorerShip class represents a spaceship specialized in exploration within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class ExplorerShip extends Spaceship {
    private int scanRange; // The range within which the explorer ship can scan for nearby spaceships
    private boolean moveHorizontally = true; // Flag to track horizontal movement

    /**
     * Constructs an ExplorerShip object with the specified attributes.
     *
     * @param id        The unique identifier of the explorer ship.
     * @param x         The initial x-coordinate of the explorer ship.
     * @param y         The initial y-coordinate of the explorer ship.
     * @param scanRange The range within which the explorer ship can scan for nearby spaceships.
     */
    public ExplorerShip(String id, int x, int y, int scanRange) {
        super(id, x, y, SpaceshipType.EXPLORER);
        this.scanRange = scanRange;
    }

    /**
     * Implements the movement behavior of the explorer ship within the galactic map.
     * The explorer ship moves in a zigzag pattern, alternating between horizontal and vertical movements.
     *
     * .........
     *
     */
    @Override
    // when the move_horizontally is true x is incremented otherwise y is incremented;
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");

        int x = getX();
        int y = getY();
        if (moveHorizontally) {
            x = x + 1;
            moveHorizontally = false;

        } else {
            y++;
            moveHorizontally = true;
        }

        if (galacticMap.isValidMove(x, y) && !galacticMap.isCollision(x, y)) {
            System.out.println("Move Configuration:");
            galacticMap.moveSpaceshipTo(this,x,y);
            setX(x);
            setY(y);
        }


        // Implementation for explorer ship movement
        // they move in a zigzag pattern, alternating between horizontal and vertical movements.
    }
    /**
     * Implements the interaction behavior of the explorer ship with another spaceship.
     * The explorer ship reports nearby spaceships found within its scan range during interaction.
     *
     * ......
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());
        if (this == other) {
            System.out.println("The spaceship cannot interact with itself.");
            return;
        }
        // calculating distance from spaceship class method
        int distance = calculateDistance(other);
        if (distance <= scanRange) {
            System.out.println("Found: " + other.getName() +
                    " at distance: " + distance);
            if(other.getType()==SpaceshipType.FIGHTER){
                galacticMap.reportList.add(other);
            }

        } else {
            System.out.println("Spaceship: " + other.getName() +
                    " is not in the scan-range");
        }
        // finally printing everything
        System.out.println(toString());
        // Implementation for explorer ship interaction
    }
}
