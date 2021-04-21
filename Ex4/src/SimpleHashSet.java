/**
 * An abstract class representing a simple HashSet.
 * Implements the SimpleSet.
 */
public abstract class SimpleHashSet implements SimpleSet {

    // Constants
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;
    protected static final int INITIAL_CAPACITY = 16;
    protected static final int INCREASE_TABLE = 1;
    protected static final int DECREASE_TABLE = -1;

    // Data Members
    protected float upperLoadFactor; //  this hashSet upper lower factor
    protected float lowerLoadFactor; // this hashSet upper lower factor
    protected int capacity; // this hashSet capacity
    protected int size = 0; // this hashSet size, i.e. total number of elements
    private int capacityMinusOne = capacity() - 1;

    /**
     * Constructor with the default load factors and capacity.
     */
    protected SimpleHashSet(){
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
        createTable(this.capacity);
    }

    /**
     * Constructor with given load factors and default capacity.
     * @param upperLoadFactor upper load capacity factor
     * @param lowerLoadFactor lower load capacity factor
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.capacity = INITIAL_CAPACITY;
        createTable(this.capacity);

    }

    protected void createTable(int capacity){};

    /**
     * Returns the capacity of this table.
     * Abstract by default.
     * @return capacity of this table
     */
    public abstract int capacity();

    /**
     * Adjusts and re-assigns the given index to a new index within the current capacity (i.e., table size).
     * @param index index within the current table's capacity
     * @return newly adjusted and re-assigned index
     */
    protected  int clamp(int index){
        return (index & (capacity() - 1));
    }

    /**
     * @return lower load factor of this table
     */
    public float getLowerLoadFactor(){return this.lowerLoadFactor;}

    /**
     * @return upper load factor of this table
     */
    public float getUpperLoadFactor(){return this.upperLoadFactor;}

    /**
     * Increases the capacity of table by a factor of 2.
     */
    protected void increaseCapacity(){ this.capacity *= 2;}

    /**
     * Decreases the capacity of table by a factor of 2.
     * Minimum capacity is 1.
     */
    protected void decreaseCapacity(){
        if (this.capacity > 1)
        { this.capacity /= 2;}
        // capacity is a power of two, therefore we can keep dividing until we reach 1
    }

        /**
         * Checks if adding an item will result in surpassing the current upper load factor.
         * @return true if new load factor is larger than the current load factor, false otherwise
         */
    protected boolean checkUpperLoadFactor(){
        float expectedLoadFactor = (float)(size() + 1) / capacity();
        return (expectedLoadFactor > this.upperLoadFactor);
    }
    /**
    * After deleting an item, checks if the new lower load factor is less the current load factor.
    * @return true if new load factor is larger than the current load factor, false otherwise
    */
    protected boolean checkLowerLoadFactor(){
            float newLoadFactor = (float)(size()) / capacity();
            return (newLoadFactor < this.lowerLoadFactor);
        }




}
