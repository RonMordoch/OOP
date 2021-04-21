import oop.ex2.SpaceShipPhysics;
import java.util.Random;

/**
 * This ship will chase the closest spaceship. It will always have it shield on, except when it is close
 * enough to another ship. If it gets closer , it will also start firing.
 * It will randomly accelerate in order to make firing at it more challenging.
 * */
public class SpecialSpaceShip extends SpaceShip {

    private static final double UNITS_TO_SHIELD = 0.25;
    private static final double UNITS_TO_FIRE = 0.15;


    /**
     * Check if this ship accelerates in the current round.
     * @param game the game object to which this ship belongs.
     * @return always true, by default.
     */
    @Override
    public boolean checkAcceleration(SpaceWars game){
        Random rand = new Random();
        return (rand.nextBoolean());
    }

    /**
     * Check if this ship turns in the current round.
     * This ship will always turn towards the closet ship.
     * @param game the game object to which this ship belongs.
     * @return int according to this ship angle in comparison to the closest ship:
     * 1 if ship turns left (angle > 0)
     * -1 if ship turns right (angle < 0)
     * 0 if no turns and by default. (closest ship is far away).
     */
    @Override
    public int checkTurn (SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        double angleTo = spaceShipPhysics.angleTo(closestShipLocation);
        if (angleTo > 0) {
            return 1; // a positive angle, turn left
        } else if (angleTo < 0) {
            return -1; // a negative angle, turn right
        } else { return 0; }
    }

    /**
     * Check if this ship turns off its in the current round.
     * This ship will turn off its shield when it is close to enough with the closest ship.
     * @param game the game object to which this ship belongs.
     * @return a boolean variable, true if ship is far enough fro, the closest ship, false otherwise, and then
     * it will turn on shield.
     */
    @Override
    public boolean checkShield(SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        double distanceFromClosestShip = spaceShipPhysics.distanceFrom(closestShipLocation);
        return (distanceFromClosestShip > UNITS_TO_SHIELD);
    }


    /**
     * Check if this ship fires in the current round.
     * @param game the game object to which this ship belongs.
     * @return  boolean variable, true if this ship is near enough to the closest ship, else false.
     */
    @Override
    public boolean checkFire(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        double distanceFromClosestShip = spaceShipPhysics.distanceFrom(closestShipLocation);
        return (distanceFromClosestShip < UNITS_TO_FIRE);
    }

    /**
     * Turn on shield.
     */
    @Override
    public void shieldOn() { shield = true; }



}
