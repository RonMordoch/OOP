package filesprocessing;

/**
 * An exception class for Invalid Usage errors, i.e. anything other than two program arguments.
 */
public class InvalidUsageException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Calls the super constructor to initialize this Exception.
     */
    public InvalidUsageException(){super();}
}
