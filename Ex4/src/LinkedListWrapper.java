import java.util.LinkedList;

/**
 * A wrapper class, delegates methods to an object of LinkedList type.
 */
public class LinkedListWrapper {

    // LinkedList object to whom we delegate methods to.
    private LinkedList<String> linkedList = new LinkedList<String>();

    /**
     * Adds a new value to the linked list.
     * @param newValue a string
     * @return false if value is already contained inside the linked list, else adds value and return true
     */
    public boolean add(String newValue){
        return linkedList.add(newValue);
    }

    /**
     * Checks if a value in contained inside the linked list.
     * @param searchVal a string
     * @return true is value was found and is contained inside the linked list, else false
     */
    public boolean contains(String searchVal){
        return linkedList.contains(searchVal);
    }

    /**
     * Deletes a value from the linked list.
     * @param toDelete a string
     * @return true if string was found and deleted, otherwise returns false if string was not found.
     */
    public boolean delete(String toDelete){
        return linkedList.remove(toDelete);
    }

    /**
     * @return Returns the linked list of this wrapper class.
     */
    public LinkedList<String> getLinkedList(){return linkedList;}


}
