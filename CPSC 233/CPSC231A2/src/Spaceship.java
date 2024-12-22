/**
 * The abstract class Spaceship represents a generic spaceship entity in the galactic space.
 * It defines common attributes and behaviors for different types of spaceships.
 * @author Parisa Daeijavad
 */
public abstract class Spaceship {

    // attributes:

    // The unique identifier of the spaceship
    String id;
    // The current coordinates of the spaceship
    int x;
    int y;
    // The type of the spaceship
    SpaceshipType type;


    // methods:

    /**
     * Constructs a Spaceship object with the specified attributes.
     *
     * @param...
     *
     */
    // constructor goes here...
    public Spaceship(String id1, int x1,int y1, SpaceshipType type1){
        this.x =x1;
        this.y = y1;
        this.id = id1;
        this.type=type1;
    }
    /**
     * Abstract method to define the movement behavior of the spaceship.
     *
     * @param...
     */
    // move ....
    abstract   void move(GalacticMap galacticMap);
    /**
     * Abstract method to define the interaction behavior of the spaceship with another spaceship.
     *
     * @param....
     */
    // interact ...
    abstract void interact(GalacticMap galacticMap, Spaceship other);
    // Getters and setters

    /**
     * Retrieves the unique identifier of the spaceship.
     *
     * ....
     */
    // getID
    public String getID(){
        return id;
    }

    /**
     * Retrieves the current x-coordinate of the spaceship.
     *
     * ....
     */
    // getX
    public int getX(){
        return x;
    }
    /**
     * Retrieves the current y-coordinate of the spaceship.
     *
     * .....
     */
    // getY
     public int getY(){
         return y;
     }
    /**
     * Retrieves the type of the spaceship.
     *
     * .....
     */
    // getType
     public SpaceshipType getType(){
         return type;
     }
    /**
     * Sets the x-coordinate of the spaceship to the specified value.
     *
     * @param....
     */
    // setX
     public void setX(int newX){
         this.x = newX;
     }
    /**
     * Sets the y-coordinate of the spaceship to the specified value.
     *
     * @param....
     */
    // setY
    public void setY(int newY){
        this.y = newY;
    }

    /**
     * Calculates the distance between this spaceship and another spaceship.
     *
     * @param other The other spaceship to calculate the distance to.
     * @return The distance between this spaceship and the other spaceship.
     */
    public int calculateDistance(Spaceship other) {
        int deltaX = Math.abs(this.getX() - other.getX());
        int deltaY = Math.abs(this.getY() - other.getY());
        return Math.max(deltaX, deltaY);
    }

    /**
     * Retrieves the name of the spaceship.
     *
     * @return The name of the spaceship, which includes its type and ID.
     */
    public String getName() {
        return this.getType() + " " + this.getID();
    }
}
