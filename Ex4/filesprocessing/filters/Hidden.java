package filesprocessing.filters;


import java.io.File;

import filesprocessing.ProcessingExeption;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * the hidden file filter extends filter and implements it.
 */
public class Hidden extends Filter {
    /*
    constants
     */
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final String BAD_PARAMS_HIDDEN = "invalid hidden filter parameters";
    /*
    data members
     */
    // wheter the file is hidden or not.
    private final String permission;

    public Hidden(String hiddenString) throws ProcessingExeption {
        if (hiddenString.equals(YES) || hiddenString.equals(NO)) {
            permission = hiddenString;
        } else {
            throw new UnsuitableYesNo(BAD_PARAMS_HIDDEN);
        }
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        if (permission.equals(YES)) {
            arrayAsList.removeIf(file -> (!file.isHidden()));
        } else {
            arrayAsList.removeIf(File::isHidden);
        }
        return (arrayAsList.toArray(new File[0]));
    }
}

