import oop.ex2.SpaceShipPhysics;

/**
 * This spaceship will always accelerate, and attempts to collide with the other ship. If the nearest ship is
 * close enough on its collision course, it will attempt to turn on its shield.
 */
public class BasherSpaceShip extends SpaceShip {

    private static final double UNITS_TO_SHIELD = 0.19;

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
     * Check if this ship turns on its in the current round.
     * This ship will turn on its shield when it is close to collision with the closest ship.
     * @param game the game object to which this ship belongs.
     * @return a boolean variable, true if ship is near enough to the closest ship, false otherwise.
     */
    @Override
    protected boolean checkShield(SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipLocation = closestShip.getPhysics();
        double distanceFromClosestShip = spaceShipPhysics.distanceFrom(closestShipLocation);
        return (distanceFromClosestShip <= UNITS_TO_SHIELD);
    }

}
