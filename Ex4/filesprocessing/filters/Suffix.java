package filesprocessing.filters;


import java.io.File;
import java.util.Arrays;


import java.util.ArrayList;

/**
 * implement suffix filter
 */
public class Suffix extends Filter {
    /*
    data members
     */
    // suffix of the file given as input
    private final String suffixGiven;

    /**
     * constructor
     *
     * @param suffix given suffix
     */
    public Suffix(String suffix) {
        suffixGiven = suffix;
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        arrayAsList.removeIf(file -> (!file.getName().endsWith(suffixGiven)));
        return (arrayAsList.toArray(new File[0]));
    }
}
