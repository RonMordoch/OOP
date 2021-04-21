import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class representing a storage unit.
 */
public abstract class AbstractStorage {

    // Sysout message constants
    static String ERROR_MSG = "Error: Your request cannot be completed at this time. ";
    static String ITEMS_MSG = " items of type ";
    static String LTS_MSG = "Warning: Action successful, but has caused items to be moved to storage";
    static String PROBLEM_MSG = "Problem: no room for ";
    static String REMOVE_NEG_MSG = "Problem: cannot remove a negative number of items of type ";
    static String CONTAIN_MSG = "Problem: the locker does not contain ";
    static String CANNOT_CONTAIN_MSG = "Problem: the locker cannot contain ";
    static String CONTRADICT_MSG = ", as it contains a contradicting item";

    // Abstract class storage unit
    protected int capacity;
    protected int availableCapacity;
    protected Map<String, Integer> inventory = new HashMap<>();

    /**
     * This constructor initializes a storage unit object with the given capacity.
     * */
    public AbstractStorage(int capacity){
        this.capacity = capacity;
        this.availableCapacity = capacity;
        setInventory();
    }

    /**
     * Initializes the inventory of this storage locker.
     */
    protected void setInventory(){
        for(Item item: ItemFactory.createAllLegalItems()){
            this.inventory.put(item.getType(), 0);
        }
    }

    /**
     * Returns the current amount of given type of item in unit.
     * @param type type of an Item object
     * @return an integer, number of items of given type
     */
    public int getItemCount(String type){return this.inventory.get(type);}

    /**
     * Returns this storage unit inventory.
     * @return HashMap representing this storage unit inventory.
     */
    public Map<String, Integer> getInventory() {return this.inventory; }

    /**
     * Returns the constant capacity of this storage unit.
     * @return capacity of this storage unit
     */
    public int getCapacity(){return this.capacity ;}

    /**
     * Returns the available capacity of this storage unit
     * @return current available capacity of this storage unit
     */
    public int getAvailableCapacity(){return this.availableCapacity;}


}
