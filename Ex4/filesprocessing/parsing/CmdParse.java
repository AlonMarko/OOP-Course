package filesprocessing.parsing;

import filesprocessing.ProcessingExeption;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * The parser of the Commands File. knows its structure, and parse it line by line into a parsed-file object.
 */
public class CmdParse {
    /*
    constants
    */
    //  for the sections
    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final String FILE_FORMAT_UNSUITABLE = "Unsuitable command file format";
    private static final String SUBSECTION_UNSUITABLE = "Unsuitable subsection";
    // since invalid order as type 1 error are treated as "abs"
    private static final String INVALID_ORDER = "abs";

    /*
    data members
     */
    // counter for current line that is a section
    private int currSecCounter;
    // if a section is 3 rows instead of 4 we use this to match the counters
    private int altSec;
    //counter
    private int lineCounter;
    // the path of the file
    private final String parsePath;
    // a line inside a being read file
    private String inFileLine;
    // the file after its being parsed.
    private final CmdParsed parsedFile;
    // flag for a filter passed in the command file.
    private boolean filterRead;


    /**
     * constructor
     *
     * @param cmdAbsPath - file path
     */
    public CmdParse(String cmdAbsPath) {
        parsePath = cmdAbsPath;
        parsedFile = new CmdParsed();
        lineCounter = 1;
        filterRead = false;
    }

    /**
     * reads the command file
     *
     * @return - the file after its been read after and it was parsed
     * @throws ProcessingExeption - exception to throw if anything is wrong with the commands / arguments
     * @throws IOException        - - exception to throw normally if an IO error happends
     */
    public CmdParsed parse() throws ProcessingExeption, IOException {
        BufferedReader cmdFile = new BufferedReader(new FileReader(parsePath));
        readLines(cmdFile);
        cmdFile.close();
        return parsedFile;
    }


    /* Reads the file, line by line - throw exceptions if needed, create sections. */

    /**
     * reads the file and hands the line to parser functions accordingly.
     *
     * @param bufferedFile - the file after it was loaded into the buffer
     * @throws IOException        - IO exception
     * @throws ProcessingExeption - processing exception
     */
    private void readLines(BufferedReader bufferedFile) throws IOException, ProcessingExeption {
        inFileLine = bufferedFile.readLine();
        if (inFileLine != null) {
            firstLineParse();
            currSecCounter = lineCounter;
            filterRead = true;
        }
        while (inFileLine != null) {
            if (!filterRead) {
                inFileLine = bufferedFile.readLine();
                if (inFileLine == null) {
                    break;
                }
                currSecCounter = ++lineCounter;
                filterRead = false;
            }
            firstLineParse();
            inFileLine = bufferedFile.readLine();
            lineCounter++;
            String filter = secondLineParse();
            inFileLine = bufferedFile.readLine();
            lineCounter++;
            thirdLineParse();
            inFileLine = bufferedFile.readLine();
            lineCounter++;
            String order = fourthLineCheck();
            parsedFile.secAdd(filter, order, currSecCounter);
            currSecCounter = altSec;
        }
    }

    /**
     * parses the first line from the file and checks for problems in it - throws exception if found
     *
     * @throws ProcessingExeption
     */
    private void firstLineParse() throws ProcessingExeption {
        if (!inFileLine.equals(FILTER)) {
            throw new UnsuitableSubSecException(SUBSECTION_UNSUITABLE);
        }
    }


    /**
     * checks for teh filter type and throws exception as needed.
     *
     * @return the line
     * @throws ProcessingExeption - exception throw
     */
    private String secondLineParse() throws ProcessingExeption {
        if (inFileLine == null) {
            throw new UnsuitableFormatException(FILE_FORMAT_UNSUITABLE);
        }
        return inFileLine;
    }


    /**
     * checks the third line - if contains an order
     *
     * @throws ProcessingExeption processing exception
     * @throws IOException        IO exception
     */
    private void thirdLineParse() throws ProcessingExeption, IOException {
        if (inFileLine == null) {
            throw new UnsuitableFormatException(FILE_FORMAT_UNSUITABLE);
        } else if (!inFileLine.equals(ORDER)) {
            throw new UnsuitableSubSecException(SUBSECTION_UNSUITABLE);
        }
    }

    /* handling the 4th line, which might be part of the old section, or the start of a new one. */

    /**
     * checks the 4th line - to see if we got an order start a new section
     *
     * @return the line itself.
     * @throws IOException        IO exception
     * @throws ProcessingExeption processing exception
     */
    private String fourthLineCheck() throws IOException, ProcessingExeption {
        if (inFileLine == null) {
            return INVALID_ORDER;
        }
        if (!inFileLine.equals(FILTER)) {
            filterRead = false;
            return fourthLineParse();
        } else {
            filterRead = true;
            altSec = lineCounter;
            return INVALID_ORDER;
        }
    }

    /**
     * parses the fourth line - which can be an specific order or a new section start with filter
     *
     * @return the fourth line
     * @throws ProcessingExeption processing exception
     * @throws IOException        IO exception
     */
    private String fourthLineParse() throws ProcessingExeption, IOException {
        if (inFileLine == null) {
            throw new UnsuitableFormatException(FILE_FORMAT_UNSUITABLE);
        }
        return inFileLine;
    }
}
