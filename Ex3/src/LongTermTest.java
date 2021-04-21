import oop.ex3.spaceship.*;
import org.junit.*;
import org.junit.Assert;

public class LongTermTest {

    // Set up capacity and items
    private static final int longTermStorageCapacity = 1000;
    private static Item bat = ItemFactory.createSingleItem("baseball bat"); // size 2
    private static Item helmet = ItemFactory.createSingleItem("helmet, size 3"); // size 5

    /**
     * A method to test getCapacity.
     */
    @Test
    public void checkGetCapacity() {
        LongTermStorage LTS = new LongTermStorage();
        Assert.assertEquals(longTermStorageCapacity, LTS.getCapacity());
    }

    /**
     * A method to adding items.
     */
    @Test
    public void checkAddItem() {
        LongTermStorage LTS = new LongTermStorage();
        Assert.assertEquals(0, LTS.addItem(bat, 0)); // add zero items
        Assert.assertEquals(0, LTS.addItem(bat, 250)); // successful addition
        Assert.assertEquals(-1, LTS.addItem(bat, 251)); // no room
        Assert.assertEquals(-1, LTS.addItem(helmet, -1)); // negative number of items
    }


    /**
     * A method to test resetting the inventory.
     */
    @Test
    public void checkResetInventory() {
        LongTermStorage LTS = new LongTermStorage();
        Assert.assertEquals(0, LTS.addItem(bat, 250)); // successful addition
        LTS.resetInventory();
        Assert.assertEquals(1000, LTS.getAvailableCapacity());
        Assert.assertEquals(0, LTS.getItemCount(bat.getType()));
    }

    /**
     * A method to test getting item count.
     */
    @Test
    public void checkItemCount() {
        LongTermStorage LTS = new LongTermStorage();
        LTS.addItem(helmet, 2);
        Assert.assertEquals(0, LTS.getItemCount(bat.getType())); // no such item in locker
        Assert.assertEquals(2, LTS.getItemCount(helmet.getType()));
    }

    /**
     * A method to test getting the inventory.
     */
    @Test
    public void checkGetInventory() {
        LongTermStorage LTS = new LongTermStorage();

        LTS.addItem(bat, 3);
        int actual1 = LTS.getInventory().get(bat.getType());
        Assert.assertEquals(3, actual1);
        Assert.assertEquals(0, (int) LTS.getInventory().get(helmet.getType()));

        LTS.addItem(bat, 2);
        int actual2 = LTS.getInventory().get(bat.getType());
        Assert.assertEquals(5, actual2);
        Assert.assertEquals(0, (int) LTS.getInventory().get(helmet.getType()));
    }

    /**
     * A method to test getting the available capacity.
     */
    @Test
    public void getAvailableCapacity(){
        LongTermStorage LTS = new LongTermStorage();
        LTS.addItem(bat, 200);
        Assert.assertEquals(600, LTS.getAvailableCapacity());

        LTS.addItem(bat, -200);
        Assert.assertEquals(600, LTS.getAvailableCapacity());

        // adding zero items wont change available capacity
        LTS.addItem(bat, 0);
        Assert.assertEquals(600, LTS.getAvailableCapacity());

    }
}
