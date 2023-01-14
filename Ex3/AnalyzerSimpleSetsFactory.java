import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeSet;

/**
 * the class is meant for the analyzer code and will create all related tests for the analyzer.
 */
public class AnalyzerSimpleSetsFactory {
    /*
    constants
     */
    // we are comparing 5 different structures. can be changes if we compare more?
    private static final int COMPARES_NUM = 5;
    // the names for the different models.
    private static final String TREE = new String("TREE");
    private static final String LINKED_LIST = new String("LINKED_LIST");
    private static final String HASH = new String("HASH");
    private static final String OPEN_HASH = new String("OPEN_HASH");
    private static final String CLOSED_HASH = new String("CLOSED_HASH");

    /**
     * creates the appropriate data structure or null if the input is invalid.
     *
     * @param type - the name of the data set to create
     * @return the newly created object or null for invalid inputs.
     */
    public static SimpleSet setCreator(String type) {
        if (Objects.equals(type, TREE)) {
            return new CollectionFacadeSet(new TreeSet<String>());
        } else if (Objects.equals(type, LINKED_LIST)) {
            return new CollectionFacadeSet(new LinkedList<String>());
        } else if (Objects.equals(type, HASH)) {
            return new CollectionFacadeSet(new HashSet<String>());
        } else if (Objects.equals(type, OPEN_HASH)) {
            return new OpenHashSet();
        } else if (Objects.equals(type, CLOSED_HASH)) {
            return new ClosedHashSet();
        } else {
            return null;
        }
    }

    /**
     * returns an array of names of the data structures in the given order.
     *
     * @return String array
     */
    public static String[] namesArray() {
        return new String[]{TREE, LINKED_LIST, HASH, OPEN_HASH, CLOSED_HASH};
    }

    /**
     * creates an array of data structures to be tested.
     *
     * @return array of the 5 (in this case) different data structures.
     */
    public static SimpleSet[] createObjectArray() {
        SimpleSet[] objectArray = new SimpleSet[COMPARES_NUM];
        String[] arrayOfNames = namesArray();
        for (int i = 0; i < COMPARES_NUM; i++) {
            objectArray[i] = setCreator(arrayOfNames[i]);
        }
        return objectArray;

    }


}
