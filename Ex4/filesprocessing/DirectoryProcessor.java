package filesprocessing;

import filesprocessing.parsing.CmdParse;
import filesprocessing.parsing.CmdParsed;
import filesprocessing.sections.Sec;
import sort.MSorter;

import java.util.ArrayList;
import java.io.IOException;
import java.io.File;

/**
 * the main processor class - runs everything
 */
public class DirectoryProcessor {
    /*
     constants
    */
    // messages
    private static final String WARNING_MESSAGE = "Warning in line ";
    private static final String TYPE_TWO_MESSAGE = "ERROR: ";
    private static final String ARGS_NUM_USAGE = "Usage, accepts two arguments.\n";
    private static final String FILE_DIR_ONE = "Usage, path needed for first argument.\n";
    private static final String FILE_DIR_TWO = "Usage, path needed the second argument. \n";
    // source dir idx
    private static final int SRC_DIR_IDX = 0;
    // command file idx
    private static final int CMD_FILE_IDX = 1;
    // number of arguments accepted
    private static final int ARGS_NUM = 2;

    /**
     * this method verifies the arguments exist and are legal.
     *
     * @param args - string array - the input
     * @throws TypeTwo - usage error
     */
    private static void argumentsVerification(String[] args) throws TypeTwo {
        if (args.length != ARGS_NUM) {
            throw new UsageHandler(ARGS_NUM_USAGE);
        }
        File fileOne = new File(args[SRC_DIR_IDX]);
        File fileTwo = new File(args[CMD_FILE_IDX]);
        if (!fileOne.isDirectory()) {
            throw new UsageHandler(FILE_DIR_ONE);
        } else if (!fileTwo.isFile()) {
            throw new UsageHandler(FILE_DIR_TWO);
        }
    }

    /**
     * runs through the directory given and filters according to the filter/order given.
     *
     * @param sectionsArray - array of sections
     * @param parsedCmd     - parsed cmd file
     * @param dirFiles      - teh files to filter/sort
     */
    private static void dirProcess(ArrayList<Sec> sectionsArray, CmdParsed parsedCmd, File[] dirFiles) {
        Integer[] lineErrors;
        for (Sec section : sectionsArray) {
            lineErrors = parsedCmd.errorLineGetter(section);
            File[] filteredArray;
            for (int line : lineErrors) {
                System.err.println(WARNING_MESSAGE + line);
            }
            filteredArray = section.filterGetter().filter(dirFiles);
            MSorter.arraysSort(filteredArray, section.orderGetter());
            for (File file : filteredArray) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * the driver function.
     *
     * @param args - input from cmd line - user given
     * @throws ProcessingExeption - throws exception as needed.
     */
    public static void main(String[] args) throws ProcessingExeption {
        try {
            argumentsVerification(args);
            File dir = new File(args[SRC_DIR_IDX]);
            File[] dirFiles = dir.listFiles(file -> (!file.isDirectory()));
            File cmdFile = new File(args[CMD_FILE_IDX]);
            CmdParsed parsedFile = new CmdParse(cmdFile.getAbsolutePath()).parse();
            ArrayList<Sec> sections = parsedFile.secGetter();
            dirProcess(sections, parsedFile, dirFiles);
        } catch (TypeTwo | IOException e) {
            System.err.println(TYPE_TWO_MESSAGE + e.getMessage());
        }
    }
}
