import java.util.Objects;

/**
 * this class will test and compute the run time according to the tests given to it by
 * SimpleSetPerformanceAnalyzer.java
 */
public class TestRunner {
    /*
     constants
     */
    // the number we need to divide in order to get miliseonds.
    private static final long NANO_TO_MILI_SECONDS_DIVISION_FACTOR = 1000000;
    // the number of iterations before any contains operation
    private static final long UNIFORM_WARM_UP = 70000;
    // the number of iterations for the linked list
    private static final long LINKED_LIST_WARM_UP = 7000;
    private static final String LINKED_LIST = new String("LINKED_LIST");


    /*
    data members
     */
    // the array that holds the files from data1
    private static String[] dataArrayOne;
    // the array that holds the files from data2
    private static String[] dataArrayTwo;
    /* Used to measure the time action takes. */
    private long timeBefore;
    /* Used to measure the time action takes. */
    private long difference;

    /**
     * constructor
     */
    public TestRunner() {
        dataArrayOne = Ex3Utils.file2array("src/data1.txt");
        dataArrayTwo = Ex3Utils.file2array("src/data2.txt");
        if (dataArrayOne == null) {
            dataArrayOne = Ex3Utils.file2array("data1.txt");
        }
        if (dataArrayTwo == null) {
            dataArrayTwo = Ex3Utils.file2array("data2.txt");
        }
    }


    /**
     * Adds all the elements in data1.txt to teh given Data structure (out of the 5 in this exercise case)
     *
     * @param setType the type of set
     * @return the time it took to perform the task.
     */
    public long addDataOne(SimpleSet setType) {
        timeBefore = System.nanoTime();
        addStringsToSet(dataArrayOne, setType);
        difference = System.nanoTime() - timeBefore;
        return nanoMiliConversion(difference);
    }

    /**
     * Adds all the elements in data2.txt to teh given Data structure (out of the 5 in this exercise case)
     *
     * @param setType the type.
     * @return the time it took to perform the task
     */
    public long addDataTwo(SimpleSet setType) {
        timeBefore = System.nanoTime();
        addStringsToSet(dataArrayTwo, setType);
        difference = System.nanoTime() - timeBefore;
        return nanoMiliConversion(difference);
    }

    /**
     * checks for certain element in a set - not linked list!
     *
     * @param elementToCheck the string we check for.
     * @param setToCheck     the data structure we check it for. performs warmup!
     * @return the amount of time in NanoSeconds that the process took.
     */
    public long containsElement(String elementToCheck, SimpleSet setToCheck, String setName) {
        if (Objects.equals(setName, LINKED_LIST)) {
            return warmUpAndCheck(elementToCheck, setToCheck, LINKED_LIST_WARM_UP);
        } else {
            return warmUpAndCheck(elementToCheck, setToCheck, UNIFORM_WARM_UP);
        }

    }

    /**
     * this method does the warmup and the contain with the time check and returns the time!
     *
     * @param elementToCheck - the string we are looking for
     * @param setToCheck     - the data structure we are checking
     * @param warmUpTime     - the warm up time needed
     * @return - the time it took in nanoseconds
     */
    private long warmUpAndCheck(String elementToCheck, SimpleSet setToCheck, long warmUpTime) {
        for (int i = 0; i < warmUpTime; i++) {
            setToCheck.contains(elementToCheck);
        }
        timeBefore = System.nanoTime();
        for (int i = 0; i < warmUpTime; i++) {
            setToCheck.contains(elementToCheck);
        }
        difference = System.nanoTime() - timeBefore;
        return (difference / warmUpTime);
    }


    /**
     * this function converts time from nanoseconds to miliseconds
     *
     * @param time - the time in nanoseconds
     * @return long - the time in miliseconds
     */
    private static long nanoMiliConversion(long time) {
        return (time / NANO_TO_MILI_SECONDS_DIVISION_FACTOR);
    }

    /**
     * adds the specified string to the data structure
     *
     * @param dataArray - the array given from ex3utils
     * @param setToAdd  - the set we need to add the string into
     */
    private void addStringsToSet(String[] dataArray, SimpleSet setToAdd) {
        for (String element : dataArray) {
            setToAdd.add(element);
        }
    }
}
