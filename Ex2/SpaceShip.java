import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 */
public class SpaceShip {
    /*
    data members and constants of this class
     */
    //Constants

    // the initial maximum energy a ship starts with.
    private static final int INITIAL_ENERGY_MAXIMUM = 210;
    // the initial current energy a ship starts with.
    private static final int INITIAL_ENERGY_CURRENT = 190;
    // the minimum energy level allowed - non-negative.
    private static final int MIN_ENERGY = 0;
    // the minimum health allowed - non-negative.
    private static final int MIN_HEALTH = 0;
    // the maximum health a ship has and starts with.
    private static final int MAX_HEALTH = 22;
    // the damage done to the ships' health when it gets shot and or collides with another ship and has no
    // shields.
    private static final int DMG = 1;
    // the damage to the current energy when ship is hit and has no shields.
    private static final int COLLISION_SHOT_NO_SHIELD_ENERGY_DMG = 10;
    //the energy being added to the current energy at each round
    private static final int ENERGY_TO_ADD = 1;
    // the energy it takes to fire a shot
    private static final int SHOT_ENERGY_USE = 19;
    // the energy it takes to use the teleport ability
    private static final int TELEPORT_ENERGY_USE = 140;
    // the energy it takes to use shields each round
    private static final int SHIELD_ENERGY_USE = 3;
    // the cool down between shots - 7 rounds.
    private static final int SHOT_COOL_DOWN = 7;
    // the energy being added to the current and maximum energy when a ship bashes another
    private static final int COLLISION_SHIELD_CURRENT_ENERGY_ADDON = 18;
    // number indicating a left turn
    protected static final int LEFT = 1;
    //number indicating a forward movement
    protected static final int FORWARD = 0;
    // number indicating right turn
    protected static final int RIGHT = -1;


    //data members of a ship - non constants

    // the ships updating max energy
    private int maxEnergy;
    // the ships updating health
    private int health;
    // the ships updating current energy
    private int energy;
    //a counter to count how many rounds have passed since the ship shot its last shot
    private int roundsSinceFire;
    // the object that hold the ships' location/movement stats
    private SpaceShipPhysics shipMovementStats;
    // a flag to tell if the shields are up or down
    protected boolean isShieldOn;

    /**
     * the cnstructor that creates a ship with default values - it does so using the reset method
     * which already creates a default ship after it died.
     */
    public SpaceShip() {
        this.reset();
    }


    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * checks anf performs the basics for every ship - its current energy and ads the end of round energy
     * value and checks the shot cooldown values - even if the ship does not fire it will still have a timer.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (SHOT_COOL_DOWN - this.roundsSinceFire > 0) {
            this.roundsSinceFire++;
        }
        if (this.energy + ENERGY_TO_ADD <= this.maxEnergy) {
            this.energy += ENERGY_TO_ADD;
        }
    }

    /**
     * This method is called every time a collision with this ship occurs.
     */
    public void collidedWithAnotherShip() {
        if (!this.isShieldOn) {
            this.gotHit();
        } else {
            this.maxEnergy += COLLISION_SHIELD_CURRENT_ENERGY_ADDON;
            this.energy += COLLISION_SHIELD_CURRENT_ENERGY_ADDON;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        this.maxEnergy = INITIAL_ENERGY_MAXIMUM;
        this.health = MAX_HEALTH;
        this.energy = INITIAL_ENERGY_CURRENT;
        this.roundsSinceFire = SHOT_COOL_DOWN;
        this.shipMovementStats = new SpaceShipPhysics();
        this.isShieldOn = false;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return True if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return this.health == MIN_HEALTH;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return The physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.shipMovementStats;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!this.isShieldOn) {
            if (this.maxEnergy >= COLLISION_SHOT_NO_SHIELD_ENERGY_DMG) {
                this.maxEnergy -= COLLISION_SHOT_NO_SHIELD_ENERGY_DMG;
            } else {
                this.maxEnergy = MIN_ENERGY;
            }
            if (this.energy > this.maxEnergy) {
                this.energy = this.maxEnergy;
            }
            this.health -= DMG;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round. -
     *
     * @return The image of this ship. by default it returns the image of an enemy ship since
     * most ship types are enemy and only 1 player ship.
     */
    public Image getImage() {
        if (this.isShieldOn) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game The game object.
     */
    public void fire(SpaceWars game) {
        if (this.energy >= SHOT_ENERGY_USE && (SHOT_COOL_DOWN - roundsSinceFire) <= 0) {
            this.roundsSinceFire = SHOT_COOL_DOWN;
            this.energy -= SHOT_ENERGY_USE;
            game.addShot(this.shipMovementStats);
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.energy >= SHIELD_ENERGY_USE) {
            this.isShieldOn = true;
            this.energy -= SHIELD_ENERGY_USE;
        } else {
            this.isShieldOn = false;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.energy >= TELEPORT_ENERGY_USE) {
            this.energy -= TELEPORT_ENERGY_USE;
            this.shipMovementStats = new SpaceShipPhysics();
        }
    }
}
