package filesprocessing.filters;

import java.io.File;

/**
 * abstract class for filter
 */
public abstract class Filter {
    /**
     * given an array of files it filters them according to the right filter
     *
     * @param filesArray - array of files
     * @return - filtered array of files
     */
    public abstract File[] filter(File[] filesArray);
}
