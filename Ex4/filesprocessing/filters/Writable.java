package filesprocessing.filters;

import java.io.File;

import filesprocessing.ProcessingExeption;

import java.util.Arrays;


import java.util.ArrayList;

/**
 * implements writable filter class
 */
public class Writable extends Filter {
    /*
    constants
     */
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final String BAD_WRITABLE_PARAMS = "invalid writable filter parameters";
    /*
    data members
     */
    //teh status of writablty to check
    private final String fileName;

    /**
     * constructor
     *
     * @param status - the status to filter as
     * @throws ProcessingExeption
     */
    public Writable(String status) throws ProcessingExeption {
        if (status.equals(YES) || status.equals(NO)) {
            fileName = status;
        } else {
            throw new UnsuitableYesNo(BAD_WRITABLE_PARAMS);
        }
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        if (fileName.equals(YES)) {
            arrayAsList.removeIf(file -> (!file.canWrite()));
        } else {
            arrayAsList.removeIf(File::canWrite);
        }
        return (arrayAsList.toArray(new File[0]));
    }
}
