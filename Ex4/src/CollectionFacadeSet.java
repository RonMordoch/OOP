import java.util.Collection;

/**
 * A facade class that wraps a collection implementing the SimpleSet interface in order to provide a
 * simplified interface to a set of classes implementing the aforementiond SimpleSet interface.
 */
public class CollectionFacadeSet implements SimpleSet {

    //The collection of wrapped by this class
    private Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection an object that extends collection
     */
    public CollectionFacadeSet(Collection<String> collection){this.collection = collection;}

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue){
        if(contains(newValue)){ return false; }
        else{return this.collection.add(newValue);}
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal){return this.collection.contains(searchVal);}

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete){return this.collection.remove(toDelete);}

    /**
     * @return The number of elements currently in the set
     */
    public int size(){return this.collection.size();}
}
