/**
 * this ship does not accelerate, it turns left or right, teleports and fire.
 * its turning is based on the aggresive ship and its teleporting is based on the runner ship so it can
 * avoid damage if enemy gets to close. it has also better regenerative ability ( 5 times better than
 * normal ship)
 */
public class SpecialShip extends SpaceShip {
    /*
   data members and constants of this class
    */
    //Constants
    // the threshold angle from which it will fire
    private static final double FIRE_ANGLE = 0.21;
    // the threshold angle from which it will teleport
    private static final double TELEPORT_ANGLE = 0.23;
    // the threshold distance from which it will teleport
    private static final double TELEPORT_DISTANCE = 0.25;

    /**
     * the movement function for this ship - it turns its head towards the closest ship but does not
     * accelerate.
     */
    private void shipMovement(double angle) {
        if (angle > 0) {
            this.getPhysics().move(false, LEFT);
        } else if (angle < 0) {
            this.getPhysics().move(false, RIGHT);
        }
    }

    /**
     * it tries to teleport it enemy ship is to close, it fires according to aggresive ship logic.
     * it regenerates 5 times more energy each round and the shot cooldown is 5 times faster than normal ship.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        double angleToClosestShip = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        double distanceToClosestShip =
                game.getClosestShipTo(this).getPhysics().distanceFrom(this.getPhysics());
        if (distanceToClosestShip < TELEPORT_DISTANCE && angleToClosestShip < TELEPORT_ANGLE) {
            this.teleport();
        }
        this.shipMovement(angleToClosestShip);
        if (angleToClosestShip < FIRE_ANGLE) {
            this.fire(game);
        }
        super.doAction(game);
        super.doAction(game);
        super.doAction(game);
        super.doAction(game);
        super.doAction(game);
    }

}
