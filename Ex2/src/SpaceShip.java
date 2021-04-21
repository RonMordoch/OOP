import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * An abstract spaceship class, a base class for the other spaceships.
 *  
 * @author ronmordoch
 */
public abstract class SpaceShip{

    private static final int INIT_MAX_ENERGY_LVL = 210;
    private static final int INIT_ENERGY_LVL = 190;
    private static final int INIT_HEALTH = 22;
    private static final int ENERGY_INCREMENT = 18;
    private static final int MAX_ENERGY_DECREMENT = 10;
    private static final int ENERGY_PER_ROUND = 1;
    private static final int FIRE_ENERGY_COST = 19;
    private static final int TELEPORT_ENERGY_COST = 140;
    private static final int SHIELD_ENERGY_COST = 3;
    private static final int FIRE_COOLDOWN = 7;

    public SpaceShipPhysics spaceShipPhysics;
    public int energyLVL;
    public int healthLVL;
    public int maxEnergyLVL;
    public int curCooldown;
    public boolean cooldown;
    public boolean shield;


    SpaceShip(){
        reset();
    }

    /**
     * Check if this ship teleports in the current round.
     * @param game the game object to which this ship belongs.
     * @return true if ship will teleport, otherwise and by default false.
     */
    protected boolean checkTeleport(SpaceWars game){
        return false;
    }

    /**
     * Check if this ship accelerate in the current round.
     * @param game the game object to which this ship belongs.
     * @return true if ship will accelerate, otherwise and by default false.
     */
    protected boolean checkAcceleration(SpaceWars game){
        return false;
    }

    /**
     * Check if this ship turns in the current round.
     * @param game the game object to which this ship belongs.
     * @return int -1 if ship turns right, 1 if ship turns left, 0 if no turns and by default.
     */
    protected int checkTurn(SpaceWars game){
        return 0;
    }

    /**
     * Check if this ship turns on its shield in the current round.
     * @param game the game object to which this ship belongs.
     * @return true if ship will turn on shield, otherwise and by default false.
     */
    protected boolean checkShield(SpaceWars game){
        return false;
    }

    /**
     * Check if this ship fires in the current round.
     * @param game the game object to which this ship belongs.
     * @return true if ship will fire, otherwise and by default false.
     */
    protected boolean checkFire(SpaceWars game){
        return false;
    }

   
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (checkTeleport(game)){
            teleport();
        }
        spaceShipPhysics.move(checkAcceleration(game), checkTurn(game));
        shield = false; // reset shield every round
        if (checkShield(game)){
            shieldOn();
        }
        if (checkFire(game)){
            fire(game);
        }
        cooldownManager();
        roundEnergyRegeneration();
    }
    /**
     * Regenerate a set amount of energy per round for this ship.
     */
    private void roundEnergyRegeneration(){
        if ( energyLVL < maxEnergyLVL){
            energyLVL += ENERGY_PER_ROUND;
        }
    }

    /**
     * This method is called every time a collision with this ship occurs .
     */
    public void collidedWithAnotherShip(){
        if (shield){
            energyLVL += ENERGY_INCREMENT;
            maxEnergyLVL += ENERGY_INCREMENT;
        }
        else {
            gotHit();
        }
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        energyLVL = INIT_ENERGY_LVL;
        healthLVL = INIT_HEALTH;
        maxEnergyLVL = INIT_MAX_ENERGY_LVL;
        spaceShipPhysics = new SpaceShipPhysics();
        curCooldown = 0;
        cooldown = false;
        shield = false;
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return (healthLVL ==0);
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return spaceShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shield){
            healthLVL -= 1;
            maxEnergyLVL -= MAX_ENERGY_DECREMENT;
            if (energyLVL > maxEnergyLVL){
                maxEnergyLVL = energyLVL;
            }
        }
    }

    /**
     * Gets the image of this ship, with or without the shield.
     * By default returns enemy spaceship's image.
     * @return the image of this ship.
     */
    public Image getImage(){
        if (shield){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if (!cooldown && energyLVL >= FIRE_ENERGY_COST){
           game.addShot(spaceShipPhysics);
           energyLVL -= FIRE_ENERGY_COST;
           cooldownOn();
       }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (energyLVL >= SHIELD_ENERGY_COST){
            shield = true;
            energyLVL -= SHIELD_ENERGY_COST;
        }
    }
    /**
     * Turns on the cooldown.
     */
    private void cooldownOn() {
        cooldown = true;
        curCooldown = FIRE_COOLDOWN;
    }

    /**
     * Manages the cooldown of this ship, called once every round.
     */
    private void cooldownManager(){
        if (cooldown){ // cooldown on
            curCooldown -= 1; // reduce by one per round
            if (curCooldown == 0){
                cooldown = false; // cooldown is over, turn off cooldown
            }
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (energyLVL >= TELEPORT_ENERGY_COST){
            spaceShipPhysics = new SpaceShipPhysics();
            energyLVL -= TELEPORT_ENERGY_COST;
        }
    }
    
}
