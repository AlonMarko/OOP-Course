package filesprocessing.orders;

import java.util.Comparator;
import java.io.File;

/**
 * abstract class for orders - will implement the compartor for File objects by its
 * subclasses
 */
public abstract class Order implements Comparator<File> {
}
