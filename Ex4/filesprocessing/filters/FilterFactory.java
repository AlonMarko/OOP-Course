package filesprocessing.filters;

import filesprocessing.ProcessingExeption;

/**
 * the factory class for all filters!
 */
public class FilterFactory {
    /*
    constants
     */
    //names of filters
    private static final String FILTER_NAME = "problem with given filter name.\n";
    private static final int NOT_SUFFIX_PATTERN = 3;
    private static final int NOT_BETWEEN_PATTERN = 4;
    private static final String SMALLER_THAN = "smaller_than";
    private static final String BETWEEN = "between";
    private static final String GREATER_THAN = "greater_than";
    private static final String CONTAINS = "contains";
    private static final String FILE = "file";
    private static final String ALL = "all";
    private static final String HIDDEN = "hidden";
    private static final String EXECUTABLE = "executable";
    private static final String WRITABLE = "writable";
    private static final String SUFFIX = "suffix";
    private static final String PREFIX = "prefix";

    /**
     * creates a filter according to the command file.
     *
     * @param filter - the filter from the command file - string
     * @return a filter
     * @throws ProcessingExeption
     */
    public static Filter filterCreator(String filter) throws ProcessingExeption {
        String[] splitInput = filter.split("#", -1);
        double beginVal;
        double endVal;
        switch (splitInput[0]) {
            case GREATER_THAN:
                beginVal = Double.parseDouble((splitInput[1]));
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new GreaterThan(beginVal));
                } else {
                    return new GreaterThan(beginVal);
                }
            case BETWEEN:
                beginVal = Double.parseDouble((splitInput[1]));
                endVal = Double.parseDouble((splitInput[2]));
                if (splitInput.length == NOT_BETWEEN_PATTERN) {
                    return new Not(new Between(beginVal, endVal));
                } else {
                    return new Between(beginVal, endVal);
                }
            case SMALLER_THAN:
                beginVal = Double.parseDouble((splitInput[1]));
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new SmallerThan(beginVal));
                } else {
                    return new SmallerThan(beginVal);
                }
            case FILE:
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new FileFilter(splitInput[1]));
                } else {
                    return new FileFilter(splitInput[1]);
                }
            case CONTAINS:
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new Contains(splitInput[1]));
                } else {
                    return new Contains(splitInput[1]);
                }
            case PREFIX:
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new Prefix(splitInput[1]));
                } else {
                    return new Prefix(splitInput[1]);
                }
            case SUFFIX:
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new Suffix(splitInput[1]));
                } else {
                    return new Suffix(splitInput[1]);
                }
            case WRITABLE:
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new Writable(splitInput[1]));
                } else {
                    return new Writable(splitInput[1]);
                }
            case EXECUTABLE:
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new Executable(splitInput[1]));
                } else {
                    return new Executable(splitInput[1]);
                }
            case HIDDEN:
                if (splitInput.length == NOT_SUFFIX_PATTERN) {
                    return new Not(new Hidden(splitInput[1]));
                } else {
                    return new Hidden(splitInput[1]);
                }
            case ALL:
                if (splitInput.length > 1) {
                    return new Not(new All());
                } else {
                    return new All();
                }
            default:
                throw new BadName(FILTER_NAME);
        }
    }
}
