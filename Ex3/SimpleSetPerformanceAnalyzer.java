/**
 * analyzes the run time for all the different structures
 */
public class SimpleSetPerformanceAnalyzer {
    /*
    constants
     */
    // indicator to perform test add test 1
    private static final int TEST_1 = 1;
    //indicator to perform add test 2
    private static final int TEST_2 = 2;
    // for the print - personal use
    private static final String DATA_1_TXT = " Add_Data1.txt = ";
    private static final String DATA_2_TXT = " Add_Data2.txt = ";
    private static final String CONTAINS = " Contains ";
    // the words needed to be searched in the tests
    private static final String HI = "hi";
    private static final String NUMBER_STRING_NEG = "-13170890158";
    private static final String NUMBER_STRING_POS = "23";


    /*
    data members
     */
    /* A tester object for our Analyzer to use. */
    private static TestRunner testRunner;
    /* A string of the names of the DS array from the factory, in the same order. */
    private static String[] names;
    /* A DS array. if needed, will point to a DS array full with the data from the 1st file. */
    private static SimpleSet[] FullSetsData1;
    /* A DS array. if needed, will point to a DS array full with the data from the 2nd file. */
    private static SimpleSet[] FullSetsData2;


    /**
     * creates the testRunner object and the array with the names of the different sets.
     * it uses test runner to read the files and creates array of strings from it etc.
     */
    private static void createBasics() {
        testRunner = new TestRunner();
        names = AnalyzerSimpleSetsFactory.namesArray();

    }


    /**
     * this function copies all object from the arrays from files 1 and 2 to different data structures
     * and runs the tests on them and prints the time!
     */
    private static void addToSets(int testNUm, SimpleSet[] sets) {
        if (testNUm == TEST_1) {
            for (int i = 0; i < sets.length; i++) {
                System.out.println(names[i] + DATA_1_TXT + testRunner.addDataOne(sets[i]));
            }
        } else {
            for (int i = 0; i < sets.length; i++) {
                System.out.println(names[i] + DATA_2_TXT + testRunner.addDataTwo(sets[i]));
            }
        }
        System.out.println();
    }

    /**
     * runs the contain tests for all the data structures with the given word to search for!
     *
     * @param word - the word we search for
     * @param sets - the array of data structures
     */
    private static void containsTests(String word, SimpleSet[] sets) {
        for (int i = 0; i < sets.length; i++) {
            System.out.println(names[i] + CONTAINS + word + " = " +
                    testRunner.containsElement(word, sets[i], names[i]));
        }
        System.out.println();
    }


    /**
     * the main driver function that runs the tests!
     *
     * @param args - actually none becuase filenames are given in the testrunner class!
     */
    public static void main(String[] args) {
        createBasics();
        SimpleSet[] dataOneSetCollection = AnalyzerSimpleSetsFactory.createObjectArray();
        SimpleSet[] dataTwoSetCollection = AnalyzerSimpleSetsFactory.createObjectArray();
        // add tests
        addToSets(TEST_1, dataOneSetCollection);
        addToSets(TEST_2, dataTwoSetCollection);
        // contain tests
        containsTests(HI, dataOneSetCollection);
        containsTests(NUMBER_STRING_NEG, dataOneSetCollection);
        containsTests(NUMBER_STRING_POS, dataTwoSetCollection);
        containsTests(HI, dataTwoSetCollection);
    }
}
