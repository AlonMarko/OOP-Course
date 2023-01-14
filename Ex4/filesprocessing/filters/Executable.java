package filesprocessing.filters;

import java.io.File;

import filesprocessing.ProcessingExeption;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * implements the executable filter
 */
public class Executable extends Filter {
    /*
    constants
     */
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final String BAD_PARAMS_EXE = "invalid executable parameters";

    /*
   data members
     */

    // executable file yes or no - user input
    private final String permission;

    /**
     * constructor
     *
     * @param executeString - user input
     * @throws ProcessingExeption
     */
    public Executable(String executeString) throws ProcessingExeption {
        if (executeString.equals(YES) || executeString.equals(NO)) {
            permission = executeString;
        } else {
            permission = YES;
            throw new UnsuitableYesNo(BAD_PARAMS_EXE);
        }
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        if (permission.equals(YES)) {
            arrayAsList.removeIf(file -> (!file.canExecute()));
        } else {
            arrayAsList.removeIf(File::canExecute);
        }
        return (arrayAsList.toArray(new File[0]));
    }
}

