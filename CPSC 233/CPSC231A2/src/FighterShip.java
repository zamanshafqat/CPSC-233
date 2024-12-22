import java.util.Random;
/**
 * The FighterShip class represents a spaceship specialized in combat within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class FighterShip extends Spaceship {
    private int damage;// The damage inflicted by the fighter ship during combat

    /**
     * Constructs a FighterShip object with the specified attributes.
     *
     * @param id     The unique identifier of the fighter ship.
     * @param x      The initial x-coordinate of the fighter ship.
     * @param y      The initial y-coordinate of the fighter ship.
     * @param damage The damage inflicted by the fighter ship during combat.
     */
    public FighterShip(String id, int x, int y, int damage) {
        // Initialize FighterShip attributes
        super(id, x, y, SpaceshipType.FIGHTER);
        this.damage=damage;
    }

    /**
     * Generates a random direction for the fighter ship to move.
     *
     * @return A random integer representing the direction (0 to 7).
     */
    private int getRandomDirection() {
        Random random = new Random();
        return random.nextInt(8); // Generates a random integer between 0 and 7
    }

    /**
     * Implements the movement behavior of the fighter ship within the galactic map.
     * The movement of the fighter ship is random, as it changes direction randomly.\
     *
     * ..............
     *
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");
        int x1 = this.getX();
        int y1 = this.getY();
        int move = getRandomDirection();
        // using switch to go through each option....
        switch (move){
            case 0:
                x1--;
                y1++;
                break;
            case 1:
                x1++;
                y1++;
                break;
            case 2:
                y1++;
                break;
            case 3:
                y1--;
                break;
            case 4:
                x1--;
                y1--;
                break;
            case 5:
                x1++;
                y1--;
                break;
            case 6:
                x1--;
                break;
            case 7:
                x1++;
                break;
        }
        // setting x and y after the case....
        // checking also if valid more and no collision....
        if (galacticMap.isValidMove(x1, y1) && !galacticMap.isCollision(x1, y1)) {
            System.out.println("Move Configuration:");
            galacticMap.moveSpaceshipTo(this,x1,y1);
            this.setX(x1);
            this.setY(y1);
        }
        // Implementation for fighter ship movement

    }

    /**
     * Implements the interaction behavior of the fighter ship with another spaceship.
     * The fighter ship engages in combat with other spaceships during interaction.
     *
     * .........
     *
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());
        if(this == other || other.getType() == SpaceshipType.FIGHTER ){
            System.out.println("fighters do not fight with fighters!");
            return;
        }
        // calculate distance from the other spaceship...
        int distance = calculateDistance(other);
        if(damage < distance){
            System.out.println("damage is less than distance!");
        }
        else {
            System.out.println("Interaction Configuration");
            System.out.println(this.getName() + " destroyed spaceship: " +other.getName());
            galacticMap.removeSpaceshipAt(other.getX(), other.getY());
        }
        // finally printing everything
        System.out.println(toString());
        // Implementation for fighter ship interaction (e.g., combat)
    }
}

