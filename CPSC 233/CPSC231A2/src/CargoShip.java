/**
 * The CargoShip class represents a spaceship specialized in transporting cargo within the galactic space.
 * It inherits from the Spaceship class and defines specific movement, interaction, and cargo management behaviors.
 * @author Parisa Daeijavad
 */
public class CargoShip extends Spaceship {

    private double cargoCapacity; // Maximum cargo capacity of the CargoShip
    private double currentCargo;  // Current amount of cargo on the CargoShip

    private int targetX; // The x-coordinate of the cargo ship's destination.
    private int targetY; // The y-coordinate of the cargo ship's destination.

    private boolean reachedDestination; // Indicates if the cargo has reached its destination

    /**
     * Constructs a CargoShip object with the specified attributes.
     *
     * @param id            The unique identifier of the cargo ship.
     * @param x             The initial x-coordinate of the cargo ship.
     * @param y             The initial y-coordinate of the cargo ship.
     * @param cargoCapacity The maximum cargo capacity of the cargo ship.
     * @param currentCargo  The current amount of cargo on the cargo ship.
     * @param targetX       The x-coordinate of the cargo ship's destination.
     * @param targetY       The y-coordinate of the cargo ship's destination.
     */
    public CargoShip(String id, int x, int y, double cargoCapacity, double currentCargo, int targetX, int targetY) {
        // Initialize CargoShip attributes properly
        super(id,x,y,SpaceshipType.CARGOSHIP);
        this.cargoCapacity = cargoCapacity;
        this.currentCargo = currentCargo;
        this.targetX = targetX;
        this.targetY = targetY;

    }

    /**
     * Implements the movement behavior of the cargo ship within the galactic map.
     * The cargo ship moves towards its designated target coordinates.
     *
     * ....................
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");

        // CargoShip-specific movement logic

        // using a loop until cargo reaches the target;
            int x = getX();
            int y = getY();
            if (targetX < x){
                x = x-1;
                if (galacticMap.isValidMove(x, y) && !galacticMap.isCollision(x, y)) {
                    System.out.println("Move Configuration");
                    galacticMap.moveSpaceshipTo(this, x, y);
                    setX(x);
                    setY(y);
                    return;
                }
            }
            if(targetX > x){
                x = x+1;
                if (galacticMap.isValidMove(x, y) && !galacticMap.isCollision(x, y)) {
                    System.out.println("Move Configuration");
                    galacticMap.moveSpaceshipTo(this, x, y);
                    setX(x);
                    setY(y);
                    return;
                }
            }
            if(targetX == x & targetY < y ){
                y--;
                if (galacticMap.isValidMove(x, y) && !galacticMap.isCollision(x, y)) {
                    System.out.println("Move Configuration");
                    galacticMap.moveSpaceshipTo(this, x, y);
                    setX(x);
                    setY(y);
                    return;
                }
            }
        if(targetX == x & targetY > y ){
            y++;
            if (galacticMap.isValidMove(x, y) && !galacticMap.isCollision(x, y)) {
                System.out.println("Move Configuration");
                galacticMap.moveSpaceshipTo(this, x, y);
                setX(x);
                setY(y);
                return;
            }
        }

        if(x== targetX & y== targetY) {
            // setting it true if reached!!!!!!!
            setReachedDestination(true);
        }
    }

    /**
     * Implements the interaction behavior of the cargo ship with another spaceship.
     * The cargo ship can exchange cargo with other cargo ships during interaction.
     *
     * ............
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {

        System.out.println(".........interacting...........with.... " + other.getName());

        // CargoShip interaction logic
        if(this == other){
            System.out.println("CargoShip cannot interact with itself");
            return;
        }
        if(other.getType() == SpaceshipType.FIGHTER){
            System.out.println("CargoShip cannot interact with FIGHTER");
            return;
        }
        if(other.getType() == SpaceshipType.EXPLORER){
            System.out.println("CargoShip cannot interact with EXPLORER");
            return;
        }
        // to check if the cargo is greater.....
        CargoShip otherCargoShip = (CargoShip) other;
        double cargoDiff;
        if(this.currentCargo > otherCargoShip.currentCargo){
            cargoDiff = this.currentCargo - otherCargoShip.currentCargo;
            cargoDiff = cargoDiff/2;
            // taking of from this cargo and giving to the other
            this.unloadCargo(cargoDiff);
            otherCargoShip.loadCargo(cargoDiff);
        }
        else if (otherCargoShip.currentCargo > this.currentCargo){
            cargoDiff = otherCargoShip.currentCargo - this.currentCargo;
            cargoDiff = cargoDiff/2;
            // taking from other and adding to this one
            this.loadCargo(cargoDiff);
            otherCargoShip.unloadCargo(cargoDiff);
        }
    }

    /**
     * Loads cargo onto the CargoShip up to its maximum capacity.
     *
     * ...........
     */
    public void loadCargo(double cargoAmount) {
        // using temp to store the new amount
        double temp = currentCargo + cargoAmount;
        if(cargoCapacity >= temp){
            cargoAmount = currentCargo + cargoAmount;
        }
        else if(temp > cargoCapacity) {
            System.out.println("Cargo capacity exceeded! Cannot load cargo onto C-");
        }

    }

    /**
     * Unloads cargo from the CargoShip.
     *
     * ..............
     */
    public void unloadCargo(double cargoAmount) {
        if(cargoAmount<= currentCargo){
            currentCargo = currentCargo - cargoAmount;
        }
        else if ( cargoAmount > currentCargo){
            System.out.println("Cannot unload more cargo than what's currently onboard.");
        }
    }

    /**
     * Retrieves the current amount of cargo on the CargoShip.
     *
     * @return The current cargo amount.
     */
    public double getCurrentCargo() {
        return currentCargo;
    }

    /**
     * Retrieves the x-coordinate of the CargoShip's destination.
     *
     * @return The x-coordinate of the destination.
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * Retrieves the y-coordinate of the CargoShip's destination.
     *
     * @return The y-coordinate of the destination.
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * Sets the status of whether the CargoShip has reached its destination.
     *
     * @param b True if the CargoShip has reached its destination, false otherwise.
     */
    public void setReachedDestination(boolean b) {
        reachedDestination = b;
    }

    /**
     * Checks if the CargoShip has reached its destination.
     *
     * @return True if the CargoShip has reached its destination, false otherwise.
     */
    public boolean isReachedDestination() {
        return reachedDestination;
    }
}

