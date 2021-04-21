import oop.ex2.*;

public class SpaceShipFactory {

    private static final String HUMAN = "h";
    private static final String RUNNER = "r";
    private static final String BASHER = "b";
    private static final String AGGRESSIVE = "a";
    private static final String DRUNKARD = "d";
    private static final String SPECIAL = "s";

    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShipsArray = new SpaceShip[args.length];
        for (int i=0; i<args.length; i++) {
            switch (args[i]) {
                case HUMAN:
                    spaceShipsArray[i] = new HumanSpaceShip();
                    break;
                case RUNNER:
                    spaceShipsArray[i] = new RunnerSpaceShip();
                    break;
                case BASHER:
                    spaceShipsArray[i] = new BasherSpaceShip();
                    break;
                case AGGRESSIVE:
                    spaceShipsArray[i] = new AggressiveSpaceShip();
                    break;
                case DRUNKARD:
                    spaceShipsArray[i] = new DrunkardSpaceShip();
                    break;
                case SPECIAL:
                    spaceShipsArray[i] = new SpecialSpaceShip();
                    break;
            }
        }
    return spaceShipsArray;
    }
}

