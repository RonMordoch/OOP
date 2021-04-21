import oop.ex2.SpaceShipPhysics;

/** This ship's driver is quite intoxicated, making him afraid of what ever he sees in sight.
 * Thus, he drowns into panic, running away from the closest ship's he spots, and consistently fire in his
 * own stupor.
 */
public class DrunkardSpaceShip extends SpaceShip {

    /**
     * Check if this ship accelerates in the current round.
     * @param game the game object to which this ship belongs.
     * @return always true, by default.
     */
    @Override
    protected boolean checkAcceleration(SpaceWars game) {
        return true;
    }


    /**
     * Check if this ship turns in the current round.
     * This ship will always turn away from the closet ship.
     * @param game the game object to which this ship belongs.
     * @return int according to this ship angle in comparison to the closest ship:
     * -1 if ship turns right (angle > 0)
     * 1 if ship turns left (angle < 0)
     * 0 if no turns and by default. (closest ship is far away).
     */
    @Override
    protected int checkTurn (SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        double angleTo = spaceShipPhysics.angleTo(closestShipLocation);
        if (angleTo > 0){
            return -1; // a positive angle, turn right
        }
        else if (angleTo < 0){
            return 1; // a negative angle, turn left
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
        return true;
    }

}
