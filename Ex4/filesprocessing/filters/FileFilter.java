package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * implements the filter class. filters by name.
 */
public class FileFilter extends Filter {

    /*
     data members
     */
    //name of the file (excluding path)
    private final String fileName;

    /**
     * constructor
     *
     * @param fileName - file name
     */
    public FileFilter(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        arrayAsList.removeIf(file -> (!file.getName().equals(this.fileName)));
        return (arrayAsList.toArray(new File[0]));
    }
}
