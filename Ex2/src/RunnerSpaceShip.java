import oop.ex2.SpaceShipPhysics;

/**
 * This spaceship will always accelerate, and runs away from the fight. If the nearest ship is
 * close enough, it will attempt to teleport.
 */
public class RunnerSpaceShip extends SpaceShip {

    private static final double UNITS_TO_TELEPORT = 0.25;
    private static final double RADIANS_TO_TELEPORT = 0.23;

    /**
     * Check if this ship teleports in the current round.
     * @param game the game object to which this ship belongs.
     * @return a boolean variable, true if ship is too close to another ship, false otherwise.
     */
    @Override
    protected boolean checkTeleport (SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        return (spaceShipPhysics.distanceFrom(closestShipLocation) < UNITS_TO_TELEPORT &&
                spaceShipPhysics.angleTo(closestShipLocation) < RADIANS_TO_TELEPORT);
    }

    /**
     * Check if this ship accelerates in the current round.
     * @param game the game object to which this ship belongs.
     * @return always true, by default.
     */
    @Override
    protected boolean checkAcceleration(SpaceWars game){
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
}
