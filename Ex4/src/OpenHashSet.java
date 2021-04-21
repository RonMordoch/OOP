/**
 * A class representing an open HashSet, using array of wrapped LinkedList objects.
 * Inherently implements the SimpleSet .
 */
public class OpenHashSet extends SimpleHashSet {

    // Make clampedIndex a method, reuse code!

    private LinkedListWrapper[] table;

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor int, upper load factor for resizing
     * @param lowerLoadFactor int, lower load factor for resizing
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values
     * should be ignored.
     * The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data an array for strings
     */
    public OpenHashSet(String[] data) {
        super();
        for (String string : data) {
            add(string);
        }
    }

    /**
     * Creates this hashSet's table.
     * @param capacity capacity of table
     */
    @Override
    protected void createTable(int capacity) {
        table = new LinkedListWrapper[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedListWrapper();
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (contains(newValue)) { // already in set, do not add
            return false;
        } // else
        if (checkUpperLoadFactor()) { // check if resizing the table is necessary
            rehashManager(INCREASE_TABLE);
        }
        int clampedIndex = clamp(newValue.hashCode());
        table[clampedIndex].add(newValue); // found a valid placement for the value
        size++; // increase number of elements
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        int clampedIndex = clamp(searchVal.hashCode());
        return table[clampedIndex].contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        int clampedIndex = clamp(toDelete.hashCode());
        if (table[clampedIndex].delete(toDelete)) { // if the LinkedList at the cell deleted the item
            size--; // reduce the number of elements
            if (checkLowerLoadFactor()) {
                rehashManager(DECREASE_TABLE); // check if resizing the table is necessary
            }
            return true;
        } // else
        return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return size;
    }

    /**
     * @return int, capacity of this table
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Manages rehashing the table.
     * @param indicator an int representing whether to increase or decrease table capacity
     */
    private void rehashManager(int indicator){
        // save the current table
        LinkedListWrapper[] oldTable = table;
        if (indicator == 1){ // increase table capacity
            increaseCapacity();
        }
        if (indicator == -1){
            decreaseCapacity(); // decrease table capacity
        }
        // create new set
        createTable(capacity);
        rehashTable(oldTable);
    }

    /**
     * Performs the actual rehashing of elements in table.
     * @param oldTable the table before changes were made to capacity
     */
    private void rehashTable(LinkedListWrapper[] oldTable) {
        for (LinkedListWrapper listWrapper : oldTable) {
            for (String string : listWrapper.getLinkedList()) {
                int clampedIndex = clamp(string.hashCode());
                table[clampedIndex].add(string);
            }
        }
    }

}