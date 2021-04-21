import oop.ex3.spaceship.*;
import org.junit.*;
import org.junit.Assert;

/**
 * A test class for Locker.java.
 */
public class LockerTest {

    // Setup Items
    private static Item bat = ItemFactory.createSingleItem("baseball bat"); // size 2
    private static Item helmet = ItemFactory.createSingleItem("helmet, size 3"); // size 5
    private static Item football = ItemFactory.createSingleItem("football"); // size 4

    /**
     * A method to test getCapacity.
     */
    @Test
    public void checkGetCapacity(){
        Locker zeroLocker = new Locker(0);
        Locker firstLocker = new Locker(3);
        Locker secondLocker = new Locker(20);
        Assert.assertEquals(0, zeroLocker.getCapacity());
        Assert.assertEquals(3, firstLocker.getCapacity());
        Assert.assertEquals(20, secondLocker.getCapacity());
    }

    // check adding to an empty locker
    /**
     * A method to test adding items to a zero locker.
     */
    @Test
    public void checkAddItem_1(){
        Locker zeroLocker = new Locker(0);
        Assert.assertEquals(0, zeroLocker.addItem(bat, 0)); // add zero items to zero locker
        // add n>0 items to zero locker
        Assert.assertEquals(-1, zeroLocker.addItem(helmet, 1));
        Assert.assertEquals(-1, zeroLocker.addItem(football, 2));
    }

    // no room for adding items
    /**
     * A method to test adding when there is no room for adding items.
     */
    @Test
    public void checkAddItem_2(){
        Locker firstLocker = new Locker(3);
        Assert.assertEquals(-1, firstLocker.addItem(bat, 2));
        Assert.assertEquals(-1, firstLocker.addItem(helmet, 1));
    }

    // check adding items successfully without moving to LTS
    /**
     * A method to test adding items successfully without moving to LTS.
     */
    @Test
    public void checkAddItem_3(){
        Locker secondLocker = new Locker(20);
        Assert.assertEquals(0, secondLocker.addItem(bat, 1));
        Assert.assertEquals(0, secondLocker.addItem(bat, 4));
        // check adding zero items
        Assert.assertEquals(0, secondLocker.addItem(bat, 0));
    }

    // check adding to LTS
    /**
     * A method to test adding items and moving to LTS.
     */
    @Test
    public void checkAddItem_4(){
        Locker secondLocker = new Locker(20);
        Assert.assertEquals(0,secondLocker.addItem(bat, 3));
        Assert.assertEquals(0, secondLocker.addItem(bat, 2));
        Assert.assertEquals(1,secondLocker.addItem(bat, 5));
    }

    // check adding, but no room in LTS
    /**
     * A method to test addition when there is no room in LTS.
     */
    @Test
    public  void checkAddItem_5(){
        Locker secondLocker = new Locker(20);
        Locker.longTermStorage.resetInventory();
        Locker.longTermStorage.addItem(bat, 500);
        Assert.assertEquals(-1, secondLocker.addItem(bat, 12));
    }

    // check adding negative number of items
    @Test
    /**
     * A method to test attempting to add a negative number of items.
     */
    public void checkAddItem_6(){
        Locker locker = new Locker (5);
        Assert.assertEquals(-1, locker.addItem(bat, -5));
    }

    //check removeItem
    /**
     * A method to test removing items.
     */
    @Test
    public void checkRemoveItem(){
        Locker locker = new Locker(20);
        locker.addItem(bat, 5);
        Assert.assertEquals(0, locker.removeItem(bat, 1)); // valid removal
        Assert.assertEquals(-1, locker.removeItem(bat,5)); // not enough items
        Assert.assertEquals(-1, locker.removeItem(bat, -1)); // negative
        Assert.assertEquals(0, locker.removeItem(bat, 0)); // remove 0 items
        Assert.assertEquals(-1, locker.removeItem(football, 2)); // no such items in locker
    }

    // check getItemCount
    /**
     * A method to test getting item count in a locker.
     */
    @Test
    public void checkItemCount(){
        Locker locker = new Locker(20);
        locker.addItem(football, 2);
        Assert.assertEquals(0, locker.getItemCount(bat.getType())); // no such item in locker
        Assert.assertEquals(2, locker.getItemCount(football.getType()));
    }

    // check getInventory
    /**
     * A method to test getting the inventory of different items in locker.
     */
    @Test
    public void checkGetInventory(){
        Locker locker = new Locker(20);

        locker.addItem(bat, 3);
        int actual1 = locker.getInventory().get(bat.getType());
        Assert.assertEquals(3, actual1);
        Assert.assertEquals(0, (int)locker.getInventory().get(football.getType()) );

        locker.addItem(bat, 2);
        int actual2 = locker.getInventory().get(bat.getType());
        Assert.assertEquals(5, actual2);
        Assert.assertEquals(0, (int)locker.getInventory().get(helmet.getType()) );


        locker.addItem(bat, 5);
        int actual3 = locker.getInventory().get(bat.getType());
        Assert.assertEquals(2, actual3);
        Assert.assertEquals(0, (int)locker.getInventory().get(football.getType()) );
    }

    /**
     * A method to test getting the available capacity of a locker.
     */
    @Test
    public void getAvailableCapacity(){
        Locker locker = new Locker(20);
        locker.addItem(bat, 3);
        Assert.assertEquals(14, locker.getAvailableCapacity());

        locker.addItem(bat, 2);
        Assert.assertEquals(10, locker.getAvailableCapacity());

        locker.addItem(bat, 5);
        Assert.assertEquals(16, locker.getAvailableCapacity());

        locker.removeItem(bat, 2);
        Assert.assertEquals(20, locker.getAvailableCapacity());

        // adding zero items wont change available capacity
        locker.addItem(football, 0);
        Assert.assertEquals(20, locker.getAvailableCapacity());

        // removing zero items wont change available capacity
        locker.removeItem(bat, 0);
        Assert.assertEquals(20, locker.getAvailableCapacity());
    }

    /**
     * A method to test adding contradicting items.
     */
    @Test
    public void checkPlotTwist(){
        Locker locker = new Locker(20);
        locker.addItem(bat, 2);
        Assert.assertEquals(-2, locker.addItem(football, 1));

        locker.removeItem(bat, 2);
        locker.addItem(football, 1);
        Assert.assertEquals(-2, locker.addItem(bat, 1));
    }

}
