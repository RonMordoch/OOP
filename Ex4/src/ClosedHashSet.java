/**
 * A class representing a closed HashSet, using array of String objects.
 * Inherently implements the SimpleSet .
 */
public class ClosedHashSet extends SimpleHashSet {

    private String[] table;
    private String deletedString = new Object().toString();
    private static final int NOT_FOUND = -1;

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor int, upper load factor for resizing
     * @param lowerLoadFactor int, lower load factor for resizing
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values
     * should be ignored.
     * The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data an array for strings
     */
    public ClosedHashSet(String[] data){
        super();
        for(String string: data){
            add(string);
        }
    }

    /**
     * Creates this hashSet's table.
     * @param capacity capacity of table
     */
    @Override
    protected void createTable(int capacity){ table = new String[capacity];}

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (contains(newValue)) { // already in set, do not add
            return false;
        } else {
            if (checkUpperLoadFactor()){ // check if resizing the table is necessary
                rehashManager(INCREASE_TABLE);}
            for (int i = 0; i < capacity; i++) {
                int clampedIndex = clamp(newValue.hashCode() + ((i + i * i)/2));
                if (table[clampedIndex] == null || table[clampedIndex].equals(deletedString)) {
                    table[clampedIndex] = newValue; // found a valid placement for the value
                    size++; // increase number of elements
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A method that searches for the index of an item in the array.
     * This method is used in the delete method in order to avoid code duplication.
     * @param searchValue Value to search for
     * @return a positive index if found, else -1
     */
    public int getIndex(String searchValue){
        for (int i = 0; i < capacity; i++){
            int clampedIndex = clamp(searchValue.hashCode() + (i+i*i)/2);
            if (table[clampedIndex] == null) { // first check for nullity to avoid NullPointerException
                return NOT_FOUND;
            }
            if (table[clampedIndex].equals(searchValue)){
                return clampedIndex;
            }
        } // else
        return NOT_FOUND;}

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal){
        int index = getIndex(searchVal);
        // returns a valid index if found, -1 else (not found / null )
        return (index != NOT_FOUND);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete){
        if (contains(toDelete)){ // delete is possible only when element exists
            int index = getIndex(toDelete); // get the index of item
            table[index] = deletedString; // remove the item and mark cell as deleted
            size--; // reduce the number of elements
            if(checkLowerLoadFactor()){ // check if resizing the table is necessary
                rehashManager(DECREASE_TABLE);
            }
            return true;
        }
        return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size(){return size;}

    /**
     * @return int, capacity of this table
     */
    public int capacity(){return capacity;}

    /**
     * Manages rehashing the table.
     * @param indicator an int representing whether to increase or decrease table capacity
     */
    private void rehashManager(int indicator){
        // save the current table
        String[] oldTable = table;
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
    private void rehashTable(String[] oldTable){
        for(String string: oldTable){
            // check if string is valid
            if(string != null && !string.equals(deletedString)){
                // probe until a valid index is found
                for(int i=0; i<capacity; i++){
                    int clampedIndex = clamp(string.hashCode() + (i+i*i)/2);
                    if (table[clampedIndex] == null){
                        table[clampedIndex] = string;
                        break; // found a valid placement for string
                    }
                }
            }
        }

    }
}
