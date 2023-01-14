package filesprocessing.filters;

import filesprocessing.ProcessingExeption;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static conversion.KBtyesToBytes.convertorBytes;

/**
 * implements smaller than filter
 */
public class SmallerThan extends Filter {
    /*
    contants
     */
    private static final String SMALLER_MSG = "smaller than filter has invalid parameters";
    /*
    data members
     */
    // the representation in bytes of the given size
    private final double bytesRep;

    /**
     * constructor
     *
     * @param sizeKBytes - size in kilo bytes
     * @throws ProcessingExeption
     */
    public SmallerThan(double sizeKBytes) throws ProcessingExeption {
        if (sizeKBytes < 0) {
            throw new NegParameters(SMALLER_MSG);
        }
        bytesRep = convertorBytes(sizeKBytes);
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> arrayAsList = new ArrayList<>(Arrays.asList(filesArray));
        arrayAsList.removeIf(file -> file.length() >= bytesRep);
        return (arrayAsList.toArray(new File[0]));
    }
}
