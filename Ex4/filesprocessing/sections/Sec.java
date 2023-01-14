package filesprocessing.sections;

import filesprocessing.filters.Filter;
import filesprocessing.orders.Order;

import java.io.File;

/**
 * section class - combines filter and order to organized structure.
 */
public class Sec {
    /*
    data members
     */
    //holds a filer  + it's filter.
    private final Filter secFilter;
    private final Order secOrder;

    /**
     * section constructor
     *
     * @param filter - the section filter
     * @param order  - the section order
     */
    public Sec(Filter filter, Order order) {
        secFilter = filter;
        secOrder = order;
    }

    /**
     * returns the section order object.
     */
    public Order orderGetter() {
        return secOrder;
    }

    /**
     * returns the section filter object
     */
    public Filter filterGetter() {
        return secFilter;
    }


}
