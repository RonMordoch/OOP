package filesprocessing.filter.filenamefilters;
import filesprocessing.filter.Filter;

/**
 * An abstract class representing all file Filters which filters by name of the files.
 */
public abstract class FileNameFilter implements Filter {

    // The string value for comparison
    protected String value;

    /**
     * Constructs this Filter object with the given value.
     * @param value String value for comparison
     */
    public FileNameFilter(String value){
        this.value = value;
    }

}
