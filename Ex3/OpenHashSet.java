public class OpenHashSet extends SimpleHashSet {
    /*
    data members
     */
    // the linked list containing all the other linked lists.
    private LinkListWrapper[] openHashSet;

    /**
     * a default constructor - constructsa new, empty table with default initial capacity
     * upper load and lower load factors
     */
    public OpenHashSet() {
        super();
        openHashSet = new LinkListWrapper[this.capacity];
        for (int i = 0; i < capacity; i++) {
            openHashSet[i] = new LinkListWrapper();
        }
    }

    /**
     * constructs a new, empty table with the specified load factors and the default initial capacity
     *
     * @param upperLoadFactor - a given max load factor
     * @param lowerLoadFactor - a given min load factor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        openHashSet = new LinkListWrapper[capacity];
        for (int i = 0; i < capacity; i++) {
            openHashSet[i] = new LinkListWrapper();
        }
    }

    /**
     * data constructor - builds the hash set by adding the elements one by one - duplicates should be
     * ignored.
     * has default capacity, and default load factors.
     *
     * @param data - the data to insert to the set
     */
    public OpenHashSet(String[] data) {
        super();
        openHashSet = new LinkListWrapper[capacity];
        for (int i = 0; i < capacity; i++) {
            openHashSet[i] = new LinkListWrapper();
        }
        for (String string : data) {
            if (string != null && !contains(string)) {
                add(string);
            }
        }
    }

    /**
     * returns the size of the storage space allocated to the set.
     *
     * @return int - storage space.
     */
    @Override
    public int capacity() {
        return capacity;
    }

    /**
     * adds the secified item to the set if its not already inside.
     *
     * @param newValue New value to add to the set.
     * @return true of false
     */
    @Override
    public boolean add(String newValue) {
        if (!contains(newValue)) {
            if (resizeUpNeeded()) {
                resize(ADD);
            }
            int i = clamp(newValue.hashCode());
            if (openHashSet[i].add(newValue)) {
                setSize++;
                return true;
            }
        }
        return false;

    }

    /**
     * searches for the specified val in the hash set
     *
     * @param searchVal Value to search for.
     * @return true if found or false otherwise.
     */
    @Override
    public boolean contains(String searchVal) {
        int i = clamp(searchVal.hashCode());
        return openHashSet[i].contains(searchVal);
    }

    /**
     * removes the value if its inside the set
     *
     * @param toDelete Value to delete.
     * @return - ture if it was found and deleted - false otherwise.
     */
    @Override
    public boolean delete(String toDelete) {
        if (contains(toDelete)) {
            if (resizeDownNeeded()) {
                resize(REMOVE);
            }
            int i = clamp(toDelete.hashCode());
            if (openHashSet[i].delete(toDelete)) {
                setSize--;
                return true;
            }
        }
        return false;
    }

    /**
     * returns the number of actual objects held in the set.
     *
     * @return int.
     */
    @Override
    public int size() {
        return setSize;
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
        LinkListWrapper[] beforeResize = openHashSet;
        openHashSet = new LinkListWrapper[capacity];
        for (int i = 0; i < capacity; i++) {
            openHashSet[i] = new LinkListWrapper();
        }
        setSize = 0;
        for (LinkListWrapper singleCellList : beforeResize) {
            for (String string : singleCellList) {
                if (string != null) {
                    int i = clamp(string.hashCode());
                    if (openHashSet[i].add(string)) {
                        setSize++;
                    }
                }
            }
        }
    }
}
