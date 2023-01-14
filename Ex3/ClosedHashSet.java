import java.lang.Math;
import java.util.Objects;

public class ClosedHashSet extends SimpleHashSet {
    /*
    constants
     */
    // indicator for no empty place in the set.
    private static final int INDEX_ERROR = -1;
    // indicator for deleted index - a long string that is unlikely to be a part of any data - so we can tell
    // if the position was previously occupied - for correct index search!
    private static final String DELETED_STRING = new String("OPENSPOTNOTNULLBUTDELETEDSTRING");

    /*
    data members
     */
    private String[] closedHashSet;

    /**
     * default constructor
     */
    public ClosedHashSet() {
        super();
        closedHashSet = new String[capacity];
    }

    /**
     * constructs a new empty table with the specified load factors with default initial capacity
     *
     * @param upperLoadFactor - upper bound
     * @param lowerLoadFactor - lower bound
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        closedHashSet = new String[capacity];
    }

    /**
     * data constructor - buildsa the hash set by adding elemnts one by one
     *
     * @param data - string array
     */
    public ClosedHashSet(String[] data) {
        super();
        closedHashSet = new String[capacity];
        for (String string : data) {
            if (string != null && !contains(string)) {
                add(string);
            }
        }


    }

    /**
     * returns the size of the storage space currently allocated for the set.
     *
     * @return - number of cells of the table.
     */
    @Override
    public int capacity() {
        return capacity;
    }

    /**
     * adds the specified element to the set if its not in it.
     *
     * @param newValue New value to add to the set.
     * @return false if the value is in the set or true if not and it was added.
     */
    @Override
    public boolean add(String newValue) {
        if (!contains(newValue)) {
            if (resizeUpNeeded()) {
                resize(ADD);
            }
            int i = cellSearch(newValue);
            closedHashSet[i] = newValue;
            setSize++;
            return true;
        }
        return false;
    }

    /**
     * looks for a specific given value in the set
     *
     * @param searchVal Value to search for.
     * @return true if in it or false otherwise
     */
    @Override
    public boolean contains(String searchVal) {
        int i = indexSearch(searchVal);
        return i != INDEX_ERROR;
    }

    /**
     * deletes the given value from the set
     *
     * @param toDelete Value to delete.
     * @return true if it was in the array and got deleted or false otherwise.
     */
    @Override
    public boolean delete(String toDelete) {
        if (contains(toDelete)) {
            if (resizeDownNeeded()) {
                resize(REMOVE);
            }
            int i = cellSearch(toDelete);
            closedHashSet[i] = DELETED_STRING;
            setSize--;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return setSize;
    }

    /**
     * this functions finds the first available index that we can insert the value to.
     * or returns INDEX_ERROR constant which is -1 if no such index is found.
     *
     * @param toAdd - the string we wish to add
     * @return - index int
     */
    private int cellSearch(String toAdd) {
        int toAddCode = toAdd.hashCode();
        for (int i = 0; i < capacity; i++) {
            int j = clamp((toAddCode + (i + i * i) / 2));
            if (Objects.equals(closedHashSet[j], DELETED_STRING) || closedHashSet[j] == null) {
                return j;
            }
        }
        return INDEX_ERROR;
    }

    /**
     * returns the index of the given string or the index error constant if it is not inside.
     *
     * @param toSearch - the string to find the index to
     * @return the index or null;
     */
    private int indexSearch(String toSearch) {
        int searchCode = toSearch.hashCode();
        for (int i = 0; i < capacity; i++) {
            int j = clamp((searchCode + (i + i * i)) / 2);
            if (Objects.equals(closedHashSet[j], toSearch)) {
                return j;
            }
        }
        return INDEX_ERROR;
    }

    /**
     * the main private driver function - it resized the array as needed - copies the values
     * to the new resized set
     *
     * @param resizeIndicator - indicator to tell whether we need to resize up or down.
     */
    private void resize(int resizeIndicator) {
        // changes the capacity.
        if (resizeIndicator == ADD) {
            capacity = capacity * RESIZE_VARIABLE;
        } else {
            capacity = capacity / RESIZE_VARIABLE;
        }
        // copies all values.
        String[] beforeResize = closedHashSet;
        closedHashSet = new String[capacity];
        setSize = 0;
        for (String string : beforeResize) {
            if (string != null && !string.equals(DELETED_STRING)) {
                int i = cellSearch(string);
                closedHashSet[i] = string;
                setSize++;
            }
        }

    }
}

