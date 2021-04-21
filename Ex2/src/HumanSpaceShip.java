import java.awt.*;
import oop.ex2.*;

/**
 * A human-player controlled spaceship.
 */
public class HumanSpaceShip extends SpaceShip {


    /**
     * Check if this ship teleports in the current round.
     * @param game the game object to which this ship belongs.
     * @return a boolean variable, true if player pressed teleport key, false otherwise.
     */
    @Override
    protected boolean checkTeleport(SpaceWars game){
        return game.getGUI().isTeleportPressed();
    }

    /**
     * Check if this ship accelerate in the current round.
     * @param game the game object to which this ship belongs.
     * @return a boolean variable, true if player pressed teleport key, false otherwise.
     */
    @Override
    protected boolean checkAcceleration(SpaceWars game){
        return game.getGUI().isUpPressed();
    }

    /**l
     * Check if this ship turns in the current round.
     * @param game the game object to which this ship belongs.
     * @return int according to players actions:
     * -1 if ship turns right (player pressed right key)
     * 1 if ship turns left (player pressed left key)
     * 0 if no turns and by default. (player did not press any keys or pressed
     *                                both at the same time).
     */
    @Override
    protected int checkTurn(SpaceWars game){
        if (game.getGUI().isRightPressed()){
            if (game.getGUI().isLeftPressed()){
                return 0; // player pressed both right and left keys simultaneously.
            }
            else {
                return -1; // turn right
            }
        }
        else if (game.getGUI().isLeftPressed()){
            return 1; // turn left
        }
        else {return 0;} // no turn
    }

    /**
     * Check if this ship turns on its in the current round.
     * @param game the game object to which this ship belongs.
     * @return a boolean variable, true if player pressed shield on key, false otherwise.
     */
    @Override
    protected boolean checkShield(SpaceWars game){
        return game.getGUI().isShieldsPressed();
    }
    /**
     * Check if this ship fires in the current round.
     * @param game the game object to which this ship belongs.
     * @return  boolean variable, true if player pressed fire key, false otherwise.
     */
    @Override
    protected boolean checkFire(SpaceWars game){
        return game.getGUI().isShotPressed();
    }
    /**
     * Gets the image of the human ship, with or without the shield.
     * @return the image of the human ship.
     */
    @Override
    public Image getImage(){
        if (shield){
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
        else {
            return GameGUI.SPACESHIP_IMAGE;
        }
    }
}
