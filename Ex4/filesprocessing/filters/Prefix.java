package filesprocessing.filters;

import java.io.File;
import java.util.Arrays;


import java.util.ArrayList;

/**
 * implements prefix filter
 */
public class Prefix extends Filter {
    /*
    data members
     */
    // the given prefix
    private final String prefixGiven;

    /**
     * constructor
     *
     * @param prefix - given prefix to filter as
     */
    public Prefix(String prefix) {
        prefixGiven = prefix;
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        arrayAsList.removeIf(file -> (!file.getName().startsWith(prefixGiven)));
        return (arrayAsList.toArray(new File[0]));
    }
}
