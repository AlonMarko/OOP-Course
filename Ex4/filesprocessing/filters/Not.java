package filesprocessing.filters;

import java.io.File;

import filesprocessing.ProcessingExeption;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * implements NOT suffix
 */
public class Not extends Filter {
    /*
    data members
     */
    // the filter we apply NOT on
    private final Filter filterToReverese;

    /**
     * constructor
     *
     * @param filter - the original filter we have to perform the opposite.
     */
    public Not(Filter filter) {
        filterToReverese = filter;
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        ArrayList<File> originallyFiltered = new ArrayList<>(Arrays.asList(filterToReverese.filter(filesArray)));
        arrayAsList.removeAll(originallyFiltered);
        return (arrayAsList.toArray(new File[0]));
    }
}
