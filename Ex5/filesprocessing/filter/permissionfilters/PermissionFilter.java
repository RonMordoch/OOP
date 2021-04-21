package filesprocessing.filter.permissionfilters;
import filesprocessing.filter.Filter;

/**
 * An abstract class representing all file Filters which filters by permission of files.
 */
public abstract class PermissionFilter implements Filter {

    // The string value for comparison
    protected String value;

    // The values for the permissions
    protected final static String YES = "YES";
    protected final static String NO = "NO";

    /**
     * A constructor
     * @param value a YES/NO value for permission comparison
     * @throws PermissionFilterException if value is neither YES nor NO
     */
    protected PermissionFilter(String value) throws PermissionFilterException {
        if (value.equals(YES) || value.equals(NO)) {
            this.value = value;
        }
        else { // invalid value
            throw new PermissionFilterException();
        }
    }

}
