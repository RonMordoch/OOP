package filesprocessing.filter.sizefilters;
import filesprocessing.filter.Filter;

/**
 * An abstract class representing all file Filters which filters by size of files.
 */
public abstract class SizeFilter implements Filter {

    // conversion from kilobytes to bytes
    protected final static double KILOBYTE = 1024;

    /**
     * Parse a string representing size in kilobytes into a double representing size in bytes.
     * @param value a string representing file size in kilobytes
     * @return a double representing size in bytes
     */
    protected double parseDouble(String value) {
        return Double.parseDouble(value) * KILOBYTE;
    }
}
