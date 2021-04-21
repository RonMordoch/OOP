import oop.ex3.spaceship.*;

/**
 * This class represents a Locker, a type of storage unit found aboard the USS Discovery starship.
 */
public class Locker extends AbstractStorage {

     // The LongTermStorage object, a special type of storage unit shared by all lockers collectively
    static LongTermStorage longTermStorage = new LongTermStorage();

    /**
     *T his constructor initializes a Locker object with the given capacity.
     * @param capacity capacity of this locker
     */
    public Locker(int capacity){ super(capacity); }

    /**
     * This method adds n Items of the given type to the locker.
     * If the addition is successful and doesn't cause Items to be moved to long-term storage, this
     * method returns 0.
     * If n Items cannot be added to the locker at this time, no Items will be added and the method
     * returns -1.
     * If this action causes n* Items to be moved to long-term storage and it can accommodate all n* Items,
     * the method returns 1.
     * If this action requires Items to be moved to long-term storage, but it doesn't have room to
     * accommodate all n* Items, then no Items will be added and the method returns -1.
     * In every case, the method will print a detailed informative message.
     * @param item an object of Item type
     * @param n integer in {-1, 0, 1}
     * @return n according to the action of addition that took place.
     */
    public int addItem(Item item, int n ){
        int footballCount = this.inventory.get("football");
        int baseballBatCount = this.inventory.get("baseball bat");
        if (item.getType().equals("football") & (baseballBatCount > 0)) {
            System.out.println(ERROR_MSG + CANNOT_CONTAIN_MSG + ITEMS_MSG + item.getType() + CONTRADICT_MSG);
            return -2; }
        if (item.getType().equals("baseball bat") & (footballCount > 0)) {
            System.out.println(ERROR_MSG + CANNOT_CONTAIN_MSG + ITEMS_MSG + item.getType() + CONTRADICT_MSG);
            return -2; }

        if (n < 0){
            System.out.println(ERROR_MSG);
            return -1; }

        int requiredStorageUnits = n * item.getVolume();
        String itemType = item.getType();

        if (getAvailableCapacity() < requiredStorageUnits){
            System.out.println(ERROR_MSG + PROBLEM_MSG  + n + ITEMS_MSG  + itemType);
            return -1; }

        else { // else getAvailableCapacity() >= requiredStorageUnits, enough room in locker
            return checkLongTermStorageAddition(item, n);
        }
    }

    // refactoring
    /**
     * Manages the long term storage addition conditions.
     * @param item item of Item object
     * @param n number of items to add
     * @return 1 if we added but items we moved to LTS, -1 if no room in LTS, 0 if we added all to the locker
     */
    private int checkLongTermStorageAddition(Item item, int n){
        int requiredStorageUnits = n * item.getVolume();
        String itemType = item.getType();
        int totalItemVolume = item.getVolume() * (getItemCount(itemType) + n);
        if ( totalItemVolume > (capacity * 0.5) ){
            int itemsToMove = (int)((totalItemVolume -(capacity * 0.2))/ item.getVolume());
            if (itemsToMove*item.getVolume() <= longTermStorage.getAvailableCapacity()){
                inventory.put(itemType, inventory.get(itemType) + n-itemsToMove);
                availableCapacity -= item.getVolume() * (n-itemsToMove);
                longTermStorage.addItem(item, itemsToMove);
                System.out.println(LTS_MSG);
                return 1; }
            else {
                System.out.println(ERROR_MSG + PROBLEM_MSG + itemsToMove + ITEMS_MSG + itemType);
                return -1; } }
        else { // else we don't need to move to long term storage
            inventory.put(itemType, inventory.get(itemType) + n);
            availableCapacity -= requiredStorageUnits;
            return 0; }
    }


    /**
     * This method removes n Items of the type type fro the locker.
     * If the action is successful, this method returns 0.
     * In case there are less than n Items of this type in the locker, no Items will be removed,
     * the method returns -1.
     * In case n is negative, no Items will be removed, the method returns -1,
     * @param item an object of Item type
     * @param n an integer in {-1, 0}
     * @return n according to the action of removal that took place.
     */
    public int removeItem(Item item, int n){
        String itemType = item.getType();
        if (n < 0){
            System.out.println(ERROR_MSG + REMOVE_NEG_MSG + itemType);
            return -1; }
        else { // else n >= 0
            int amountInLocker = getItemCount(itemType);
            if (amountInLocker < n) {
                System.out.println(ERROR_MSG + CONTAIN_MSG + n + ITEMS_MSG + itemType);
                return -1; }
            else { // else, amountInLocker >= n
                inventory.put(itemType, inventory.get(itemType) - n);
                availableCapacity += n * item.getVolume();
                return 0; }
        }
    }

}
