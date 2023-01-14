package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * implements the contains filter!
 */
public class Contains extends Filter {
    /*
    data members
     */

    /* The given name of a file by the user */
    private final String nameToContain;


    /**
     * constructor
     *
     * @param contained - the string of the filter to check if contained
     */
    public Contains(String contained) {
        nameToContain = contained;
    }


    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> filesArrayList = new ArrayList<>(Arrays.asList(filesArray));
        filesArrayList.removeIf(file -> (!file.getName().contains(nameToContain)));
        return (filesArrayList.toArray(new File[0]));
    }
}
