import oop.ex2.*;

/**
 * class that represents the aggresive spaceship that tries to hunt other ships down.
 */
public class AggresiveShip extends SpaceShip {
    /*
    data members and constants of this class
     */
    //Constants
    // the threshold angle from which it will fire
    private static final double ANGLE = 0.21;

    /**
     * this method is used to get the ship to move towards the closest ship.
     *
     * @param game SpaceWars object
     */
    private void shipMovement(SpaceWars game) {
        double angleToClosestShip = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        if (angleToClosestShip > 0) {
            this.getPhysics().move(true, LEFT);
        } else if (angleToClosestShip < 0) {
            this.getPhysics().move(true, RIGHT);
        } else {
            this.getPhysics().move(true, FORWARD);
        }
    }

    /**
     * overrides the function from the main class - this ship only tries to shoot.
     * Movement -> fire -> end of round actions.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        double angleToClosestShip = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        this.shipMovement(game);
        if (angleToClosestShip < ANGLE) {
            this.fire(game);
        }
        super.doAction(game);
    }


}
