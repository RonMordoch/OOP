import oop.ex3.spaceship.*;

public class LongTermStorage extends AbstractStorage {

    // The constant storage capacity of all long term storage units
    private static final int longTermStorageCapacity = 1000;

    /**
     * This constructor initializes a Long-Term Storage object.
     */
    public LongTermStorage(){ super(longTermStorageCapacity); }

    /**
     * This method adds n Items of the given type to the long term storage unit.
     * If the action is successful, this method returns 0.
     * If n Items cannot be added to the locker at this time, no Items will be added, the method returns -1.
     * @param n an integer in {-1, 0}
     * @return n according to the action of addition that took place.
     * @return
     */
    public int addItem(Item item, int n){
        if (n < 0){
            System.out.println(ERROR_MSG);
            return -1; }
        else{
            int requiredStorageUnits = n * item.getVolume();
            String itemType = item.getType();
            if (getAvailableCapacity() < requiredStorageUnits){
                System.out.println(ERROR_MSG + PROBLEM_MSG + n +  ITEMS_MSG + itemType);
                return -1; }
            else {
                inventory.put(itemType, inventory.get(itemType) +n);
                availableCapacity -= n * item.getVolume();
                return 0; }
        }
    }

    /**
     * This method resets the long-term storage's inventory
     */
    public void resetInventory(){
        availableCapacity = capacity;
        setInventory();}

}
