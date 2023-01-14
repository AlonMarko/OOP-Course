package filesprocessing.filters;

import static conversion.KBtyesToBytes.*;

import java.io.File;

import filesprocessing.ProcessingExeption;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * class implements the between filter subsection
 */
public class Between extends Filter {

    /*
     constants
    */
    private static final String NEG_MSG = "Negative values were given to between.";
    private static final String FORBIDDEN_VALS = "Values given to filter are forbidden.";

    /*
     data members
    */
    // the first size converted
    private final double bytesStart;
    // the end size converted
    private final double bytesEnd;

    /**
     * checks to see wheter values are legal and converts them to bytes
     *
     * @param begin - the first between value
     * @param end   - the second between value
     * @throws ProcessingExeption
     */
    public Between(double begin, double end) throws ProcessingExeption {
        if (begin < 0 || end < 0) {
            throw new NegParameters(NEG_MSG);
        } else if (begin > end) {
            throw new UnsuitableOrderVals(FORBIDDEN_VALS);
        }
        bytesStart = convertorBytes(begin);
        bytesEnd = convertorBytes(end);
    }

    @Override
    public File[] filter(File[] filesArray) {
        ArrayList<File> filesArrayList = new ArrayList<>(Arrays.asList(filesArray));
        filesArrayList.removeIf(file -> (file.length() > bytesEnd) ||
                (file.length() < bytesStart));
        return (filesArrayList.toArray(new File[0]));
    }
}
