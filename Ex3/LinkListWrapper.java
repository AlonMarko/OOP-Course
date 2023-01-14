import java.util.LinkedList;
import java.util.Iterator;

/**
 * a wrapper class for link list - its is a linked list capable of add,remove and see specific item exists or
 * not - for open hash set.
 */
public class LinkListWrapper implements Iterable<String> {
    /*
    data members and constants of this class
     */
    // the linkedlist that will contain all the string that go in a certain place in the hashset.
    private final LinkedList<String> myLinkedList;

    /**
     * constructor.
     */
    public LinkListWrapper() {
        myLinkedList = new LinkedList<String>();
    }

    /**
     * adds the string to the linked list
     *
     * @param addedString - the string to be added
     * @return true or false.
     */
    public boolean add(String addedString) {
        return myLinkedList.add(addedString);
    }

    /**
     * removes the string from the hashset
     *
     * @param removedString - the string to delete
     * @return true or false.
     */
    public boolean delete(String removedString) {
        return myLinkedList.remove(removedString);
    }

    /**
     * checks wheter the string is already in the linked list
     *
     * @param stringToCheck - the string to check
     * @return - true or false.
     */
    public boolean contains(String stringToCheck) {
        return myLinkedList.contains(stringToCheck);
    }

    /**
     * The iterator for the linked list.
     *
     * @return - returns an iterator object.
     */
    @Override
    public Iterator<String> iterator() {
        return myLinkedList.iterator();
    }
}

