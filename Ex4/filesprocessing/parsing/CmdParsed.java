package filesprocessing.parsing;


import filesprocessing.ProcessingExeption;
import filesprocessing.TypeOne;
import filesprocessing.filters.Filter;
import filesprocessing.filters.FilterFactory;
import filesprocessing.orders.Order;
import filesprocessing.orders.OrderFactory;
import filesprocessing.sections.Sec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * class for a command file that was parsed. it holds an array of sections
 */
public class CmdParsed {
    /*
    constants
     */
    private static final String DEFAULT_FILTER = "all";
    private static final String DEFAULT_ORDER = "abs";

    /*
    data members
     */
    // array that holds sections objects.
    private final ArrayList<Sec> secsArray;
    // hashmap for storing lines where error occurred - each section acts as a key and the line where error
    // occurred is the value
    private final Map<Sec, ArrayList<Integer>> messagesMap;

    /**
     * constructor
     */
    public CmdParsed() {
        secsArray = new ArrayList<>();
        messagesMap = new HashMap<>();
    }

    /**
     * getter for the sections
     *
     * @return array of sections objects
     */
    public ArrayList<Sec> secGetter() {
        return secsArray;
    }


    /**
     * getter for the lines where errors occurred
     *
     * @param section - the section which we want error for
     * @return - array of line numbers
     */
    public Integer[] errorLineGetter(Sec section) {
        return messagesMap.get(section).toArray(new Integer[0]);
    }


    /**
     * creates and adds a section to the section array - handles errors aswell.
     *
     * @param filter - the given filter string
     * @param order  - the given order string
     * @param line   - the line counter
     * @throws ProcessingExeption - processing exception thrown if raised during creating procedure
     */
    public void secAdd(String filter, String order, int line) throws ProcessingExeption {
        Filter secFilter;
        Order secOrder;
        ArrayList<Integer> warnings = new ArrayList<Integer>();
        try {
            secFilter = FilterFactory.filterCreator(filter);
        } catch (TypeOne e) {
            warnings.add(line + 1);
            secFilter = FilterFactory.filterCreator(DEFAULT_FILTER);
        }
        try {
            secOrder = OrderFactory.orderCreator(order);
        } catch (TypeOne e) {
            warnings.add(line + 3);
            secOrder = OrderFactory.orderCreator(DEFAULT_ORDER);
        }
        Sec section = new Sec(secFilter, secOrder);
        messagesMap.put(section, warnings);
        secsArray.add(section);
    }
}
