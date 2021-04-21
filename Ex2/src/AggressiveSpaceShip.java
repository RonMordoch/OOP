import oop.ex2.SpaceShipPhysics;

/**
 * This spaceship will always accelerate, and chase other ships. if the nearest ship is close enough, it will
 * attempt to fire at them.
 */
public class AggressiveSpaceShip extends SpaceShip {

    private static final double RADIANS_TO_FIRE = 0.21;

    /**
     * Check if this ship accelerates in the current round.
     * @param game the game object to which this ship belongs.
     * @return always true, by default.
     */
    protected boolean checkAcceleration(SpaceWars game) {
        return true;
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
    protected int checkTurn (SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        double angleTo = spaceShipPhysics.angleTo(closestShipLocation);
        if (angleTo > 0){
            return 1; // a positive angle, turn left
        }
        else if (angleTo < 0){
            return -1; // a negative angle, turn right
        }
        else {return 0;}
    }

    /**
     * Check if this ship fires in the current round.
     * @param game the game object to which this ship belongs.
     * @return  boolean variable, true if this ship is near enough to the closest ship, else false.
     */
    @Override
    protected boolean checkFire(SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        double angleTo = spaceShipPhysics.angleTo(closestShipLocation);
        return (angleTo < RADIANS_TO_FIRE);
    }

}
