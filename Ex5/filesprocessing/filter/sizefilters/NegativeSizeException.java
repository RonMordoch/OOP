package filesprocessing.filter.sizefilters;
import filesprocessing.filter.FilterException;

/**
 * An exception class for NegativeSize errors in SizeFilters.
 * Extends FilterException.
 */
public class NegativeSizeException extends FilterException {

    private static final long serialVersionUID = 1L;

    /**
     * Calls the super constructor to initialize this Exception.
     */
    public NegativeSizeException(){super();}

}
