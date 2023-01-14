public abstract class SimpleHashSet implements SimpleSet {
    /*
    constants
     */
    // the default higher load bound
    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f;
    //the default lower load bound
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f;
    // the default initial capacity of a hash set
    protected static int INITIAL_CAPACITY = 16;
    // the enlargement factor - power of 2
    protected static final int RESIZE_VARIABLE = 2;
    // indicator for addition
    protected static final int ADD = 0;
    // indicator for removal
    protected static final int REMOVE = 1;
    /*
    data members
     */
    // the class actual lower bound load factor
    protected float lowerFactor;
    // the class actual upper bound load factor
    protected float upperFactor;
    // the capacity of this class
    protected int capacity;
    // the current size of the hash set - num of items in it.
    protected int setSize;

    /**
     * default constructor - constructs a hash set with default capacity values
     */
    protected SimpleHashSet() {
        lowerFactor = DEFAULT_LOWER_CAPACITY;
        upperFactor = DEFAULT_HIGHER_CAPACITY;
        capacity = INITIAL_CAPACITY;
        setSize = 0;
    }

    /**
     * specialized constructor - new hash set with capacity - INITIAL_CAPACITY
     *
     * @param upperLoadFactor - given upper load factor
     * @param lowerLoadFactor - given lower load factor
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        lowerFactor = lowerLoadFactor;
        upperFactor = upperLoadFactor;
        capacity = INITIAL_CAPACITY;
        setSize = 0;
    }

    /**
     * returns the size of the storage space currently allocated for the set
     *
     * @return - int the size
     */
    abstract int capacity();

    /**
     * clamps hashing indices to fit within the current table capacity
     *
     * @param index - int index to clamp
     * @return - an index properly clamped.
     */
    protected int clamp(int index) {
        return index & (capacity - 1);
    }

    /**
     * getter for lower load factor.
     *
     * @return load factor - float
     */
    protected float getLowerLoadFactor() {
        return lowerFactor;
    }

    /**
     * getter for upper load factor
     *
     * @return - load factor - float
     */
    protected float getUpperLoadFactor() {
        return upperFactor;
    }

    /**
     * function to check if a resize upwards is needed before adding an item to the array
     *
     * @return true of false
     */
    protected boolean resizeUpNeeded() {
        return ((float) (setSize + 1) / capacity) > upperFactor;
    }

    /**
     * function to check if a resize downwards is needed before adding an item to the array
     *
     * @return true of false
     */
    protected boolean resizeDownNeeded() {
        return ((float) (setSize - 1) / capacity) < lowerFactor;
    }
}
