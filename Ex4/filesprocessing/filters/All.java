package filesprocessing.filters;

import java.io.File;

/**
 * all filter class
 */
public class All extends Filter {
    @Override
    public File[] filter(File[] filesArray) {
        return filesArray;
    }
}
